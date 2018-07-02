package test.org.mytwitterreader.model.twitter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserMention(

        @SerializedName("screen_name")
        @Expose
        val screenName: String? = null,
        @SerializedName("name")
        @Expose
        val name: String? = null,
        @SerializedName("id")
        @Expose
        val id: Long? = null,
        @SerializedName("id_str")
        @Expose
        val idStr: String? = null,
        @SerializedName("indices")
        @Expose
        val indices: List<Long>? = null
)