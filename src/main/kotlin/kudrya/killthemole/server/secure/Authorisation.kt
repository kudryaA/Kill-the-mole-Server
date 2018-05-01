package kudrya.killthemole.server.secure

import kudrya.killthemole.server.secure.hash.Hash
import kudrya.killthemole.server.secure.hash.MDA5Hash

class Authorisation(correct: String, current: String) {
    private val hash: Hash = MDA5Hash(current)
    val status = correct.toLowerCase() == hash.hash.toLowerCase()
}