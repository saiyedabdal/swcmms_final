package com.example.smahsanabdal.finalfirstlevel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class mainscreen extends AppCompatActivity {

    public void ProjectPanel (View V)
    {
        Intent i = new Intent(this,projectpanel.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
    }

}
