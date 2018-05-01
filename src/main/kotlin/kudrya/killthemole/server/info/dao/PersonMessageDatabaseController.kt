package kudrya.killthemole.server.info.dao

import kudrya.killthemole.server.info.repositories.PersonMessageRepository
import kudrya.killthemole.server.info.struct.PersonMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PersonMessageDatabaseController {
    @Autowired
    private lateinit var repository: PersonMessageRepository

    operator fun get(login: String): PersonMessage? {
        return if (repository.existsById(login)) {
            repository.findById(login).get()
        } else {
            null
        }
    }

    operator fun set(login: String, value: Int) {
        if (repository.existsById(login)) {
            val person = get(login)!!
            person.message = value
            repository.deleteById(login)
            repository.save(person)
        }
    }

}