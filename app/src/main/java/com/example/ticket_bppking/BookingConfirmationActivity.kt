//package com.example.ticket_bppking
//
//import android.os.Bundle
//import com.google.android.material.snackbar.Snackbar
//import androidx.appcompat.app.AppCompatActivity
//import androidx.navigation.findNavController
//import androidx.navigation.ui.AppBarConfiguration
//import androidx.navigation.ui.navigateUp
//import androidx.navigation.ui.setupActionBarWithNavController
//import com.example.ticket_bppking.databinding.ActivityBookingBinding
//
//class BookingConfirmationActivity : AppCompatActivity() {
//
//    private lateinit var appBarConfiguration: AppBarConfiguration
//    private var binding: ActivityBookingBinding? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityBookingBinding.inflate(layoutInflater)
//        setContentView(binding?.root)
//
//        setSupportActionBar(binding?.toolbar)
//
//        // Set up the navigation controller and configure the action bar
//        val navController = findNavController(R.id.Sharebookingc)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//
//        // Set a click listener for the FAB to display a Snackbar
//        binding?.fab?.setOnClickListener { view ->
//            Snackbar.make(view, "Confirm", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        // Handle navigation up button press
//        val navController = findNavController(R.id.Sharebookingc)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        binding = null
//    }
//}
