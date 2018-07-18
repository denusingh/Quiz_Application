package com.rahul.quiz.quiz


import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.squareup.picasso.Picasso

class RecyclerviewAdapter(private var listdata: List<Test>) : RecyclerView.Adapter<RecyclerviewAdapter.MyHolder>() {
    internal lateinit var context: Context
    internal lateinit var data: Test
    internal var p: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_layout, parent, false)
        context = parent.context
        return MyHolder(view)
    }


    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        data = listdata[position]
        p = position
        holder.test_name.text = data.tesT_NAME
        Picasso.with(context)
                .load(data.tesT_IMAGE)
                .into(holder.test_image)
        holder.hiddentext.text = Integer.toString(position)

    }


    override fun getItemCount(): Int {
        return listdata.size
    }


    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var test_name: TextView
        var hiddentext: TextView
        var test_image: ImageView
        var start_test_but: Button
        private val testClickListener: TestClickListener? = null

        init {
            hiddentext = itemView.findViewById(R.id.hidden_text) as TextView
            test_image = itemView.findViewById(R.id.test_image) as ImageView
            test_name = itemView.findViewById(R.id.test_name) as TextView
            start_test_but = itemView.findViewById(R.id.start_but) as Button
            itemView.setOnClickListener(this)

            start_test_but.setOnClickListener(this)
            hiddentext.visibility = View.INVISIBLE

        }

        override fun onClick(v: View) {
            if (v === start_test_but) {
                GlobalData.test_id = hiddentext.text.toString().trim { it <= ' ' }
                val i = Intent(context, StartTestinterface::class.java)
                context.startActivity(i)
            }
        }
    }
}
