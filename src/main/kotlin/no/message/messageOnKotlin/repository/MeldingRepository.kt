package no.message.messageOnKotlin.repository

import no.message.messageOnKotlin.model.Bruker
import no.message.messageOnKotlin.model.Meldinger
import org.springframework.data.jpa.repository.JpaRepository

interface MeldingRepository : JpaRepository<Meldinger, Long> {
    fun findAllByTilbruker(bruker: Bruker): List<Meldinger>?
}