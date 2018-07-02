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

import android.util.Log
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import test.org.mytwitterreader.model.twitter.TokenResponse
import test.org.mytwitterreader.model.twitter.TwitterPost

/**
 * API communication setup
 */
interface TwitterApi {

    @GET("1.1/statuses/user_timeline.json")
    fun getPostsBefore(
            @Query("screen_name") userName: String,
            @Query("exclude_replies") excludeReplies: Boolean,
            @Query("max_id") maxId: Long,
            @Query("count") count: Int): Call<List<TwitterPost>>

    @GET("1.1/statuses/user_timeline.json")
    fun getPosts(
            @Query("screen_name") userName: String,
            @Query("exclude_replies") excludeReplies: Boolean,
            @Query("count") count: Int): Call<List<TwitterPost>>

    @POST("oauth2/token")
    @FormUrlEncoded
    fun getToken(
            @Field("grant_type") grantType: String): Call<TokenResponse>


//    class ListingResponse(val data: ListingData)
//
//    class ListingData(
//            val children: List<RedditChildrenResponse>,
//            val after: String?,
//            val before: String?
//    )


    companion object {
        private const val BASE_URL = "https://api.twitter.com/"
        fun createApiClient(token: String): TwitterApi = create(HttpUrl.parse(BASE_URL)!!, token, null)
        fun createAuthClient(base64EncodedCredentials: String): TwitterApi = create(HttpUrl.parse(BASE_URL)!!, null, base64EncodedCredentials)


        fun create(httpUrl: HttpUrl, token: String?, base64EncodedCredentials: String?): TwitterApi {
            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.d("API", it)
            })
            logger.level = HttpLoggingInterceptor.Level.BODY


            val twitterAuth = Interceptor { chain ->
                when {
                    token != null -> {
                        val newRequest = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer $token")
                                .build()
                        chain.proceed(newRequest)
                    }
                    base64EncodedCredentials != null -> {
                        val newRequest = chain.request().newBuilder()
                                .addHeader("Authorization", "Basic $base64EncodedCredentials")
                                .addHeader("Content-Type", " : application/x-www-form-urlencoded;charset=UTF-8")
                                .build()
                        chain.proceed(newRequest)
                    }
                    else -> chain.proceed(chain.request())
                }
            }

            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .addInterceptor(twitterAuth)
                    .build()
            return Retrofit.Builder()
                    .baseUrl(httpUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(TwitterApi::class.java)
        }
    }
}