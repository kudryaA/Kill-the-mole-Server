package kudrya.killthemole.secure

import kudrya.killthemole.secure.hash.Hash
import kudrya.killthemole.secure.hash.MDA5Hash

class Authorisation(correct: String, current: String) {
    private val hash: Hash = MDA5Hash(current)
    val status = correct.toLowerCase() == hash.hash.toLowerCase()
}