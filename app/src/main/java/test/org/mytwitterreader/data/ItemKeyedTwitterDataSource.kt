package test.org.mytwitterreader.data

import android.arch.paging.ItemKeyedDataSource
import android.util.Log
import test.org.mytwitterreader.model.twitter.TwitterPost
import java.io.IOException

class ItemKeyedTwitterDataSource(
        private val twitterApi: TwitterApi,
        private val userName: String)
    : ItemKeyedDataSource<Long, TwitterPost>() {

    override fun getKey(item: TwitterPost) = item.id ?: 0


    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<TwitterPost>) {
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<TwitterPost>) {
        val request = twitterApi.getPostsBefore(userName, true, params.key, params.requestedLoadSize)
        try {
            val response = request.execute()
            val items = response.body() ?: emptyList()
            if (!items.isEmpty())
                Log.d("API:", "received: " + items.size + " items. Last id: " + items[items.size - 1].id)
            callback.onResult(items)
        } catch (ioException: IOException) {

        }
    }


    override fun loadInitial(
            params: LoadInitialParams<Long>,
            callback: LoadInitialCallback<TwitterPost>) {
        val request = twitterApi.getPosts(userName, true, params.requestedLoadSize)
        try {
            val response = request.execute()
            val items = response.body() ?: emptyList()
            if (!items.isEmpty())
                Log.d("API:", "received: " + items.size + " items. Last id: " + items[items.size - 1].id)
            callback.onResult(items)
        } catch (ioException: IOException) {

        }
    }
}