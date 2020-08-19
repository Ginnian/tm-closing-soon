package nz.co.trademe.techtest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nz.co.trademe.wrapper.TradeMeApi
import nz.co.trademe.wrapper.dto.ClosingSoonListings
import nz.co.trademe.wrapper.dto.ListedItemDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListingRepository {

    companion object {
        const val TAG = "ListingRepository"

        private var listingRepository: ListingRepository? = null
        @Synchronized
        fun getInstance(): ListingRepository {
            if (listingRepository == null) {
                listingRepository = ListingRepository()
            }
            return listingRepository!!
        }
    }

    fun getListings(): LiveData<ClosingSoonListings> {
        val liveData = MutableLiveData<ClosingSoonListings>()
        TradeMeApi.listingService.retrieveClosingSoonListings().enqueue(object : Callback<ClosingSoonListings> {
            override fun onFailure(call: Call<ClosingSoonListings>, t: Throwable) {
                liveData.value = null
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<ClosingSoonListings>, response: Response<ClosingSoonListings>) {
                liveData.value = response.body()
           }
        })
        return liveData
    }

    fun getListingDetails(listingId: Long): LiveData<ListedItemDetail> {
        val liveData = MutableLiveData<ListedItemDetail>()
        TradeMeApi.listingService.retrieveListedItemDetail(listingId).enqueue(object: Callback<ListedItemDetail> {
            override fun onFailure(call: Call<ListedItemDetail>, t: Throwable) {
                liveData.value = null
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<ListedItemDetail>, response: Response<ListedItemDetail>) {
                liveData.value = response.body()
            }
        })
        return liveData
    }
}

