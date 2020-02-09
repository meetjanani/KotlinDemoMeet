package `in`.meetjanani.kotlindemomeet

import com.google.gson.annotations.SerializedName

data class MediaModel(@SerializedName("charityMedia") val charityMedia : List<CharityMedia>
)
{
    data class CharityMedia (

        @SerializedName("id") val id : Int,
        @SerializedName("charity_id") val charity_id : Int,
        @SerializedName("type") val type : Int,
        @SerializedName("mediaFrom") val mediaFrom : Int,
        @SerializedName("url") val url : String,
        @SerializedName("created_at") val created_at : String,
        @SerializedName("updated_at") val updated_at : String,
        @SerializedName("deleted_at") val deleted_at : String
    )
}


