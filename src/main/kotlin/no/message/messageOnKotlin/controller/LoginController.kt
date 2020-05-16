package no.message.messageOnKotlin.controller

import no.message.messageOnKotlin.model.Bruker
import no.message.messageOnKotlin.service.BrukerService
import no.message.messageOnKotlin.service.MeldingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
@Scope("session")
class LoginController {
    var brukerService: BrukerService? = null
    var meldingService: MeldingService? = null

    @Autowired
    fun LoginController(brukerService: BrukerService, meldingService: MeldingService) {
        this.brukerService = brukerService
        this.meldingService = meldingService
    }

    @RequestMapping(value = ["/login"], method = arrayOf(RequestMethod.POST))
    fun login(@RequestParam("username") brukernavn: String, model: ModelMap): String {
        val brukeren = brukerService?.hentBruker(brukernavn)
        if (brukeren != null) {
            model.addAttribute("username", brukernavn)
            val meldingers = meldingService?.getAlleMineMeldinger(brukeren)
            if (meldingers != null) {
                if (meldingers.isNotEmpty()) {
                    model.addAttribute("meldinger", meldingers)
                }
            }
            return "meldinger"
        } else {
            val bruker = Bruker(null, brukernavn)
            brukerService?.createBruker(bruker)
        }
        model.addAttribute("username", brukernavn)
        return "meldinger"
    }
}