package com.drakkens.alerttimer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Calendar;


public class MainActivity extends Activity {
    String timeSelected;
    String dateSelected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main_activity);

        Button button = findViewById(R.id.button);
        Button time = findViewById(R.id.time);
        Button date = findViewById(R.id.date);

        button.setOnClickListener(v -> {
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Alert")
                    .setMessage("Click Ok to Continue, Cancel to Stop")
                    .setPositiveButton("Ok", (dialog, which) -> Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show())
                    .setNegativeButton("Cancel", (dialog, which) -> Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show())
                    .create();
            alertDialog.show();
        });

        time.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
                this.timeSelected = hourOfDay + ":" + minute;
                makeToastOnDateSetup();

            }, Calendar.HOUR_OF_DAY, Calendar.MINUTE, true);
            timePickerDialog.show();

        });

        date.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                this.dateSelected = dayOfMonth + "/" + month + "/" + year;
                makeToastOnDateSetup();

            }, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
            datePickerDialog.show();
        });
    }

    private void makeToastOnDateSetup() {
        if (timeSelected != null && dateSelected != null) {
            Toast.makeText(this, timeSelected + " " + dateSelected, Toast.LENGTH_SHORT).show();
        }
    }

}
