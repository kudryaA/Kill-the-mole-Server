package kudrya.killthemole.controller

import com.google.gson.GsonBuilder
import kudrya.killthemole.info.dao.PersonInfoDatabaseController
import kudrya.killthemole.info.dao.PersonMessageDatabaseController
import kudrya.killthemole.secure.SecureController
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
            "error"
        }
    }

    @RequestMapping(value = ["/check"], method = [(RequestMethod.POST)])
    @ResponseBody
    fun check(@RequestParam(value="login", required=true, defaultValue="login") login: String,
                   @RequestParam(value="password", required=true, defaultValue="password") password: String): String {
        return if (secureConfig.check(login, password)) { 0
            "success"
        } else {
            "error"
        }
    }


    @RequestMapping(value = ["/message"], method = [(RequestMethod.POST)])
    @ResponseBody
    fun message(@RequestParam(value="login", required=true, defaultValue="login") login: String,
                   @RequestParam(value="password", required=true, defaultValue="password") password: String): String {
        return if (secureConfig.check(login, password)) {
            val person = messageDatabase[login]
            if (person == null)
                "error"
            else {
                val gson = GsonBuilder().setPrettyPrinting().create()
                gson.toJson(person)
            }
        } else {
            "error"
        }
    }

    @RequestMapping(value = ["/send"], method = [(RequestMethod.POST)])
    @ResponseBody
    fun sendMessage(@RequestParam(value="login", required=true, defaultValue="login") login: String): String {
        messageDatabase[login] = 1
        val person = messageDatabase[login]
        return if (person == null)
            "error"
        else {
            val gson = GsonBuilder().setPrettyPrinting().create()
            gson.toJson(person)
        }
    }
}