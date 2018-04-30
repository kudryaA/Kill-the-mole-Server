package kudrya.killthemole.info.struct

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class PersonMessage {
    @Id
    @Column
    lateinit var login: String

    @Column
    var message: Int = 0
}