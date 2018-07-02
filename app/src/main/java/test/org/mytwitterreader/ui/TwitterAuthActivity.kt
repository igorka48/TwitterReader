package test.org.mytwitterreader.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import test.org.mytwitterreader.R
import test.org.mytwitterreader.data.PersistentTokenRepository
import test.org.mytwitterreader.data.TokenRepository
import test.org.mytwitterreader.data.TwitterApi
import test.org.mytwitterreader.model.twitter.TokenResponse

class TwitterAuthActivity : AppCompatActivity() {

    //TODO: it might be provided by DI of ServiceLocator
    private lateinit var tokenRepository: TokenRepository


    companion object {
        /**
         * Concatenate the encoded consumer key, a colon character ”:”
         * https://developer.twitter.com/en/docs/basics/authentication/overview/application-only.html
         */
        private const val TWITTER_CONSUMER_AND_CONSUMER_SECRET_KEYS = ""
    }


    private val twitterApi = TwitterApi.createAuthClient( Base64.encodeToString(TWITTER_CONSUMER_AND_CONSUMER_SECRET_KEYS.toByteArray(), Base64.NO_WRAP))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter_auth)
        tokenRepository = PersistentTokenRepository(applicationContext)


        checkToken()
    }

    private fun checkToken() {
        if(tokenRepository.isTokenExist()){
            launchMainActivity()
        } else {
            obtainToken()
        }
    }

    private fun obtainToken() {
        twitterApi.getToken("client_credentials").enqueue(object: Callback<TokenResponse>{
            override fun onFailure(call: Call<TokenResponse>?, t: Throwable?) {
                showErrorMessage(t?.localizedMessage ?: "SOME ERROR")
            }

            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
                if(response.isSuccessful){
                    tokenRepository.storeToken(response.body()?.accessToken ?: return)
                    launchMainActivity()
                } else {
                    showErrorMessage(response.message())
                }
            }
        })
    }

    private fun launchMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun showErrorMessage(message: String){
        AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton("ok") { _, _ ->
                    finish()
                }
                .show()

    }
}
