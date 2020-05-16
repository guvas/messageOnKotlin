package no.message.messageOnKotlin.controller

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MainControllerTest {

    @Test
    fun test_index(){
        var mainCotroller = MainCotroller();
        var resutat = mainCotroller.index();
        assertEquals(resutat, "index")
    }
}
