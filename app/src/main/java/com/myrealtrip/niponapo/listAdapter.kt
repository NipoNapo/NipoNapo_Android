package com.example.niponapo_3

import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.niponapo_3.ui.home.HomeFragment
import com.example.niponapo_3.ui.post.PostFragment
import kotlin.coroutines.coroutineContext
import android.widget.Toast.makeText as makeText1

class listAdapter(val PostedList: ArrayList<PostedList>) : RecyclerView.Adapter<listAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener {

                val curPos: Int = adapterPosition
                val post: PostedList = PostedList.get(curPos)
                //Toast.makeText(parent.context, "교환이 요청되었습니다.", Toast.LENGTH_SHORT).show()
                val builder =  AlertDialog.Builder(parent.context)
                builder.setTitle("교환 요청")
                builder.setMessage("성공적으로 교환 요청 되었습니다.")
                builder.setPositiveButton("확인"){
                        dialog: DialogInterface?, i: Int ->
                }
                builder.show()
            }

        }
    }

    override fun getItemCount(): Int {
        return PostedList.size
    }

    override fun onBindViewHolder(holder: listAdapter.CustomViewHolder, position: Int) {
        holder.pf_img.setImageResource(PostedList.get(position).pf_img)
        holder.user_id.text = PostedList.get(position).user_id
        holder.c_way.text = PostedList.get(position).c_way
        holder.p_date.text = PostedList.get(position).p_date
        holder.req_btn.text = PostedList.get(position).req_btn

    }

    //내부 클래
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pf_img = itemView.findViewById<ImageView>(R.id.li_profile)
        val user_id = itemView.findViewById<TextView>(R.id.il_user_id)
        val c_way = itemView.findViewById<TextView>(R.id.il_user_way)
        val p_date = itemView.findViewById<TextView>(R.id.il_user_date)
        val req_btn = itemView.findViewById<TextView>(R.id.il_req)


    }
}