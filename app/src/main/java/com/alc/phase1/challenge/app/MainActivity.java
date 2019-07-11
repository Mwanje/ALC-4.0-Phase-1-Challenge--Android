package com.alc.phase1.challenge.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtHeader, txtWelcome;
    Button btnAbout, btnProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getLayoutInflater().setFactory(new LayoutInflater.Factory() {

            @Override
            public View onCreateView(String name, Context context,
                                     AttributeSet attrs) {
                View v = Utils.tryInflate(name, context, attrs);
                if (v instanceof TextView) {
                    Utils.setTypeFace((TextView) v, MainActivity.this);
                }
                return v;
            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHeader = (TextView) findViewById(R.id.txtheader);
        txtHeader.setTypeface(Utils.setBoldFont(this));
        txtWelcome = (TextView) findViewById(R.id.txtwelcome);
        txtWelcome.setTypeface(Utils.setBoldFont(this));

        btnAbout = (Button) findViewById(R.id.btnAbout);
        btnAbout.setTypeface(Utils.setBoldFont(this));
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent localIntent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(localIntent);
            }
        });

        btnProfile = (Button)findViewById(R.id.btnMyprofile);
        btnProfile.setTypeface(Utils.setBoldFont(this));
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent localIntent = new Intent(MainActivity.this, MyProfileActivity.class);
                startActivity(localIntent);
            }
        });
    }

}
