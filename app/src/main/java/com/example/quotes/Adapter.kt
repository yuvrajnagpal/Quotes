package com.example.quotes

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.like.OnLikeListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.quote_item.view.*

class Adapter(val quotes:ArrayList<Quotee>?=null):RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {

        val view=LayoutInflater.from(p0.context).inflate(R.layout.quote_item,p0,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {


        return quotes!!.size

    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        p0.bindview(quotes!![p1])

        p0.itemView.setOnLongClickListener(object:View.OnLongClickListener{
           override fun onLongClick(v: View?): Boolean {

               quotes.removeAt(p1)
               notifyItemRemoved(p1)
               return true
           }

       })
    }


    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {


        fun bindview(quote: Quotee) {

            Picasso.get().load(Uri.parse(quote.image)).into(itemView.saved_image)
            itemView.saved_quote.text=quote.quote

        }
    }
}
