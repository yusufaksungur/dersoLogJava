package com.example.dersolog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Anasayfa extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.anasayfa);

    }

    /*Burada intentle iki aktiviteyi bir birine bağlıyoruz

      Aynı zamanda AndroidManifest.XML de activity tanımlıyoruz

     */
    public void buttonNot(View view){
        Intent intent = new Intent(Anasayfa.this, Notdefteri.class);
        startActivity(intent);
    }


    public void buttonOrt(View view){
        Intent intent = new Intent(Anasayfa.this, Ortalama.class);
        startActivity(intent);
    }

    public void buttonSayfa(View view){
        Intent intent = new Intent(Anasayfa.this, gereklisayfalar.class);
        startActivity(intent);

    }

    public void buttonHesap(View view){
        Intent intent = new Intent(Anasayfa.this, Hesapmakinasi.class );
        startActivity(intent);
    }
}
