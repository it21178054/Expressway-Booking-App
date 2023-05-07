package com.example.payment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button stripeButton;
    EditText amountEditText;
    String paymentIntentClientSecret, amount;
    PaymentSheet.CustomerConfiguration customerConfig;
    PaymentSheet paymentSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stripeButton = findViewById(R.id.stripeButton);
        amountEditText = findViewById(R.id.amountEditText);

        stripeButton.setOnClickListener(view -> {
            if (TextUtils.isEmpty(amountEditText.getText().toString())) {
                Toast.makeText(this, "Amount cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                amount = amountEditText.getText().toString() + "00";
                getDetails();
            }
        });

        // Initialize PaymentSheet (moved to getDetails method)
        // paymentSheet = new PaymentSheet(this, this::onPaymentSheetResult);
    }

    private void onPaymentSheetResult(PaymentSheetResult paymentSheetResult) {
        if (paymentSheetResult instanceof PaymentSheetResult.Canceled) {
            Toast.makeText(this, "Payment Cancelled", Toast.LENGTH_SHORT).show();
        } else if (paymentSheetResult instanceof PaymentSheetResult.Failed) {
            Toast.makeText(this, ((PaymentSheetResult.Failed) paymentSheetResult).getError().toString(), Toast.LENGTH_SHORT).show();
        } else if (paymentSheetResult instanceof PaymentSheetResult.Completed) {
            Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();
        }
    }

    void getDetails() {
        Fuel.INSTANCE.post("https://us-central1-payment-b2230.cloudfunctions.net/helloWorld?amt=" + amount, null).responseString(new Handler<String>() {
            @Override
            public void success(String s) {
                try {
                    JSONObject result = new JSONObject(s);
                    customerConfig = new PaymentSheet.CustomerConfiguration(
                            result.getString("customer"),
                            result.getString("ephemeralKey")
                    );
                    paymentIntentClientSecret = result.getString("paymentIntent");
                    PaymentConfiguration.init(getApplicationContext(), result.getString("publishableKey"));

                    // Initialize PaymentSheet here
                    paymentSheet = new PaymentSheet(MainActivity.this, MainActivity.this::onPaymentSheetResult);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showStripePaymentSheet();
                        }
                    });

                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(@NonNull FuelError fuelError) {
                // Handle failure case
            }
        });
    }

    void showStripePaymentSheet() {
        final PaymentSheet.Configuration configuration = new PaymentSheet.Configuration.Builder("ExpressWay.inc")
                .customer(customerConfig)
                .allowsDelayedPaymentMethods(true)
                .build();
        paymentSheet.presentWithPaymentIntent(
                paymentIntentClientSecret,
                configuration
        );
    }
}
