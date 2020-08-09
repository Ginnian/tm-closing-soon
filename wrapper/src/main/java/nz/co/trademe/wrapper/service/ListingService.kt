package nz.co.trademe.wrapper.service

import nz.co.trademe.wrapper.dto.ClosingSoonListings
import nz.co.trademe.wrapper.dto.ListedItemDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ListingService {

    @GET("v1/Listings/closing.json?rows=250&sort_order=ExpiryAsc")
    fun retrieveClosingSoonListings(): Call<ClosingSoonListings>

    @GET("https://api.trademe.co.nz/v1/Listings/{listingId}.json")
    fun retrieveListedItemDetail(@Path("listingId") listingId: Long): Call<ListedItemDetail>
}
