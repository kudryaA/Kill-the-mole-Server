package kudrya.killthemole.secure.entity

import javax.persistence.*

@Entity
class PersonAuthorisation {
    @Id
    @Column
    lateinit var login: String

    @Column
    lateinit var password: String


    override fun toString(): String {
        return "PersonAuthorisation(login=$login)"
    }
}