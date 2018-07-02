package test.org.mytwitterreader.data

interface TokenRepository {
    fun storeToken(token: String)
    fun getToken(): String
    fun isTokenExist(): Boolean
}