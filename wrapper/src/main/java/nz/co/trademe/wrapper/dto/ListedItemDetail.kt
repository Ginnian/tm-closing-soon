package nz.co.trademe.wrapper.dto

import com.squareup.moshi.Json

data class ListedItemDetail(
        @Json(name = "ListingId") val listingId: Long,
        @Json(name = "Title") val title: String,
        @Json(name = "PriceDisplay") val endDate: String,
        @Json(name = "Member") val member: Member,
        @Json(name = "Body") val body: String,
        @Json(name = "Photos") val photoList: List<Gallery>
)

data class Gallery (
        @Json(name = "Value") val gallery: Photos
)

data class Photos (
        @Json(name = "PhotoId") val photoId: Int,
        @Json(name = "FullSize") val pictureHref: String
)
