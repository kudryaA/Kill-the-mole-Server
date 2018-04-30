package kudrya.killthemole.info.repositories

import kudrya.killthemole.info.struct.PersonInfo
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonInfoRepository: CrudRepository<PersonInfo, String>