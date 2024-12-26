package com.example.dersolog;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrtalamaUniDersOrt extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ortalamauniders);
    }

    public void hesapla(View view) {
        EditText vizeInput = findViewById(R.id.vize1);
        EditText vizeYuzde = findViewById(R.id.vizeYuzde);
        EditText finalInput = findViewById(R.id.vize2);
        EditText finalYuzde = findViewById(R.id.finalYuzde);
        EditText quiz1Input = findViewById(R.id.vize3);
        EditText quiz1Yuzde = findViewById(R.id.quiz1Yuzde);
        EditText quiz2Input = findViewById(R.id.quiz2);
        EditText quiz2Yuzde = findViewById(R.id.quiz2Yuzde);
        EditText odev1Input = findViewById(R.id.odev1);
        EditText odev1Yuzde = findViewById(R.id.odev1Yuzde);
        EditText odev2Input = findViewById(R.id.odev2);
        EditText odev2Yuzde = findViewById(R.id.odev2Yuzde);
        TextView sonucTextView = findViewById(R.id.sonucTextView);

        // Notların alınması
        double vize = Double.parseDouble(vizeInput.getText().toString());
        double vizeYuzdeVal = Double.parseDouble(vizeYuzde.getText().toString());
        double finalNotu = Double.parseDouble(finalInput.getText().toString());
        double finalYuzdeVal = Double.parseDouble(finalYuzde.getText().toString());
        double quiz1 = !quiz1Input.getText().toString().isEmpty() ? Double.parseDouble(quiz1Input.getText().toString()) : 0;
        double quiz1YuzdeVal = !quiz1Yuzde.getText().toString().isEmpty() ? Double.parseDouble(quiz1Yuzde.getText().toString()) : 0;
        double quiz2 = !quiz2Input.getText().toString().isEmpty() ? Double.parseDouble(quiz2Input.getText().toString()) : 0;
        double quiz2YuzdeVal = !quiz2Yuzde.getText().toString().isEmpty() ? Double.parseDouble(quiz2Yuzde.getText().toString()) : 0;
        double odev1 = !odev1Input.getText().toString().isEmpty() ? Double.parseDouble(odev1Input.getText().toString()) : 0;
        double odev1YuzdeVal = !odev1Yuzde.getText().toString().isEmpty() ? Double.parseDouble(odev1Yuzde.getText().toString()) : 0;
        double odev2 = !odev2Input.getText().toString().isEmpty() ? Double.parseDouble(odev2Input.getText().toString()) : 0;
        double odev2YuzdeVal = !odev2Yuzde.getText().toString().isEmpty() ? Double.parseDouble(odev2Yuzde.getText().toString()) : 0;

        // Yüzde toplamı hesaplama
        double toplamYuzde = vizeYuzdeVal + finalYuzdeVal + quiz1YuzdeVal + quiz2YuzdeVal + odev1YuzdeVal + odev2YuzdeVal;

        // Geçerli Yüzde Kontrolü
        if (toplamYuzde != 100) {
            Toast.makeText(this, "Yüzde toplamı 100 olmalıdır.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Notların 100'den büyük olup olmadığını kontrol et
        if (vize > 100 || finalNotu > 100 || quiz1 > 100 || quiz2 > 100 || odev1 > 100 || odev2 > 100) {
            Toast.makeText(this, "Notlar 100'den fazla olamaz.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Puan Hesaplama
        double toplamPuan = (vize * (vizeYuzdeVal / 100)) + (finalNotu * (finalYuzdeVal / 100)) +
                (quiz1 * (quiz1YuzdeVal / 100)) +
                (quiz2 * (quiz2YuzdeVal / 100)) +
                (odev1 * (odev1YuzdeVal / 100)) +
                (odev2 * (odev2YuzdeVal / 100));

        // Harf Notu Hesaplama
        String harfNotu;
        if (toplamPuan >= 90) {
            harfNotu = "A";
        } else if (toplamPuan >= 80) {
            harfNotu = "B";
        } else if (toplamPuan >= 70) {
            harfNotu = "C";
        } else if (toplamPuan >= 60) {
            harfNotu = "D";
        } else {
            harfNotu = "F";
        }

        sonucTextView.setText("Sonuç: " + toplamPuan + " - Harf Notu: " + harfNotu);
    }
}
