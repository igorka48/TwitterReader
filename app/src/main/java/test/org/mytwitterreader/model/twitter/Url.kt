package test.org.mytwitterreader.model.twitter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Url (

    @SerializedName("url")
    @Expose
    val url: String? = null,
    @SerializedName("expanded_url")
    @Expose
    val expandedUrl: String? = null,
    @SerializedName("display_url")
    @Expose
    val displayUrl: String? = null,
    @SerializedName("indices")
    @Expose
    val indices: List<Int>? = null

)