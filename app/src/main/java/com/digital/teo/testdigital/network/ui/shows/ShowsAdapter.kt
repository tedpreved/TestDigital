package com.digital.teo.testdigital.network.ui.shows

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.digital.teo.testdigital.R
import com.digital.teo.testdigital.network.model.Show

/**
 * Created by Teo on 8/15/17
 */
class ShowsAdapter(val context: Context, val listOfShows: List<Show>) : RecyclerView.Adapter<ShowsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        return ViewHolder(context = context,
                itemView = layoutInflater.inflate(R.layout.item_shows_list, null))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = listOfShows[position]
        holder?.bind(show = item)
    }

    override fun getItemCount(): Int = listOfShows.size

    class ViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {
        val icon = itemView.findViewById(R.id.itemShowIcon) as ImageView
        val name = itemView.findViewById(R.id.itemShowTvName) as TextView

        fun bind(show: Show) {
            Glide.with(context)
                    .load(show.urlIcon)
                    .into(icon)

            name.text = show.name
        }
    }
}