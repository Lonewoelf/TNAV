package com.example.tnav;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_layout);

      //  setContentView(R.layout.settings_layout);
//        Button buttonOne = findViewById(R.id.fingerscan);
//        buttonOne.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                System.out.println("Button Clicked");
//
//                Intent activity2Intent = new Intent(LogIn.this, MainActivity.class);
//                startActivity(activity2Intent);
//            }
//        });
    }
}
