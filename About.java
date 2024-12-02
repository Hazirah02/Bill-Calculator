package com.example.billscalculator; // Ensure this matches your package name

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;



public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about); // Correct way to reference the layout

        // Initialize the back button and set its click listener
        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener((View.OnClickListener) v -> {
            finish(); // Close the About activity and return to the previous activity
        });

        // Set up a clickable link in the TextView
        TextView linkTextView = findViewById(R.id.websiteLink);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            linkTextView.setText(Html.fromHtml("For more information, visit <a href='https://github.com/Hazirah02?tab'>our website</a>.", Html.FROM_HTML_MODE_LEGACY));
        } else {
            linkTextView.setText(Html.fromHtml("For more information, visit <a href='https://github.com/Hazirah02?tab'>our website</a>."));
        }

        // Enable link clicking
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }


}
