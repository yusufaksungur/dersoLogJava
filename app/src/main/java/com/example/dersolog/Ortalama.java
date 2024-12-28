package com.example.dersolog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ortalama extends AppCompatActivity {


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.ortalama);
    }

    /*Burada intentle iki aktiviteyi bir birine bağlıyoruz

      Aynı zamanda AndroidManifest.XML de activity tanımlıyoruz


     */
    public void buttonuni(View view){
        Intent intent = new Intent(Ortalama.this, OrtalamaUniDersOrt.class);
        startActivity(intent);
    }
    public void buttonlise(View view){
        Intent intent = new Intent(Ortalama.this, OrtalamaLise.class);
        startActivity(intent);
    }
}