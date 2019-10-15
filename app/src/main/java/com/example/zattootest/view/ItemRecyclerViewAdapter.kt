package com.example.zattootest.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zattootest.R
import com.example.zattootest.model.ArrayOffers
import com.example.zattootest.model.MovieDataDetails
import com.example.zattootest.model.Constants.Companion.IMAGEBASE_URL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_item.view.*

//@author Dmitry Tkachuk
class ItemRecyclerViewAdapter : RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder>() {

    private var moviecount : Int = 0
    private var mlist: List<Pair<MovieDataDetails, ArrayOffers>>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item, parent, false)
            return ViewHolder(view)
    }

    //Here we're download pictures via Picasso and paste data to XML elements
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(IMAGEBASE_URL + (mlist?.get(position)?.second?.mimage ))
                                                                             .into(holder.imgposter)
        holder.txttitle.text = mlist?.get(position)?.first?.mtitle
        holder.txtsubtitle.text = mlist?.get(position)?.first?.msub_title
        holder.txtcost.text = mlist?.get(position)?.second?.mpice
        holder.txtavailable.text = mlist?.get(position)?.second?.mavailable.toString()
    }

    //After we received the items from server we push it to the adapter
    fun  setMovies(list: List<Pair<MovieDataDetails, ArrayOffers>>?){
        this.mlist = list
        moviecount = this.mlist?.size ?: 0
    }

    override fun getItemCount(): Int {
          return moviecount
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val imgposter = view.imgposter
            val txttitle = view.txttitle
            val txtsubtitle = view.txtsubtitle
            val txtcost = view.txtcost
            val txtavailable = view.txtavailable
        }
}
