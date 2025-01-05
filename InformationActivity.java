package com.example.electriccalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_activity);

        // Reference UI components
        TextView aboutUsTitle = findViewById(R.id.about_us_title);
        TextView groupInfo = findViewById(R.id.group_info);
        TextView studentNumber = findViewById(R.id.student_number);
        TextView programmeCode = findViewById(R.id.programme_code);
        TextView nameTextView = findViewById(R.id.name_text_view);
        TextView linkText = findViewById(R.id.link_text);
        ImageView profilePicture = findViewById(R.id.profile_picture);
        Button returnButton = findViewById(R.id.btn_return);

        // Set content for text fields
        aboutUsTitle.setText("About Me");
        nameTextView.setText("Name: Nurhazirah Binti Hanapi");
        groupInfo.setText("Group: RCDCS2405A");
        studentNumber.setText("Student Number: 2022663988");
        programmeCode.setText("Programme Code: Bachelor in Information Technology");

        // Set the profile picture (Ensure image exists in drawable)
        profilePicture.setImageResource(R.drawable.img);

        // Set the clickable link text
        linkText.setText("https://github.com/Hazirah02/Bill-Calculator");

        // Use LinkMovementMethod to make the URL clickable
        linkText.setMovementMethod(LinkMovementMethod.getInstance());

        // Alternatively, you can directly handle the click in the code for a custom link
        linkText.setOnClickListener(v -> {
            // Open the actual GitHub link when clicked
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Hazirah02/Bill-Calculator"));
            startActivity(browserIntent);
        });

        // Handle the return button to navigate to MainActivity
        returnButton.setOnClickListener(v -> {
            Intent intent = new Intent(InformationActivity.this, MainActivity.class);
            startActivity(intent);
            finish();  // Close this activity so the user can't come back to it with the back button
        });
    }
}
