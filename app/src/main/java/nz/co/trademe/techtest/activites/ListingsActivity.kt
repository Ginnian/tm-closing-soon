package nz.co.trademe.techtest.activites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import nz.co.trademe.techtest.R
import nz.co.trademe.techtest.adapters.ListingsRecyclerAdapter
import nz.co.trademe.techtest.viewmodels.ListingsViewModel
import nz.co.trademe.wrapper.dto.ClosingSoonListings

class ListingsActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ListingsActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel =
                ViewModelProvider(this).get(ListingsViewModel::class.java)

        val listings = viewModel.getListingsObservable()

        listings.observe(this, Observer<ClosingSoonListings> {
            listings -> layout_rv_listings.adapter =
                    ListingsRecyclerAdapter(this, listings.closingSoonListings)
        })
    }
}