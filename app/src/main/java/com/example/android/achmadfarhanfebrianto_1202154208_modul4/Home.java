package com.example.android.achmadfarhanfebrianto_1202154208_modul4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("AsyncTask");//memunculkan Title AsyncTask pada Home
    }
    //method saat button ditekan
    public void mahasiswa(View view) {
        //untuk berpindah ke mainactivity//
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    //method saat button ditekan
    public void gambar(View view) {
        //untuk berpindah ke AsyncTaskGambar//
        Intent intent = new Intent(this, AsyncTaskGambar.class);
        startActivity(intent);
    }
}
