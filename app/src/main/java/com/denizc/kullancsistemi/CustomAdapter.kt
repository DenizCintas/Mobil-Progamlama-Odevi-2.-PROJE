package com.denizc.kullancsistemi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val userList: ArrayList<user>,  val context: Context ):
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvad:TextView= view.findViewById(R.id.tvad)
        val tvyas:TextView= view.findViewById(R.id.tvyas)
        val tvhakkinda:TextView= view.findViewById(R.id.tvhakkinda)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.user_item_list, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvhakkinda.text =userList[position].kendinitanit
        viewHolder.tvad.text = userList[position].adisoyadi
        viewHolder.tvyas.text = userList[position].yasi
        // Bir kullanıcıya tıklanınca detaylıca göstermek için
        viewHolder.itemView.setOnClickListener{
            var user = userList[position]
            var adisoyadi:String? = user.adisoyadi
            var yasi:String? = user.yasi
            var kendinitanit:String? = user.kendinitanit

            var intent = Intent(context, DetayActivity::class.java)
            intent.putExtra("putadisoyadi",adisoyadi)
            intent.putExtra("putyasi",yasi)
            intent.putExtra("putkendinitanit",kendinitanit)
            // hemen start diyip başlatmıyorum intente yukartdaki değişkenlerimi aktarmam gererkiyor
            context.startActivity(intent)

        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = userList.size

}