package nz.co.trademe.techtest.activites

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_listing_details.*
import nz.co.trademe.techtest.R
import nz.co.trademe.techtest.adapters.ListingDetailsPhotoRecyclerAdapter
import nz.co.trademe.wrapper.TradeMeApi
import nz.co.trademe.wrapper.dto.ListedItemDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListingDetailsActivity : AppCompatActivity(), Callback<ListedItemDetail>, ListingDetailsPhotoRecyclerAdapter.OnImageClickListener {
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

        if (body != null) {
            listing_title_tv.text = body.title
            listing_description_tv.text = body.body
            listing_member_nickname_tv.text = body.member.nickname
            listing_member_location_tv.text = body.member.region
            listing_negative_feedback_tv.text = body.member.uniqueNegative.toString()
            listing_positive_feedback_tv.text = body.member.uniquePositive.toString()
            if (body.primaryPhotoId != null) {
                listing_images_rv.visibility = View.VISIBLE
                Glide.with(this)
                        .load(body.photoList[0].gallery.pictureHref)
                        .placeholder(R.drawable.no_image)
                        .centerCrop()
                        .into(listing_detail_current_iv)
                listing_images_rv.adapter =
                        ListingDetailsPhotoRecyclerAdapter(this, body, this)
            }
        } else {
            Toast.makeText(this, response.message(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onImageClick(position: Int, photoHref: String) {
        Log.d(TAG,"Item $position clicked. Href: $photoHref")

        Glide.with(this)
                .load(photoHref)
                .placeholder(R.drawable.no_image)
                .centerCrop()
                .into(listing_detail_current_iv)
    }

    companion object {
        const val TAG = "ListingDetailsActivity"
    }
}