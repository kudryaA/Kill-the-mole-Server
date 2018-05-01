package kudrya.killthemole.server.info.dao

import kudrya.killthemole.server.info.repositories.PersonInfoRepository
import kudrya.killthemole.server.info.struct.PersonInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class PersonInfoDatabaseController {
    @Autowired
    private lateinit var repository: PersonInfoRepository

    val persons: List<PersonInfo>
        get() {
            val res = ArrayList<PersonInfo>()
            repository.findAll().forEach {
                res.add(it)
            }
            return res
        }
    operator fun get(login: String): PersonInfo? {
        return if (repository.existsById(login)) {
            repository.findById(login).get()
        } else {
            null
        }
    }

    operator fun set(login: String, place: String) {
        val person = get(login)
        if (person != null) {
            person.current = place
            person.time = Date().toString()
            repository.existsById(login)
            repository.save(person)
        }

    }
}