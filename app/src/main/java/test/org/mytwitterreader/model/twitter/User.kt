package test.org.mytwitterreader.model.twitter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User (

        @SerializedName("id")
    @Expose
    val id: Long? = null,
        @SerializedName("id_str")
    @Expose
    val idStr: String? = null,
        @SerializedName("name")
    @Expose
    val name: String? = null,
        @SerializedName("screen_name")
    @Expose
    val screenName: String? = null,
        @SerializedName("location")
    @Expose
    val location: String? = null,
        @SerializedName("description")
    @Expose
    val description: String? = null,
        @SerializedName("url")
    @Expose
    val url: String? = null,
        @SerializedName("entities")
    @Expose
    val entities: Entities? = null,
        @SerializedName("protected")
    @Expose
    val protected: Boolean? = null,
        @SerializedName("followers_count")
    @Expose
    val followersCount: Int? = null,
        @SerializedName("friends_count")
    @Expose
    val friendsCount: Int? = null,
        @SerializedName("listed_count")
    @Expose
    val listedCount: Int? = null,
        @SerializedName("created_at")
    @Expose
    val createdAt: String? = null,
        @SerializedName("favourites_count")
    @Expose
    val favouritesCount: Int? = null,
        @SerializedName("utc_offset")
    @Expose
    val utcOffset: Int? = null,
        @SerializedName("time_zone")
    @Expose
    val timeZone: String? = null,
        @SerializedName("geo_enabled")
    @Expose
    val geoEnabled: Boolean? = null,
        @SerializedName("verified")
    @Expose
    val verified: Boolean? = null,
        @SerializedName("statuses_count")
    @Expose
    val statusesCount: Int? = null,
        @SerializedName("lang")
    @Expose
    val lang: String? = null,
        @SerializedName("contributors_enabled")
    @Expose
    val contributorsEnabled: Boolean? = null,
        @SerializedName("is_translator")
    @Expose
    val isTranslator: Boolean? = null,
        @SerializedName("is_translation_enabled")
    @Expose
    val isTranslationEnabled: Boolean? = null,
        @SerializedName("profile_background_color")
    @Expose
    val profileBackgroundColor: String? = null,
        @SerializedName("profile_background_image_url")
    @Expose
    val profileBackgroundImageUrl: String? = null,
        @SerializedName("profile_background_image_url_https")
    @Expose
    val profileBackgroundImageUrlHttps: String? = null,
        @SerializedName("profile_background_tile")
    @Expose
    val profileBackgroundTile: Boolean? = null,
        @SerializedName("profile_image_url")
    @Expose
    val profileImageUrl: String? = null,
        @SerializedName("profile_image_url_https")
    @Expose
    val profileImageUrlHttps: String? = null,
        @SerializedName("profile_banner_url")
    @Expose
    val profileBannerUrl: String? = null,
        @SerializedName("profile_link_color")
    @Expose
    val profileLinkColor: String? = null,
        @SerializedName("profile_sidebar_border_color")
    @Expose
    val profileSidebarBorderColor: String? = null,
        @SerializedName("profile_sidebar_fill_color")
    @Expose
    val profileSidebarFillColor: String? = null,
        @SerializedName("profile_text_color")
    @Expose
    val profileTextColor: String? = null,
        @SerializedName("profile_use_background_image")
    @Expose
    val profileUseBackgroundImage: Boolean? = null,
        @SerializedName("has_extended_profile")
    @Expose
    val hasExtendedProfile: Boolean? = null,
        @SerializedName("default_profile")
    @Expose
    val defaultProfile: Boolean? = null,
        @SerializedName("default_profile_image")
    @Expose
    val defaultProfileImage: Boolean? = null,
        @SerializedName("following")
    @Expose
    val following: Boolean? = null,
        @SerializedName("follow_request_sent")
    @Expose
    val followRequestSent: Boolean? = null,
        @SerializedName("notifications")
    @Expose
    val notifications: Boolean? = null,
        @SerializedName("translator_type")
    @Expose
    val translatorType: String? = null

)