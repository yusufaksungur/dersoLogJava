package com.example.dersolog;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrtalamaLise extends AppCompatActivity {
    EditText edt1, edt2, edt3, edt4, edt5;
    TextView textsonuc;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ortalamalise);

        //Notları tanımlama
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        edt3 = findViewById(R.id.edt3);
        edt4 = findViewById(R.id.edt4);
        edt5 = findViewById(R.id.edt5);
        textsonuc = findViewById(R.id.textsonuc);
    }

    @SuppressLint("WrongViewCast")
    public void hesapla(View view) {
        int toplam = 0; // Girilen değerlerin toplamını tutacak değişken.
        int sayac = 0;  // Girilen değer sayısını takip edecek değişken.

        // Eğer veriler boş değilse, içindeki değeri topla ve sayacı artır.isEmpty()
        if (!edt1.getText().toString().isEmpty()) {
            toplam += Integer.parseInt(edt1.getText().toString());
            sayac++;
        }
        if (!edt2.getText().toString().isEmpty()) {
            toplam += Integer.parseInt(edt2.getText().toString());
            sayac++;
        }
        if (!edt3.getText().toString().isEmpty()) {
            toplam += Integer.parseInt(edt3.getText().toString());
            sayac++;
        }
        if (!edt4.getText().toString().isEmpty()) {
            toplam += Integer.parseInt(edt4.getText().toString());
            sayac++;
        }
        if (!edt5.getText().toString().isEmpty()) {
            toplam += Integer.parseInt(edt5.getText().toString());
            sayac++;
        }

        // Eğer veriler boş değilse, içindeki değeri topla ve sayacı artır.
        if (sayac > 0) {
            double sonuc = (double) toplam / sayac; // double ile bölme
            textsonuc.setText(String.format("Ortalama: %.2f", sonuc));
        }
        else {
            // Eğer hiç değer girilmemişse, kullanıcıya bilgi mesajı göster.
            textsonuc.setText("Lütfen en az bir not girin.");
        }
    }
}
