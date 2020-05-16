package no.message.messageOnKotlin.controller

import junit.framework.Assert.assertEquals
import no.message.messageOnKotlin.testdata.testBruker1
import no.message.messageOnKotlin.testdata.testBruker2
import no.message.messageOnKotlin.testdata.testMeldinger
import no.message.messageOnKotlin.model.Bruker
import no.message.messageOnKotlin.service.BrukerService
import no.message.messageOnKotlin.service.MeldingService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.ui.ModelMap

@RunWith(MockitoJUnitRunner.Silent::class)
class LoginControllerTest {

    var loginController =  LoginController()
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
        loginController.LoginController(brukerService, meldingService)
    }

    @Test
    fun test_login(){
        Mockito.`when`(brukerService.hentBruker(ArgumentMatchers.anyString())).thenReturn(testBruker1())
        Mockito.`when`(meldingService.getAlleMineMeldinger(any(Bruker::class.java))).thenReturn(testMeldinger())

        var resultat = testBruker1()?.name?.let { loginController.login(it,modelMap ) }

        verify(brukerService, times(1)).hentBruker(ArgumentMatchers.anyString())
        verify(meldingService, times(1)).getAlleMineMeldinger(any(Bruker::class.java))

        assertEquals(resultat, "meldinger")
    }

    @Test
    fun test_login_opprett_bruker_hvis_ikke_finnes_i_db(){
        Mockito.`when`(brukerService.hentBruker(ArgumentMatchers.anyString())).thenReturn(null)
        Mockito.`when`(brukerService.createBruker(any(Bruker::class.java))).thenReturn(testBruker2())

        var resultat = testBruker1()?.name?.let { loginController.login(it,modelMap ) }

        verify(brukerService, times(1)).hentBruker(ArgumentMatchers.anyString())
        verify(brukerService, times(1)).createBruker(any(Bruker::class.java))

        assertEquals(resultat, "meldinger")
    }

    private fun <T> any(type : Class<T>): T {
        Mockito.any(type)
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T
}