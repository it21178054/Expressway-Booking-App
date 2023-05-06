package com.example.firebasekotlin.activies

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasekotlin.models.CustomerModel
import com.example.firebasekotlin.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertionActivity:AppCompatActivity() {

    private lateinit var etName:EditText
    private lateinit var etNic:EditText
    private lateinit var etTp:EditText
    private lateinit var etEmail:EditText
    private lateinit var etInq:EditText
    private lateinit var btnSaveData:Button

    private lateinit var dbRef: DatabaseReference


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContentView(R.layout.activity_insertion)

        etName=findViewById(R.id.etName)
        etNic=findViewById(R.id.etNic)
        etTp=findViewById(R.id.etTp)
        etEmail=findViewById(R.id.etEmail)
        etInq=findViewById(R.id.etInq)
        btnSaveData=findViewById(R.id.btnSave)

        dbRef=FirebaseDatabase.getInstance().getReference("customers")

        btnSaveData.setOnClickListener {
            saveCustomerData()
        }
    }

    private fun saveCustomerData(){

        //getting values
        val cuName=etName.text.toString()
        val cuNic=etNic.text.toString()
        val cuTpNumber=etTp.text.toString()
        val cuEmail=etEmail.text.toString()
        val cuInquiry=etInq.text.toString()
if (cuName.isEmpty()){
    etName.error="Please Enter your name"
    return
}

        if (cuNic.isEmpty()){
            etNic.error="Please Enter your NIC"
            return
        }

        if (cuTpNumber.isEmpty()){
            etTp.error="Please Enter your Telephone Number"
            return
        }
        if (cuEmail.isEmpty()){
            etEmail.error="Please Enter your Email"
            return
        }
        if (cuInquiry.isEmpty()){
            etInq.error="Please Enter your Inquiry"
            return
        }

        val cuId= dbRef.push().key!!

        val customer= CustomerModel(cuId,cuName,cuNic,cuTpNumber,cuEmail,cuInquiry)

        dbRef.child(cuId).setValue(customer)
            .addOnCompleteListener {
                Toast.makeText( this,"data inserted successfully",Toast.LENGTH_LONG).show()

                etName.text.clear()
                etNic.text.clear()
                etTp.text.clear()
                etEmail.text.clear()
                etInq.text.clear()

            }.addOnFailureListener { err->
                Toast.makeText( this, "Error ${err.message}",Toast.LENGTH_LONG).show()
            }



    }



}