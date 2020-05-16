package no.message.messageOnKotlin.model

import javax.persistence.*

@Entity
data class Bruker (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var brukerid: Long? = null,

    var name: String = ""
)