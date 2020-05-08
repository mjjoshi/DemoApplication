package com.spaceo.practicaltest.pratical3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.spaceo.practicaltest.R
import kotlinx.android.synthetic.main.row_item_recylerview.view.*


class ResponseDataAdapterP(
    private val context: Context,
    private val navItemList: List<Result>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var VIEWTYPE_ITEM = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item_recylerviewp, parent, false)
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
            Glide.with(context)
                .load(item.MerchantLogo)
                .error(R.drawable.user_img)
                .fallback(R.drawable.user_img)
                .placeholder(circularProgressDrawable)
                .apply(options)
                .into(holder.itemView.imgPic)
            //Glide.with(holder.itemView.context).load(item.Image).into(holder.itemView.imgPic)
            holder.itemView.txtTitle.text = item.StoreName
            holder.itemView.txtDesciption.text = "${item.TowerNumber}:${item.UnitNumber}"
            holder.itemView.setOnClickListener {
                listener.onItemClick(position)

            }
        }

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
