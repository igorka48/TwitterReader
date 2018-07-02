package test.org.mytwitterreader.data

import android.arch.paging.DataSource
import test.org.mytwitterreader.model.twitter.TwitterPost

class TwitterDataSourceFactory(
        private val twitterApi: TwitterApi,
        private val userName: String) : DataSource.Factory<Long, TwitterPost>() {
    override fun create(): DataSource<Long, TwitterPost> {
        val source  = ItemKeyedTwitterDataSource(twitterApi, userName)
        return source
    }
}
