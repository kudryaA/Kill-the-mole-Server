package kudrya.killthemole.info.struct

import com.google.gson.annotations.Expose
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class PersonInfo {
    @Id
    @Column
    @Expose
    lateinit var login: String

    @Column
    @Expose
    lateinit var name: String

    @Column
    var place: String? = null

    @Column
    var current: String? = null

    @Column
    var time: String? = null

    @Column
    var image: String? = null

    override fun toString(): String {
        return "PersonInfo(login='$login', name='$name', place=$place, current=$current, time=$time)"
    }


}