package no.message.messageOnKotlin.controller

import no.message.messageOnKotlin.model.Bruker
import no.message.messageOnKotlin.model.Meldinger
import no.message.messageOnKotlin.service.BrukerService
import no.message.messageOnKotlin.service.MeldingService
import no.message.messageOnKotlin.testdata.testBruker1
import no.message.messageOnKotlin.testdata.testBruker2
import no.message.messageOnKotlin.testdata.testMelding
import no.message.messageOnKotlin.testdata.testMeldinger
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.ui.ModelMap

@RunWith(MockitoJUnitRunner.Silent::class)
class MeldingControllerTest {

    var meldingController = MeldingController()

    val modelMap: ModelMap
        get() {
            return ModelMap()
        }

    @Mock
    lateinit var brukerService : BrukerService
    @Mock
    lateinit var meldingService: MeldingService

    @Before
    fun setUp(){
        meldingController.MeldingController(meldingService, brukerService)
    }

    @Test
    fun test_sendMelding(){
        val header = HttpHeaders()
        header.contentType = MediaType.APPLICATION_JSON
        val responseEntity = ResponseEntity(testMelding(), header, HttpStatus.OK)

        Mockito.`when`(brukerService.hentBruker("test1")).thenReturn(testBruker1())
        Mockito.`when`(brukerService.hentBruker("test2")).thenReturn(testBruker2())
        Mockito.`when`(meldingService.sendMelding(any(Meldinger::class.java))).thenReturn(testMelding())

        val resultat = meldingController.sendMelding("test2", "some text", "test1", modelMap)

        verify(brukerService, times(2)).hentBruker(ArgumentMatchers.anyString())
        verify(meldingService, times(1)).sendMelding(any(Meldinger::class.java))
        assertEquals(responseEntity.statusCode, resultat.statusCode)
    }

    @Test
    fun test_test_sendMelding_som_bruker_ikkeFinnes_iDB(){
        val header = HttpHeaders()
        header.contentType = MediaType.APPLICATION_JSON
        val responseEntity = ResponseEntity(testMelding(), header, HttpStatus.BAD_REQUEST)

        Mockito.`when`(brukerService.hentBruker("test1")).thenReturn(null)

        val resultat = meldingController.sendMelding("test2", "some text", "test1", modelMap)

        verify(brukerService, times(1)).hentBruker(ArgumentMatchers.anyString())
        assertEquals(responseEntity.statusCode, resultat.statusCode)
    }

    @Test
    fun test_hvisAlleMeldingenemine(){
        val header = HttpHeaders()
        header.contentType = MediaType.APPLICATION_JSON
        val responseEntity = ResponseEntity(testMelding(), header, HttpStatus.OK)

        Mockito.`when`(brukerService.hentBruker(ArgumentMatchers.anyString())).thenReturn(testBruker1())
        Mockito.`when`(meldingService.getAlleMineMeldinger(any(Bruker::class.java))).thenReturn(testMeldinger())

        val resultat = testBruker1()?.name?.let { meldingController.visAlleMeldingenemine(it) }
        verify(brukerService, times(1)).hentBruker(ArgumentMatchers.anyString())
        verify(meldingService, times(1)).getAlleMineMeldinger(any(Bruker::class.java))
        assertEquals(responseEntity.statusCode, resultat?.statusCode)
    }

    private fun <T> any(type : Class<T>): T {
        Mockito.any(type)
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T
}