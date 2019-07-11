package com.example.quotes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_saved_quotes.*
import kotlinx.android.synthetic.main.activity_saved_quotes.view.*

class SavedQuotes : Fragment() {

    lateinit var quotes:ArrayList<Quotee>
    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        quotes= ArrayList()

        realm= Realm.getDefaultInstance()





    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.activity_saved_quotes,container,false)
        val result=realm.where(Quotee::class.java).findAll()

        for (quote in result){

            quotes.add(quote)
        }
        view.saved_recycler.layoutManager=LinearLayoutManager(context)
       view.saved_recycler.adapter=Adapter(quotes)
        return  view
    }
}
