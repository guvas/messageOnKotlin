package no.message.messageOnKotlin.controller

import no.message.messageOnKotlin.model.Meldinger
import no.message.messageOnKotlin.service.BrukerService
import no.message.messageOnKotlin.service.MeldingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
@Controller
@Scope("session")
@RequestMapping("/meldinger")
class MeldingController {
    var meldingService: MeldingService? = null
    var brukerService: BrukerService? = null

    @Autowired
    fun MeldingController(meldingService: MeldingService, brukerService: BrukerService) {
        this.meldingService = meldingService
        this.brukerService = brukerService
    }
    @RequestMapping(value = ["/sendMelding"], method = arrayOf(RequestMethod.GET))
    fun sendMelding(@RequestParam(value = "tilbruker") tilBruker: String, @RequestParam(value = "melding") melding: String, @RequestParam(value = "username") username: String, model: ModelMap): ResponseEntity<*> {
        val tilBrukeren = brukerService?.hentBruker(tilBruker)
                ?: return ResponseEntity.badRequest().body("Bruker $tilBruker finnes ikke i systemet")
        val fraBrukeren = brukerService?.hentBruker(username)
        val opprettMelding = Meldinger(null, melding, fraBrukeren, tilBrukeren)
        val lagredeMelding = meldingService?.sendMelding(opprettMelding)

        return ResponseEntity.ok<Any>(lagredeMelding!!)
    }

    @RequestMapping(value = ["/hvisMeldingeneMine"], method = arrayOf(RequestMethod.GET))
    fun visAlleMeldingenemine(@RequestParam(value = "username") brukernavn: String): ResponseEntity<*> {
        val brukeren = brukerService?.hentBruker(brukernavn)
        val meldingers = meldingService?.getAlleMineMeldinger(brukeren!!)
        return ResponseEntity.ok<List<Meldinger>>(meldingers!!)
    }
}