package com.example.dersolog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class gereklisayfalar extends AppCompatActivity {


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gereklisayfalar);



        ImageView turkcellogo = findViewById(R.id.turkcell);
        setLogoClickListener(turkcellogo, "https://gelecegiyazanlar.turkcell.com.tr/");

        ImageView btkakademilogo = findViewById(R.id.btkakademi);
        setLogoClickListener(btkakademilogo, "https://www.btkakademi.gov.tr/" );

        ImageView udemylogo = findViewById(R.id.udemy);
        setLogoClickListener(udemylogo, "https://www.udemy.com/");

        ImageView khanlogo = findViewById(R.id.khan);
        setLogoClickListener(khanlogo, "https://tr.khanacademy.org/");

        ImageView youtubelogo = findViewById(R.id.youtube);
        setLogoClickListener(youtubelogo, "https://www.youtube.com/");

        ImageView linkedinlogo = findViewById(R.id.linkedin);
        setLogoClickListener(linkedinlogo,"https://www.linkedin.com/in/yusufaksungur/");

        ImageView githublogo = findViewById(R.id.github);
        setLogoClickListener(githublogo,  "https://www.github.com/");

    }

    /*Burada setLogoClickListener adında bir metod tanımlayarak kod tekrarının bir nebzede olsa önüne geçtik
    Verilen urlin doğru çalışması icin intent.ACTION_VIEW ve tarayacı icin Uri.parse kullanıldı.

        AndroidManifest.XML de saydfaların acılabilmesi icin asağıdaki uygulama iznini verdim,

     <uses-permission android:name="android.permission.INTERNET" />

     */

    private void setLogoClickListener(ImageView imageView,final String url) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}