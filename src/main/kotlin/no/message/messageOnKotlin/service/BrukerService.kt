package no.message.messageOnKotlin.service

import no.message.messageOnKotlin.model.Bruker
import no.message.messageOnKotlin.repository.BrukerRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BrukerService {
    private val logger = LoggerFactory.getLogger(BrukerService::class.java)
    private var brukerRepository: BrukerRepository? = null

    @Autowired
    fun setBrukerRepository(brukerRepository: BrukerRepository) {
        this.brukerRepository = brukerRepository
    }

    fun findByName(name: String): Bruker? {
        return brukerRepository?.findByName(name)
    }

    fun createBruker(bruker: Bruker): Bruker {
        return brukerRepository!!.save(bruker)
    }

    fun hentBruker(brukernavn: String): Bruker? {
        var bruker: Bruker? = null
        try {
            bruker = findByName(brukernavn)
        } catch (e: NullPointerException) {
            logger.info("{} finnes ikke i db$brukernavn")
        }

        return bruker
    }
}