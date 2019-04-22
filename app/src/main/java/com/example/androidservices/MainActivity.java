package com.example.androidservices;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btn_web, btn_email, btn_dial, btn_call, btn_text, btn_maps;
    EditText et_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_web = findViewById(R.id.btn_web);
        btn_email = findViewById(R.id.btn_email);
        btn_dial = findViewById(R.id.btn_dial);
        btn_call = findViewById(R.id.btn_call);
        btn_text = findViewById(R.id.btn_text);
        btn_maps = findViewById(R.id.btn_maps);

        et_data = findViewById(R.id.et_data);

        btn_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage(et_data.getText().toString());
            }
        });

        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] addresses = new String [1];
                addresses [0] = et_data.getText().toString();

                composeEmail(addresses, "Hello from Isaiah");
            }
        });

        btn_dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber(et_data.getText().toString());
            }
        });

        btn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeMmsMessage(et_data.getText().toString(), "Hello I would like to talk");
            }
        });

        btn_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mapsQuery = "geo:0,0?q=" + et_data.getText().toString();
                Uri mapuri = Uri.parse(mapsQuery);
                showMap(mapuri);
            }
        });
    }

    private void openWebPage(String url) {
        if (!url.startsWith("http://") || !url.startsWith("https://")) {
            url = "http://" + url;
        }
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("*/*"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void composeMmsMessage(String phoneNumber, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber));
        intent.putExtra("sms_body", message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void showMap (Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            startActivity(intent);
        }
    }
}
