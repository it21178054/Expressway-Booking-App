package com.example.ticket_bppking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DatabaseRegistrar
import com.google.firebase.database.FirebaseDatabase
import java.util.Date

class booking : AppCompatActivity() {

    private lateinit var nameb: EditText
    private lateinit var arivalb: EditText
    private lateinit var departureb: EditText
    private lateinit var noofseatsb: EditText
    private lateinit var dateb: EditText
    private lateinit var timeb: EditText
    private lateinit var btnbookingb: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        nameb = findViewById(R.id.nameb)
        arivalb = findViewById(R.id.arivalb)
        departureb = findViewById(R.id.departureb)
        noofseatsb = findViewById(R.id.noofseatsb)
        dateb = findViewById(R.id.dateb)
        timeb = findViewById(R.id.timeb)
        btnbookingb = findViewById(R.id.bookingb)

        dbRef = FirebaseDatabase.getInstance().getReference("customers")

        btnbookingb.setOnClickListener {
            saveCustomerData()
        }
    }
    private fun saveCustomerData(){

        //geting values
        val name = nameb.text.toString()
        val arival = arivalb.text.toString()
        val departure = departureb.text.toString()
        val noofseats = noofseatsb.text.toString()
        val date = dateb.text.toString()
        val time = timeb.text.toString()


        if (name.isEmpty()){
            nameb.error = "please enter name"
        }
        if (arival.isEmpty()){
            arivalb.error = "please enter arival place"
        }
        if (departure.isEmpty()){
            departureb.error = "please enter departure place"
        }
        if (noofseats.isEmpty()){
            noofseatsb.error = "please enter no of seats"
        }
        if (date.isEmpty()){
            dateb.error = "please enter date"
        }
        if (time.isEmpty()){
            timeb.error = "please enter time"
        }
        val  customerId = dbRef.push().key!!

        val customer = CustomerModel (customerId,name,arival,departure,noofseats,date,time)

        dbRef.child(customerId).setValue(customer)
            .addOnCompleteListener{
                Toast.makeText(this,"booking successfully",Toast.LENGTH_LONG).show()

                nameb.text.clear()
                arivalb.text.clear()
                departureb.text.clear()
                noofseatsb.text.clear()
                dateb.text.clear()
                timeb.text.clear()

            }.addOnFailureListener{err ->
                Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
            }

    }



        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()




    }



