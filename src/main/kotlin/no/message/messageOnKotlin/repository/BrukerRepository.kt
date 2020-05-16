package no.message.messageOnKotlin.repository

import no.message.messageOnKotlin.model.Bruker
import org.springframework.data.repository.CrudRepository

interface BrukerRepository : CrudRepository<Bruker, Long> {
    fun findByName(name: String): Bruker?
}