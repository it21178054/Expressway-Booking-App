package com.example.firebasekotlin.activies

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasekotlin.R
import com.example.firebasekotlin.models.CustomerModel
import com.google.firebase.database.FirebaseDatabase

class CustomerDetailsActivity:AppCompatActivity() {
    private lateinit var tvId:TextView
    private lateinit var tvName: TextView
    private lateinit var tvNic: TextView
    private lateinit var tvTel:TextView
    private lateinit var tvEmail:TextView
    private lateinit var tvInq:TextView
    private lateinit var btnUpdate:TextView
    private lateinit var btnDelete:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra( "cuName").toString(),
                intent.getStringExtra( "cuNic").toString()
            )
        }

        btnDelete.setOnClickListener{
            intent.getStringExtra( "cuName".toString())?.let { it1 ->
                deleteRecord(
                    it1
                )
            }
        }
    }
    private  fun deleteRecord(
        cusName: String

    ){
      val dbRef =FirebaseDatabase.getInstance().getReference( "customers").child(cusName)
        val mTask=dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText( this,"Employee data deleted",Toast.LENGTH_LONG).show()
            val intent=Intent( this,FetchingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnSuccessListener { error ->
            Toast.makeText( this, "Deleting err ${error.message}",Toast.LENGTH_LONG).show()
        }
    }

    private fun initView(){}

    private fun setValuesToViews(){

        tvName.text=intent.getStringExtra( "cuName")
        tvNic.text=intent.getStringExtra( "cuNic")
        tvTel.text=intent.getStringExtra( "cuTel")
        tvEmail.text=intent.getStringExtra( "cuEmail")
        tvInq.text=intent.getStringExtra( "cuInquiry")

    }

    @SuppressLint("MissingInflatedId")
    private fun openUpdateDialog(
        cusName: String,
        cusNic:String

    ){
        val mDialog=AlertDialog.Builder( this)
        val inflater=layoutInflater
        val mDialogView=inflater.inflate(R.layout.update_dialog,null)

        mDialog.setView(mDialogView)

        val etName=mDialogView.findViewById<EditText>(R.id.etName)
        val etNic=mDialogView.findViewById<EditText>(R.id.etNic)
        val etTp=mDialogView.findViewById<EditText>(R.id.etTp)
        val etEmail=mDialogView.findViewById<EditText>(R.id.etEmail)
        val etInq=mDialogView.findViewById<EditText>(R.id.etInq)
        val btnUpdateData=mDialogView.findViewById<EditText>(R.id.btnUpdateData)


        etName.setText(intent.getStringExtra( "cuName").toString())
        etNic.setText(intent.getStringExtra( "cuNic").toString())
        etTp.setText(intent.getStringExtra( "cuTp").toString())
        etEmail.setText(intent.getStringExtra( "cuEmail").toString())
        etInq.setText(intent.getStringExtra( "cuInq").toString())

        mDialog.setTitle("Updating $cusName Record")

        val alertDialog=mDialog.create()
        alertDialog.show()

       btnUpdateData.setOnClickListener {
           updateCusData(

               etName.text.toString(),
               etNic.text.toString(),
               etTp.text.toString(),
               etEmail.text.toString(),
               etInq.text.toString()
           )

           Toast.makeText(applicationContext, "Customer Data Updated",Toast.LENGTH_LONG).show()


           tvName.text= etName.text.toString()
           tvNic.text=etNic.text.toString()
           tvTel.text=etTp.text.toString()
           tvEmail.text=etEmail.text.toString()
           tvInq.text=etInq.text.toString()

           alertDialog.dismiss()

       }

    }
    private fun updateCusData(
        name: String,
        nic:String,
        tel:String,
        Email:String,
        Inquiry:String

    ){

        val dbRef=FirebaseDatabase.getInstance().getReference( "customers").child(name)
        val cusInfo=CustomerModel(name,nic,tel,Email,Inquiry)
        dbRef.setValue(cusInfo)

    }
}