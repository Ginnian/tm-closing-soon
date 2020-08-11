package nz.co.trademe.techtest

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_listings_main.view.*
import nz.co.trademe.wrapper.dto.ListingDetails

class ListingsRecyclerAdapter(private val context: Context, private val listings: List<ListingDetails>) :
        RecyclerView.Adapter<ListingsRecyclerAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val imageIv = v.auction_image_main_iv!!
        val titleTv = v.auction_title_main_tv!!
        val closingTimeTv = v.auction_closing_time_tv!!
        val rowLayout = v.auction_row_cv!!
        val regionTv = v.auction_region_tv
        val nicknameTv = v.auction_nickname_tv
        val priceDisplayTv = v.auction_priceDisplay_tv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_listings_main, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listings.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTv.text = listings[position].title
        Picasso.get()
                .load(Uri.parse(listings[position].pictureHref ?: ""))
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .resize(50, 50)
                .centerCrop()
                .into(holder.imageIv)
        holder.regionTv.text = listings[position].region
        holder.priceDisplayTv.text = listings[position].priceDisplay
//        holder.nicknameTv.text = listings[position].

        holder.rowLayout.setOnClickListener {
            val intent = Intent(context, ListingDetailsActivity::class.java)
            intent.putExtra("listingId", listings[position].listingId)
            context.startActivity(intent)
        }
    }
}