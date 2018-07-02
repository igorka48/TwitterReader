package test.org.mytwitterreader.data

import android.content.Context
import android.content.Context.MODE_PRIVATE


class PersistentUserRepository(context: Context) : UserRepository {

    companion object {
        private const val USER_KEY = "USER_KEY"
        private const val PREFS_NAME = "MY_PREFS_NAME"
        private const val DEFAULT_USER = "Android"
    }

    private val prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

    override fun storeUser(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_KEY, token)
        editor.commit()
    }

    override fun getUser() = prefs.getString(USER_KEY, DEFAULT_USER)

}