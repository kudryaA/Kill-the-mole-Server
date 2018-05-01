package kudrya.killthemole.server.secure.repositories

import kudrya.killthemole.server.secure.entity.PersonAuthorisation
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonAuthorisationRepository: CrudRepository<PersonAuthorisation, String>