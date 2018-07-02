package test.org.mytwitterreader.data

interface UserRepository {
    fun storeUser(token: String)
    fun getUser(): String
}