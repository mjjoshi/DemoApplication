package com.spaceo.practicaltest.Practical1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.spaceo.practicaltest.Practical1.Model.Result
import com.spaceo.practicaltest.R

import kotlinx.android.synthetic.main.row_item_recylerview.view.*


class ResponseDataAdapter(private val context: Context,
    private val navItemList:List<Result>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var VIEWTYPE_ITEM = 1

    private var VIEWTYPE_LOAD = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_item_recylerview, parent, false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MyViewHolder) {
            val item = navItemList[position]
            //Picasso.with(context).load(item.Image).into(holder.itemView.imgPic)
            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.strokeWidth = 1f
            circularProgressDrawable.centerRadius = 15f
            circularProgressDrawable.start()
            val options = RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
//            Glide.with(context)
//                .load(item.Image)
//                .error(R.drawable.user_img)
//                .fallback(R.drawable.user_img)
//                .placeholder(circularProgressDrawable)
//                .apply(options)
//                .into(holder.itemView.imgPic)
            //Glide.with(holder.itemView.context).load(item.Image).into(holder.itemView.imgPic)
            holder.itemView.txtTitle.text=item.Title
            holder.itemView.txtDesciption.text=item.Description
            holder.itemView.setOnClickListener {
                listener.onItemClick(position)

            }
        }

    }


    fun AppCompatImageView.loadImageCurvedCorner(url: String?, cornerRadius: Int = 18) {


    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount(): Int {
        return navItemList.size

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun getItemViewType(position: Int): Int {
        return VIEWTYPE_ITEM

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}
