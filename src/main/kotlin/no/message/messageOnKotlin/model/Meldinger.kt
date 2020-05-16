package no.message.messageOnKotlin.model

import javax.persistence.*

@Entity
class Meldinger(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var meldingId: Long?  = null,

    var melding: String = "",

    @ManyToOne(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.MERGE))
    @JoinColumn(name = "frabrukerid")
    var frabruker: Bruker?  = null,

    @ManyToOne(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.MERGE))
    @JoinColumn(name = "tilbrukerid")
    var tilbruker: Bruker? = null
)

