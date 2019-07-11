package com.example.quotes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.DropBoxManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.like.LikeButton
import com.like.OnLikeListener
import com.squareup.picasso.Picasso
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_main.view.*


class MainActivity : Fragment() {

    lateinit var requestQueue: RequestQueue
     var quoteText:String?=null
     var backgroundText:String?=null
     var id:String?=null
    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = RealmConfiguration.Builder(context)
        config.deleteRealmIfMigrationNeeded()
        Realm.setDefaultConfiguration(config.build())
        realm=Realm.getDefaultInstance()


        requestQueue=Volley.newRequestQueue(context)






    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val url="http://quotes.rest/qod.json"
        val view=inflater.inflate(R.layout.activity_main,container,false)
        val jsonObjectRequest=JsonObjectRequest(0,url,null,
            Response.Listener {

                val contents=it.getJSONObject("contents")
                val quotes=contents.getJSONArray("quotes")

                for (i in 0 until quotes.length()){

                    val quoteObject=quotes.getJSONObject(i)
                    quoteText=quoteObject.getString("quote")
                    backgroundText=quoteObject.getString("background")
                    id=quoteObject.getString("id")

                    Picasso.get().load(backgroundText).placeholder(R.drawable.ic_insert_photo_black_24dp).into(view.background_image)
                    view.quote.text=quoteText
                }

            },
            Response.ErrorListener {

                Toast.makeText(context,"error",Toast.LENGTH_LONG).show()
            })
        requestQueue!!.add(jsonObjectRequest)


        view.likebutton.setOnLikeListener(object:OnLikeListener{
            override fun liked(likeButton: LikeButton?) {

                realm.beginTransaction()
                val obj=realm.createObject(Quotee::class.java)
                obj.id=id
                obj.image=backgroundText
                obj.quote=quoteText
                realm.commitTransaction()

            }

            override fun unLiked(likeButton: LikeButton?) {

            }


        })
        return view
    }


}
