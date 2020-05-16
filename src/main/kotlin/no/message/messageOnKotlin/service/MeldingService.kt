package no.message.messageOnKotlin.service

import no.message.messageOnKotlin.model.Bruker
import no.message.messageOnKotlin.model.Meldinger
import no.message.messageOnKotlin.repository.MeldingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MeldingService {
    private var meldingRepository: MeldingRepository? = null

    @Autowired
    fun setMeldingRepository(meldingRepository: MeldingRepository) {
        this.meldingRepository = meldingRepository
    }

    fun getAlleMineMeldinger(bruker: Bruker): List<Meldinger>? {
        return meldingRepository?.findAllByTilbruker(bruker)
    }

    fun sendMelding(meldinger: Meldinger): Meldinger {
        return meldingRepository!!.save(meldinger)
    }
}