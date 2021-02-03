package com.bmuschko.f;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bmuschko.a.LibraryA;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new LibraryA().fromProjectA();
    }
}