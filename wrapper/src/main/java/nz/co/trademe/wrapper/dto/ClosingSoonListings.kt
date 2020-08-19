package nz.co.trademe.wrapper.dto

import com.squareup.moshi.Json

data class ClosingSoonListings(
        @Json(name = "TotalCount") val totalCount: Int,
        @Json(name = "List") val closingSoonListings: List<ListingDetails>
)

data class ListingDetails(
        @Json(name = "ListingId") val listingId: Long,
        @Json(name = "Title") val title: String,
        @Json(name = "PictureHref") val pictureHref: String?,
        @Json(name = "Region") val region: String,
        @Json(name = "PriceDisplay") val priceDisplay: String,
        @Json(name = "MemberId") val memberId: Long,
        @Json(name = "EndDate") val listingClosingDate: String
)