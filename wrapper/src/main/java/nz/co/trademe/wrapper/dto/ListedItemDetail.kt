package nz.co.trademe.wrapper.dto

import com.squareup.moshi.Json

data class ListedItemDetail(
        @Json(name = "ListingId") val listingId: Long,
        @Json(name = "Title") val title: String,
        @Json(name = "PriceDisplay") val endDate: String,
        @Json(name = "Member") val member: Member,
        @Json(name = "PictureHref") val pictureHref: String,
        @Json(name = "Body") val body: String
)
