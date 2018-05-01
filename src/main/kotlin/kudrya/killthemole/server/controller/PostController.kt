package kudrya.killthemole.server.controller

import com.google.gson.GsonBuilder
import kudrya.killthemole.server.info.dao.PersonInfoDatabaseController
import kudrya.killthemole.server.info.dao.PersonMessageDatabaseController
import kudrya.killthemole.server.secure.SecureController
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class PostController {
    @Autowired
    private lateinit var infoDatabase: PersonInfoDatabaseController

    @Autowired
    private lateinit var messageDatabase: PersonMessageDatabaseController

    @Autowired
    private lateinit var secureConfig: SecureController

    companion object {
        private val logger = Logger.getLogger(PostController::class.java)
    }


    @RequestMapping(method = [(RequestMethod.POST)])
    @ResponseBody
    fun index(): String {
        val list = infoDatabase.persons
        val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create()
        return gson.toJson(list)
    }

    @RequestMapping(value = ["/info"], method = [(RequestMethod.POST)])
    @ResponseBody
    fun showInfo(@RequestParam(value="login", required=true, defaultValue="login") login: String): String {
        val person = infoDatabase[login]
        return if (person == null) {
            logger.warn("Info about not exist user with $login")
            "error"
        } else {
            val gson = GsonBuilder().setPrettyPrinting().create()
            gson.toJson(person)
        }
    }

    @RequestMapping(value = ["/update"], method = [(RequestMethod.POST)])
    @ResponseBody
    fun updateInfo(@RequestParam(value="login", required=true, defaultValue="login") login: String,
                   @RequestParam(value="password", required=true, defaultValue="password") password: String,
                   @RequestParam(value="place", required=true, defaultValue="place") place: String): String {
        return if (secureConfig.check(login, password)) {
            infoDatabase[login] = place
            messageDatabase[login] = 0
            "success"
        } else {
            logger.warn("Error authorisation login=$login password=$password")
            "error"
        }
    }

    @RequestMapping(value = ["/check"], method = [(RequestMethod.POST)])
    @ResponseBody
    fun check(@RequestParam(value="login", required=true, defaultValue="login") login: String,
                   @RequestParam(value="password", required=true, defaultValue="password") password: String): String {
        return if (secureConfig.check(login, password)) {
            "success"
        } else {
            logger.warn("Error authorisation login=$login password=$password")
            "error"
        }
    }


    @RequestMapping(value = ["/message"], method = [(RequestMethod.POST)])
    @ResponseBody
    fun message(@RequestParam(value="login", required=true, defaultValue="login") login: String,
                   @RequestParam(value="password", required=true, defaultValue="password") password: String): String {
        return if (secureConfig.check(login, password)) {
            val person = messageDatabase[login]
            if (person == null) {
                "error"
            } else {
                val gson = GsonBuilder().setPrettyPrinting().create()
                gson.toJson(person)
            }
        } else {
            logger.warn("Error authorisation login=$login password=$password")
            "error"
        }
    }

    @RequestMapping(value = ["/send"], method = [(RequestMethod.POST)])
    @ResponseBody
    fun sendMessage(@RequestParam(value="login", required=true, defaultValue="login") login: String): String {
        messageDatabase[login] = 1
        val person = messageDatabase[login]
        return if (person == null) {
            logger.warn("Send request for not exist user with $login")
            "error"
        } else {
            val gson = GsonBuilder().setPrettyPrinting().create()
            gson.toJson(person)
        }
    }
}