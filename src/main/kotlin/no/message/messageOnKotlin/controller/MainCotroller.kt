package no.message.messageOnKotlin.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainCotroller {
    @GetMapping("/")
    public fun index(): String {
        return "index"
    }
}