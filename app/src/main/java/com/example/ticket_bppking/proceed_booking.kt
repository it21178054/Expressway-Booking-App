import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.ticket_bppking.R

class TicketBookingActivity : AppCompatActivity() {

    private lateinit var seatA1Button: Button
    private lateinit var seatB1Button: Button
    private lateinit var proceedToBookingButton: Button
    private lateinit var busOperatorTextView: TextView
    private lateinit var facilitiesTextView: TextView
    private lateinit var seatLayoutImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proceed_booking)

        // Initialize views
        seatA1Button = findViewById(R.id.seatA1Button)
        seatB1Button = findViewById(R.id.seatB1Button)
        proceedToBookingButton = findViewById(R.id.proceedToBookingButton)
        busOperatorTextView = findViewById(R.id.busoperatorp)
        facilitiesTextView = findViewById(R.id.facilitiesp)
        seatLayoutImageView = findViewById(R.id.seatLayoutImageViewp)

        // Set click listeners for seat buttons
        seatA1Button.setOnClickListener {
            // Handle seat A1 button click
        }

        seatB1Button.setOnClickListener {
            // Handle seat B1 button click
        }

        proceedToBookingButton.setOnClickListener {
            // Handle proceed to booking button click
        }
    }
}
