package no.message.messageOnKotlin.testdata

import no.message.messageOnKotlin.model.Bruker
import no.message.messageOnKotlin.model.Meldinger
import java.util.*

fun testBruker1(): Bruker? {
    val bruker = Bruker( 1L, "Some Name1")
    return bruker
}

fun testBruker2(): Bruker? {
    val bruker = Bruker(2L, "Some Name2")
    return bruker
}

fun testMelding(): Meldinger? {
    val meldinger = Meldinger(1L,"some text",testBruker1(),testBruker2())
    return meldinger
}

fun testMeldinger(): List<Meldinger>? {
    val meldinger = Meldinger(1L," some melding", testBruker1(), testBruker2())
    val list: MutableList<Meldinger> = ArrayList<Meldinger>()
    list.add(meldinger)
    return list
}