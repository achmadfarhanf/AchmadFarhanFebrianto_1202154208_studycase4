package com.example.android.achmadfarhanfebrianto_1202154208_modul4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listMhswa; //mendeklarasikan variabel yang dibutuhkan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("AsyncTask"); //set title pada tampilan layar
        listMhswa = (ListView) findViewById(R.id.listmahasiswa); //memanggil atribut yang ada di layout
    }

    //method saat button ditekan
    public void mulai(View view) {
        new getData(listMhswa).execute(); //proses asynctask dimulai
    }

    //subclass assynctask
    class getData extends AsyncTask<String, Integer, String> {
        ListView listMhswa;
        ArrayAdapter adapter;
        ArrayList<String> listNama;
        ProgressDialog dialog;

        public getData(ListView listMhswa) {
            this.listMhswa = listMhswa;
            dialog = new ProgressDialog(MainActivity.this);
            listNama = new ArrayList<>();
        }

        //method ketika proses asynctask belum dimulai
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //menampilkan proses dialog
            dialog.setTitle("Loading Data"); //Setting Title
            dialog.setIndeterminate(true);
            dialog.setProgress(0); //Progress dari 0
            dialog.setMax(100);    //Maksimal Progress 100
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setCancelable(true);
            //Mengcancel process
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialog.dismiss();
                    getData.this.cancel(true);
                }
            });
            //memunculkaan dialog show
            dialog.show();
        }
        //method untuk memasukan data pada list
        @Override
        protected String doInBackground(String... strings) {
            adapter = new ArrayAdapter<>
                    (MainActivity.this, android.R.layout.simple_list_item_1, listNama); //membuat adapter

            //menyimpan array pada sebuah variabel
            String[] mhs = getResources().getStringArray(R.array.namamahasiswa);
            //perulangan ketika memulai array
            for (int a = 0; a < mhs.length; a++) {
                final long persen = 100L * a / mhs.length;
                final String nama = mhs[a];
                try {
                    Runnable change = new Runnable() {
                        @Override
                        public void run() {
                            //Dialog pada message
                            dialog.setMessage((int) persen + "% Add " + nama);
                        }
                    };
                    runOnUiThread(change);
                    Thread.sleep(300);
                    listNama.add(mhs[a]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        //Method saat sudah dilakukan stream akan menjalankan proses selanjutnya yaitu postExecute
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            listMhswa.setAdapter(adapter);
            //Nonaktifkan dialog
            dialog.dismiss();

        }
    }
}