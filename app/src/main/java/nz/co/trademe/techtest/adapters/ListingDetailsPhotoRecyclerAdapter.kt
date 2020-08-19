package nz.co.trademe.techtest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_listing_details_image.view.*
import nz.co.trademe.techtest.R
import nz.co.trademe.wrapper.dto.ListedItemDetail

class ListingDetailsPhotoRecyclerAdapter(
        private val context: Context,
        private val photos: ListedItemDetail,
        private val listener: OnImageClickListener
    ) : RecyclerView.Adapter<ListingDetailsPhotoRecyclerAdapter.ViewHolder>()
{
    interface OnImageClickListener {
        fun onImageClick(position: Int, photoHref: String)
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener{
        val listingImagesIv: ImageView = v.listing_detail_images_iv!!

        init { v.setOnClickListener(this) }

        override fun onClick(v: View?) {
            val position = adapterPosition
            val photoHref = photos.photoList[position].gallery.pictureHref
            if (position != RecyclerView.NO_POSITION) {
                listener.onImageClick(position, photoHref)
            }
        }
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
        Glide.with(context)
                .load(photos.photoList[position].gallery.pictureHref)
                .placeholder(R.drawable.no_image)
                .centerCrop()
                .into(holder.listingImagesIv)
    }
}