package kudrya.killthemole.secure

import kudrya.killthemole.secure.dao.PersonAuthorisationDatabaseController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SecureController {
    @Autowired
    private lateinit var databaseController: PersonAuthorisationDatabaseController

    fun check(login: String, password: String): Boolean {
        val person = databaseController[login]
        return if (person == null)
            false
        else {
            Authorisation(person.password, password).status
        }
    }
}