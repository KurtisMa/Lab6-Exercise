package com.example.lab6_exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onStartClicked(View view) {
        MyIntentService.startActionDemo(this,"Service Finished");
//        SharedPreferences preference = getSharedPreferences("LAB6_EXERCISE_PREFERENCES", MODE_PRIVATE);
//        SharedPreferences.Editor editor = preference.edit();
//        TextView nameView = findViewById(R.id.textView);
//        editor.putString("Kurtis Ma", nameView.getText().toString());
//        editor.apply();
    }

    public void onShowNameClicked(View view) {
        SharedPreferences preference = getSharedPreferences("LAB6_EXERCISE_PREFERENCES", MODE_PRIVATE);
        String name = preference.getString("name","Name not ready yet.");
        TextView nameView = findViewById(R.id.textView);
        nameView.setText(name);
    }
}