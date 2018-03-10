package com.example.mihai.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToAddress(View view) {

        TextView textView = findViewById(R.id.address);
        String address = "geo:0,0?q=" + textView.getText().toString();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(address));
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    public void makeCall(View view) {

        TextView textView = findViewById(R.id.phone_number);
        String phoneNumber = "tel:" + textView.getText().toString();

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(phoneNumber));
        startActivity(intent);

    }

    public void accessWebSite(View view) {

        TextView textView = findViewById(R.id.websiteLink);
        String url = "http://" + textView.getText().toString();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
