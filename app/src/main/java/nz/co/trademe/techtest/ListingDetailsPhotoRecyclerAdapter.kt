package nz.co.trademe.techtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_listing_details.view.*
import kotlinx.android.synthetic.main.activity_listing_details_image.view.*
import nz.co.trademe.wrapper.dto.ListedItemDetail

class ListingDetailsPhotoRecyclerAdapter(private val photos: ListedItemDetail) :
        RecyclerView.Adapter<ListingDetailsPhotoRecyclerAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val listingImagesIv = v.listing_detail_images_iv!!
        val primaryImage = v.listing_detail_current_iv!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_listing_details_image, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return photos.photoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        assignImage(photos.photoList[position].gallery.pictureHref,
                R.drawable.no_image,
                R.drawable.no_image,
                holder.primaryImage)

        holder.listingImagesIv.setOnClickListener {
            assignImage(photos.photoList[position].gallery.pictureHref,
                    R.drawable.no_image,
                    R.drawable.no_image,
                    holder.primaryImage)
        }
    }

    private fun assignImage(uri: String, placeholderImage: Int,
                            errorImage: Int, targetImageView: ImageView) {
        Picasso.get()
                .load(uri)
                .placeholder(placeholderImage)
                .error(errorImage)
                .resize(50, 50)
                .centerCrop()
                .into(targetImageView)
    }
}