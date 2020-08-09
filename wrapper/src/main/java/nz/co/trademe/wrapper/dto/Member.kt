package nz.co.trademe.wrapper.dto

import com.squareup.moshi.Json

data class Member (
        @Json(name = "MemberId") val memberId: Long,
        @Json(name = "Nickname") val nickname: String,
        @Json(name = "UniqueNegative") val uniqueNegative: Int,
        @Json(name = "UniquePositive") val uniquePositive: Int,
        @Json(name = "Region") val region: String
)