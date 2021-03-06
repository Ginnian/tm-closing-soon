package nz.co.trademe.wrapper.service

import nz.co.trademe.wrapper.dto.ClosingSoonListings
import nz.co.trademe.wrapper.dto.ListedItemDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ListingService {

    @GET("v1/Listings/closing.json?rows=250&photo_size=FullSize&sort_order=ExpiryAsc")
    fun retrieveClosingSoonListings(): Call<ClosingSoonListings>

    @GET("v1/Listings/{listingId}.json?return_member_profile=true")
    fun retrieveListedItemDetail(@Path("listingId") listingId: Long): Call<ListedItemDetail>
}
