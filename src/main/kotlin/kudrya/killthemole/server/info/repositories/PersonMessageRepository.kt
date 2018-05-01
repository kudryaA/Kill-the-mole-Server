package kudrya.killthemole.server.info.repositories

import kudrya.killthemole.server.info.struct.PersonMessage
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonMessageRepository: CrudRepository<PersonMessage, String>