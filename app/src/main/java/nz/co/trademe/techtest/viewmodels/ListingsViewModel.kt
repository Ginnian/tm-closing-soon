package nz.co.trademe.techtest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import nz.co.trademe.techtest.ListingRepository
import nz.co.trademe.wrapper.dto.ClosingSoonListings

class ListingsViewModel() : ViewModel() {
    private val listingsObservable =
            ListingRepository.getInstance().getListings()


    fun getListingsObservable(): LiveData<ClosingSoonListings> {
        return listingsObservable
    }

    companion object {
        const val TAG = "ListingsViewModel"
    }
}
