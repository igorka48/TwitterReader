/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package test.org.mytwitterreader.data

import android.arch.paging.ItemKeyedDataSource
import android.util.Log
import test.org.mytwitterreader.model.twitter.TwitterPost
import java.io.IOException

/**
 * A data source that uses the "name" field of posts as the key for next/prev pages.
 * <p>
 * Note that this is not the correct consumption of the Reddit API but rather shown here as an
 * alternative implementation which might be more suitable for your backend.
 * see PageKeyedSubredditDataSource for the other sample.
 */
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