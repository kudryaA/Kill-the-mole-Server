package kudrya.killthemole.secure.repositories

import kudrya.killthemole.secure.entity.PersonAuthorisation
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonAuthorisationRepository: CrudRepository<PersonAuthorisation, String>