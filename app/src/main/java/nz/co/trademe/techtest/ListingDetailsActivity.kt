package nz.co.trademe.techtest

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_listing_details.*
import nz.co.trademe.wrapper.TradeMeApi
import nz.co.trademe.wrapper.dto.ListedItemDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListingDetailsActivity : AppCompatActivity(), Callback<ListedItemDetail> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing_details)

        val listingId = intent.getLongExtra("listingId", -1L)

        TradeMeApi.listingService.retrieveListedItemDetail(listingId).enqueue(this)
    }

    override fun onFailure(call: Call<ListedItemDetail>, t: Throwable) {
        Toast.makeText(this, t.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<ListedItemDetail>, response: Response<ListedItemDetail>) {
        val body = response.body()

        viewSetup()
    }

    private fun viewSetup() {
        listing_title_tv.text = "example"
        listing_description_tv.text = "example"
        listing_member_nickname_tv.text = "example"
        listing_member_location_tv.text = "example"
        listing_negative_feedback_tv.text = "example"
        listing_positive_feedback_tv.text = "example"
//        Picasso.get()
//                .load(Uri.parse("listings[position].pictureHref" ?: ""))
//                .placeholder(R.drawable.no_image)
//                .error(R.drawable.no_image)
//                .resize(50, 50)
//                .centerCrop()
//                .into(listing_detail_current_iv)
    }
}