package kudrya.killthemole.server.secure.hash

import javax.xml.bind.DatatypeConverter
import java.security.MessageDigest



class MDA5Hash(text: String): Hash {
    override val hash: String
    init {
        val md = MessageDigest.getInstance("MD5")
        md.update(text.toByteArray())
        val digest = md.digest()
        hash = DatatypeConverter.printHexBinary(digest).toUpperCase()
    }
}