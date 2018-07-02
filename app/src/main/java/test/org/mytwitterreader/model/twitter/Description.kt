package test.org.mytwitterreader.model.twitter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Description(
        @SerializedName("urls")
        @Expose
        val urls: List<Any>? = null
)