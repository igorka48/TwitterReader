package test.org.mytwitterreader.ui

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.abnormallydriven.paginglibrarytestdrive.names.TwittsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import test.org.mytwitterreader.R
import test.org.mytwitterreader.model.twitter.TwitterPost
import android.content.Intent
import android.net.Uri
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import test.org.mytwitterreader.data.*


class MainActivity : AppCompatActivity(), PostAdapter.Callback {



    //TODO: it might be provided by DI of ServiceLocator
    private lateinit var tokenRepository: TokenRepository
    private lateinit var userRepository: UserRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tokenRepository = PersistentTokenRepository(applicationContext)
        userRepository = PersistentUserRepository(applicationContext)
        swipeRefresh.setOnRefreshListener { initData() }

        initToolbar()
        initData()
    }


    private fun initToolbar() {
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.person -> {
                showUserPromptDialog()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initData() {

        val twitterApi = TwitterApi.createApiClient(tokenRepository.getToken())
        val adapter = PostAdapter(this)
        recyclerView.adapter = adapter
        progressBar.visibility = VISIBLE

        val user = userRepository.getUser()
        collapsingToolbarLayout?.title = user

        val viewModel = TwittsViewModel(TwitterDataSourceFactory(twitterApi, user))
        viewModel.livePagedListOfNames.observe(this, Observer<PagedList<TwitterPost>>{
            if(swipeRefresh.isRefreshing)
                swipeRefresh.isRefreshing = false
            if(progressBar.visibility == VISIBLE)
                progressBar.visibility = GONE
            adapter.submitList(it)
        })
    }

    override fun onItemClicked(item: TwitterPost) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.user?.url))
        startActivity(browserIntent)
    }


    private fun showUserPromptDialog(){
        val taskEditText = EditText(this)
        val dialog = AlertDialog.Builder(this)
                .setTitle("Change user")
                .setMessage("Enter user's display name")
                .setView(taskEditText)
                .setPositiveButton("Ok") { _, _ ->
                    val userName = taskEditText.text.toString()
                    if(userName.isNotBlank()) {
                        userRepository.storeUser(userName)
                        initData()
                    }
                }
                .create()
        dialog.show()
    }

}
