package test.org.mytwitterreader.model.twitter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TwitterPost(

        @SerializedName("created_at")
        @Expose
        val createdAt: String? = null,
        @SerializedName("id")
        @Expose
        val id: Long? = null,
        @SerializedName("id_str")
        @Expose
        val idStr: String? = null,
        @SerializedName("text")
        @Expose
        val text: String? = null,
        @SerializedName("truncated")
        @Expose
        val truncated: Boolean? = null,
        @SerializedName("entities")
        @Expose
        val entities: Entities? = null,
        @SerializedName("source")
        @Expose
        val source: String? = null,
        @SerializedName("in_reply_to_status_id")
        @Expose
        val inReplyToStatusId: Any? = null,
        @SerializedName("in_reply_to_status_id_str")
        @Expose
        val inReplyToStatusIdStr: Any? = null,
        @SerializedName("in_reply_to_user_id")
        @Expose
        val inReplyToUserId: Any? = null,
        @SerializedName("in_reply_to_user_id_str")
        @Expose
        val inReplyToUserIdStr: Any? = null,
        @SerializedName("in_reply_to_screen_name")
        @Expose
        val inReplyToScreenName: Any? = null,
        @SerializedName("user")
        @Expose
        val user: User? = null,
        @SerializedName("geo")
        @Expose
        val geo: Any? = null,
        @SerializedName("coordinates")
        @Expose
        val coordinates: Any? = null,
        @SerializedName("place")
        @Expose
        val place: Any? = null,
        @SerializedName("contributors")
        @Expose
        val contributors: Any? = null,
        @SerializedName("retweeted_status")
        @Expose
        val retweetedStatus: RetweetedStatus? = null,
        @SerializedName("is_quote_status")
        @Expose
        val isQuoteStatus: Boolean? = null,
        @SerializedName("retweet_count")
        @Expose
        val retweetCount: Int? = null,
        @SerializedName("favorite_count")
        @Expose
        val favoriteCount: Int? = null,
        @SerializedName("favorited")
        @Expose
        val favorited: Boolean? = null,
        @SerializedName("retweeted")
        @Expose
        val retweeted: Boolean? = null,
        @SerializedName("possibly_sensitive")
        @Expose
        val possiblySensitive: Boolean? = null,
        @SerializedName("lang")
        @Expose
        val lang: String? = null

)