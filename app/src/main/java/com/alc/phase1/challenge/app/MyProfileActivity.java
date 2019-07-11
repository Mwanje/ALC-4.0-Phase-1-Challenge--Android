package com.alc.phase1.challenge.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

public class MyProfileActivity extends AppCompatActivity {

    TextView txtTracker, txtCountry, txtEmail, txtPhone, txtSlack, txtName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       /* getLayoutInflater().setFactory(new LayoutInflater.Factory() {

            @Override
            public View onCreateView(String name, Context context,
                                     AttributeSet attrs) {
                View v = Utils.tryInflate(name, context, attrs);
                if (v instanceof TextView) {
                    Utils.setTypeFace((TextView) v, MyProfileActivity.this);
                }
                return v;
            }
        });*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);
        init();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME |
                ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_USE_LOGO);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Profile");

    }

    public void init(){
        txtTracker = (TextView) findViewById(R.id.txtTrack);
        txtTracker.setTypeface(Utils.setFont(this));
        txtCountry = (TextView) findViewById(R.id.txtCountry);
        txtCountry.setTypeface(Utils.setFont(this));
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtEmail.setTypeface(Utils.setFont(this));
        txtPhone = (TextView) findViewById(R.id.txtPhone);
        txtPhone.setTypeface(Utils.setFont(this));
        txtSlack = (TextView) findViewById(R.id.txtSlack);
        txtSlack.setTypeface(Utils.setFont(this));
        txtName = (TextView) findViewById(R.id.txtName);
        txtName.setTypeface(Utils.setBoldFont(this));

    }
    @Override
    public void onResume(){
        super.onResume();
        // adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
