package com.example.firebasekotlin.activies

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasekotlin.R
import com.example.firebasekotlin.adapters.CusAdapter
import com.example.firebasekotlin.models.CustomerModel
import com.google.firebase.database.*

class FetchingActivity :AppCompatActivity(){

    private lateinit var cuRecyclerView: RecyclerView
    private lateinit var tvLoadingData:TextView
    private lateinit var cuList: ArrayList<CustomerModel>
    private lateinit var dbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate)

        cuRecyclerView=findViewById(R.id.rvCu)
        cuRecyclerView.layoutManager=LinearLayoutManager( this)
        cuRecyclerView.setHasFixedSize(true)
        tvLoadingData=findViewById(R.id.tvLoadingData)

        cuList= arrayListOf<CustomerModel>()

        getCustomerData()
    }

    private fun getCustomerData(){
        cuRecyclerView.visibility= View.GONE
        tvLoadingData.visibility=View.VISIBLE

       dbRef=FirebaseDatabase.getInstance().getReference( "customers")

        dbRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               cuList.clear()
                if(snapshot.exists()){
                    for (cuSnap in snapshot.children){
                       val cuData =cuSnap.getValue(CustomerModel::class.java)

                        cuList.add(cuData!!)
                    }
                    val mAdapter=CusAdapter(cuList)
                    cuRecyclerView.adapter=mAdapter

                    mAdapter.setOnItemClickListener(object :CusAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                           val intent=Intent( this@FetchingActivity,CustomerDetailsActivity::class.java)
                            //put extras
                            intent.putExtra( "cuId",cuList[position].cuId)
                            intent.putExtra( "cuName",cuList[position].cuName)
                            intent.putExtra( "cuNic",cuList[position].cuNic)
                            intent.putExtra( "cuTp",cuList[position].cuTpNumber)
                            intent.putExtra( "cuEmail",cuList[position].cuEmail)
                            intent.putExtra( "cuInq",cuList[position].cuInquiry)

                        }

                    })

                    cuRecyclerView.visibility=View.VISIBLE
                    tvLoadingData.visibility=View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}