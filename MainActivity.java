package com.example.electriccalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etUnits, etRebate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link UI components
        etUnits = findViewById(R.id.et_units);
        etRebate = findViewById(R.id.et_rebate);
        Button btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);
        Button btnInfo = findViewById(R.id.btn_info);

        // Calculate button logic
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBill();
            }
        });

        // About button logic
        btnInfo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InformationActivity.class);
            startActivity(intent);
        });
    }

    private void calculateBill() {
        String unitsStr = etUnits.getText().toString();
        String rebateStr = etRebate.getText().toString();

        if (unitsStr.isEmpty() || rebateStr.isEmpty()) {
            Toast.makeText(this, "Please enter both units and rebate.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double units = Double.parseDouble(unitsStr);
            double rebate = Double.parseDouble(rebateStr);

            if (rebate < 0 || rebate > 5) {
                Toast.makeText(this, "Rebate must be between 0% and 5%.", Toast.LENGTH_SHORT).show();
                return;
            }

            double bill = calculateElectricBill(units);
            double discountedBill = applyRebate(bill, rebate);
            tvResult.setText(String.format("Your Electric Bill: RM %.2f", discountedBill));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numeric values.", Toast.LENGTH_SHORT).show();
        }
    }

    private double calculateElectricBill(double units) {
        double bill = 0;

        if (units <= 200) {
            bill = units * 0.218;
        } else if (units <= 300) {
            bill = (200 * 0.218) + ((units - 200) * 0.334);
        } else if (units <= 600) {
            bill = (200 * 0.218) + (100 * 0.334) + ((units - 300) * 0.516);
        } else {
            bill = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + ((units - 600) * 0.546);
        }

        return bill;
    }

    private double applyRebate(double bill, double rebate) {
        double discount = bill * (rebate / 100);
        return bill - discount;
    }
}
