package com.example.firebasekotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasekotlin.R
import com.example.firebasekotlin.models.CustomerModel

class CusAdapter(private val cuList: ArrayList<CustomerModel>):
    RecyclerView.Adapter<CusAdapter.ViewHolder>(){

    private lateinit var mListener: onItemClickListener

    interface  onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener=clickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CusAdapter.ViewHolder {
       val itemView=LayoutInflater.from(parent.context).inflate(R.layout.emp_list_item,parent, false)
        return ViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val currentCu=cuList[position]
        holder.tvName.text=currentCu.cuName
    }


    override fun getItemCount(): Int {
        return cuList.size
    }
    class ViewHolder(itemView: View,clickListener:onItemClickListener):RecyclerView.ViewHolder(itemView) {

        val tvName:TextView=itemView.findViewById(R.id.tvName)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }



}
