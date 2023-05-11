package com.example.ticket_bppking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.ticket_bppking.R

class EditBookingActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var arrivalEditText: EditText
    private lateinit var departureEditText: EditText
    private lateinit var seatsEditText: EditText
    private lateinit var dateEditText: EditText
    private lateinit var timeEditText: EditText
    private lateinit var editButton: Button
    private lateinit var deleteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editbooking)

        // Initialize EditText fields and buttons
        nameEditText = findViewById(R.id.namee)
        arrivalEditText = findViewById(R.id.arivale)
        departureEditText = findViewById(R.id.departuree)
        seatsEditText = findViewById(R.id.noofseatse)
        dateEditText = findViewById(R.id.datee)
        timeEditText = findViewById(R.id.timee)
        editButton = findViewById(R.id.edite)
        deleteButton = findViewById(R.id.deletee)

        // Retrieve data from the intent
        val name = intent.getStringExtra("name")
        val arrival = intent.getStringExtra("arrival")
        val departure = intent.getStringExtra("departure")
        val seats = intent.getStringExtra("noofseats")
        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")

        // Set the retrieved data to the corresponding EditText fields
        nameEditText.setText(name)
        arrivalEditText.setText(arrival)
        departureEditText.setText(departure)
        seatsEditText.setText(seats)
        dateEditText.setText(date)
        timeEditText.setText(time)

        // Set click listener for the edit button
        editButton.setOnClickListener {
            // Get the updated values from the EditText fields
            val updatedName = nameEditText.text.toString()
            val updatedArrival = arrivalEditText.text.toString()
            val updatedDeparture = departureEditText.text.toString()
            val updatedSeats = seatsEditText.text.toString()
            val updatedDate = dateEditText.text.toString()
            val updatedTime = timeEditText.text.toString()

            // TODO: Update the booking data in the database or any other storage medium

            // Show a toast message to indicate successful booking update
            Toast.makeText(this, "Booking updated successfully", Toast.LENGTH_SHORT).show()

            // Close the activity after the booking has been updated
            finish()
        }

        // Set click listener for the delete button
        deleteButton.setOnClickListener {
            // TODO: Delete the booking data from the database or any other storage medium

            // Show a toast message to indicate successful booking deletion
            Toast.makeText(this, "Booking deleted successfully", Toast.LENGTH_SHORT).show()

            // Close the activity after the booking has been deleted
            finish()
        }
    }
}
