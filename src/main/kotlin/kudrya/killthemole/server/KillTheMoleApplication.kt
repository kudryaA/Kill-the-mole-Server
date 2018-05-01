package kudrya.killthemole.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KillTheMoleApplication {
    /*@Autowired
    lateinit var databaseController: PersonInfoDatabaseController

    @PostConstruct
    fun showDatabase() {
        /*val person = PersonAuthorisation()
        person.login = "sff"
        person.password = "password"
        person.name = "Anton"
        person.place = "place"
        person.current =  "current"*/
        //databaseController.add(person)
        //databaseController.printAll()
    }*/
}




fun main(args: Array<String>) {
    runApplication<KillTheMoleApplication>(*args)
}
