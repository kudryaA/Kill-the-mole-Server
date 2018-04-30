package kudrya.killthemole.secure.dao

import kudrya.killthemole.secure.entity.PersonAuthorisation
import kudrya.killthemole.secure.repositories.PersonAuthorisationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PersonAuthorisationDatabaseController {
    @Autowired
    private lateinit var repository: PersonAuthorisationRepository

    val persons: List<PersonAuthorisation>
        get() {
            val res = ArrayList<PersonAuthorisation>()
            repository.findAll().forEach {
                res.add(it)
            }
            return res
        }
    operator fun get(login: String): PersonAuthorisation? {
        return if (repository.existsById(login)) {
            repository.findById(login).get()
        } else {
            null
        }
    }

}