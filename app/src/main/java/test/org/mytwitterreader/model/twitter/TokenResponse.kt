package test.org.mytwitterreader.model.twitter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TokenResponse(

        @SerializedName("token_type")
        @Expose
        var tokenType: String? = null,
        @SerializedName("access_token")
        @Expose
        var accessToken: String? = null

)