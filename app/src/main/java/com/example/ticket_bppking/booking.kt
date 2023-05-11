package com.example.ticket_bppking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.Date

class BookingActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var arrivalEditText: EditText
    private lateinit var departureEditText: EditText
    private lateinit var seatsEditText: EditText
    private lateinit var dateEditText: EditText
    private lateinit var timeEditText: EditText
    private lateinit var bookingButton: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        // Initialize EditText fields and button
        nameEditText = findViewById(R.id.nameb)
        arrivalEditText = findViewById(R.id.arivalb)
        departureEditText = findViewById(R.id.departureb)
        seatsEditText = findViewById(R.id.noofseatsb)
        dateEditText = findViewById(R.id.dateb)
        timeEditText = findViewById(R.id.timeb)
        bookingButton = findViewById(R.id.bookingb)

        // Get the reference to the "customers" node in the Firebase database
        dbRef = FirebaseDatabase.getInstance().getReference("customers")

        bookingButton.setOnClickListener {
            saveCustomerData()
        }
    }

    private fun saveCustomerData() {
        // Get values from the EditText fields
        val name = nameEditText.text.toString()
        val arrival = arrivalEditText.text.toString()
        val departure = departureEditText.text.toString()
        val seats = seatsEditText.text.toString()
        val date = dateEditText.text.toString()
        val time = timeEditText.text.toString()

        // Validate input fields
        if (name.isEmpty()) {
            nameEditText.error = "Please enter name"
            return
        }
        if (arrival.isEmpty()) {
            arrivalEditText.error = "Please enter arrival place"
            return
        }
        if (departure.isEmpty()) {
            departureEditText.error = "Please enter departure place"
            return
        }
        if (seats.isEmpty()) {
            seatsEditText.error = "Please enter number of seats"
            return
        }
        if (date.isEmpty()) {
            dateEditText.error = "Please enter date"
            return
        }
        if (time.isEmpty()) {
            timeEditText.error = "Please enter time"
            return
        }

        // Generate a unique customer ID
        val customerId = dbRef.push().key!!

        // Create a CustomerModel object with the customer data
        val customer = CustomerModel(customerId, name, arrival, departure, seats, date, time)

        // Save the customer data to the Firebase database
        dbRef.child(customerId).setValue(customer)
            .addOnCompleteListener {
                Toast.makeText(this, "Booking successful", Toast.LENGTH_LONG).show()

                // Clear the EditText fields after successful booking
                nameEditText.text.clear()
                arrivalEditText.text.clear()
                departureEditText.text.clear()
                seatsEditText.text.clear()
                dateEditText.text.clear()
                timeEditText.text.clear()
            }
            .addOnFailureListener { err ->
                Toast.makeText(this, "Error: ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}
