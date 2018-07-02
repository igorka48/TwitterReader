package test.org.mytwitterreader.data

import android.content.Context
import android.content.Context.MODE_PRIVATE


class PersistentTokenRepository(context: Context): TokenRepository {

    companion object {
        private const val TOKEN_KEY = "TOKEN_KEY"
        private const val PREFS_NAME = "MY_PREFS_NAME"
    }

    private val prefs =  context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

    override fun storeToken(token: String) {
        val editor = prefs.edit()
        editor.putString(TOKEN_KEY, token)
        editor.commit()
    }

    override fun getToken() = prefs.getString(TOKEN_KEY, "")

    override fun isTokenExist() = prefs.contains(TOKEN_KEY)
}