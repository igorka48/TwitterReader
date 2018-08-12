package com.abnormallydriven.paginglibrarytestdrive.names

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import test.org.mytwitterreader.data.TwitterDataSourceFactory
import test.org.mytwitterreader.model.twitter.TwitterPost

class TwittsViewModel constructor(private val dataSourceFactory: TwitterDataSourceFactory) : ViewModel() {

    val livePagedListOfNames: LiveData<PagedList<TwitterPost>>

    init {

        val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(30)
                .setPageSize(20)
                .build()



        livePagedListOfNames = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
                .build()
    }

}