package com.example.dersolog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

// Hesap makinesi uygulamasını temsil eden sınıf.
public class Hesapmakinasi extends AppCompatActivity {

    private EditText inputData; // Kullanıcıdan veri almak ve sonuçları göstermek için EditText.
    private String input = ""; // Kullanıcının girdiği sayı veya işlemleri geçici olarak tutar.
    private String operator = ""; // Seçilen aritmetik işlemi (örneğin "+", "-") tutar.
    private double firstNumber = 0; // İlk girilen sayıyı tutar.
    private boolean isOperatorPressed = false; // Bir operatörün seçilip seçilmediğini takip eder.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Kenardan kenara bir düzen sağlar (sistem çubuğunu şeffaf yapar).
        setContentView(R.layout.activity_hesapmakinasi); // Aktiviteye uygun layout'u bağlar.

        inputData = findViewById(R.id.inputData); // EditText'i XML dosyasından bağlar.

        // Sayı butonlarını temsil eden butonların ID'lerini tutan bir dizi.
        int[] numericButtonIds = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3,
                R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7,
                R.id.btn_8, R.id.btn_9
        };

        // Sayı butonlarına tıklama olayını dinlemek için bir döngü.
        for (int id : numericButtonIds) {
            Button button = findViewById(id); // Her butonu ID'sine göre bulur.
            button.setOnClickListener(this::onNumericButtonClick); // Butonun tıklama olayını tanımlar.
        }

        // İşlem butonlarını temsil eden butonların ID'lerini tutan bir dizi.
        int[] operatorButtonIds = {
                R.id.btn_plus, R.id.btn_minus, R.id.btn_multiply, R.id.btn_divide
        };

        // İşlem butonlarına tıklama olayını dinlemek için bir döngü.
        for (int id : operatorButtonIds) {
            Button button = findViewById(id); // Her butonu ID'sine göre bulur.
            button.setOnClickListener(this::onOperatorButtonClick); // Butonun tıklama olayını tanımlar.
        }

        // Temizleme, geri silme, nokta ekleme ve eşittir butonları için tıklama olaylarını ayarlar.
        findViewById(R.id.btn_clear).setOnClickListener(v -> clearInput());
        findViewById(R.id.btn_delete).setOnClickListener(v -> deleteLastCharacter());
        findViewById(R.id.btn_dot).setOnClickListener(v -> addDot());
        findViewById(R.id.btn_equals).setOnClickListener(v -> calculateResult());
    }

    // Sayı butonlarına tıklanıldığında çağrılan metot.
    private void onNumericButtonClick(View view) {
        Button button = (Button) view; // Tıklanan butonu alır.
        if (isOperatorPressed) { // Eğer bir operatör seçildiyse, giriş sıfırlanır.
            input = "";
            isOperatorPressed = false;
        }
        input += button.getText().toString(); // Tıklanan butonun değerini girişe ekler.
        inputData.setText(input); // Girişi ekranda gösterir.
    }

    // İşlem butonlarına tıklanıldığında çağrılan metot.
    private void onOperatorButtonClick(View view) {
        Button button = (Button) view; // Tıklanan butonu alır.
        if (!input.isEmpty()) { // Eğer giriş boş değilse:
            firstNumber = Double.parseDouble(input); // İlk sayıyı kaydeder.
            operator = button.getText().toString(); // Operatörü kaydeder.
            isOperatorPressed = true; // Operatörün seçildiğini işaretler.
        }
    }

    // Eşittir butonuna tıklanıldığında sonucu hesaplayan metot.
    private void calculateResult() {
        if (!input.isEmpty() && !operator.isEmpty()) { // Eğer giriş ve operatör boş değilse:
            double secondNumber = Double.parseDouble(input); // İkinci sayıyı alır.
            double result = 0; // Sonucu saklamak için değişken.

            // Operatöre göre işlem yapar.
            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber; // Toplama.
                    break;
                case "-":
                    result = firstNumber - secondNumber; // Çıkarma.
                    break;
                case "*":
                    result = firstNumber * secondNumber; // Çarpma.
                    break;
                case "/":
                    if (secondNumber != 0) { // Sıfıra bölme kontrolü.
                        result = firstNumber / secondNumber; // Bölme.
                    } else { // Sıfıra bölme hatası.
                        inputData.setText("Error"); // Hata mesajı gösterir.
                        return;
                    }
                    break;
            }

            input = String.valueOf(result); // Sonucu giriş olarak kaydeder.
            inputData.setText(input); // Sonucu ekranda gösterir.
            operator = ""; // Operatörü sıfırlar.
        }
    }

    // Giriş ve operatörü temizleyen metot.
    private void clearInput() {
        input = ""; // Girişi sıfırlar.
        operator = ""; // Operatörü sıfırlar.
        firstNumber = 0; // İlk sayıyı sıfırlar.
        inputData.setText("0"); // Ekrana sıfır yazar.
    }

    // Son karakteri silen metot.
    private void deleteLastCharacter() {
        if (!input.isEmpty()) { // Eğer giriş boş değilse:
            input = input.substring(0, input.length() - 1); // Son karakteri siler.
            inputData.setText(input.isEmpty() ? "0" : input); // Ekranı günceller.
        }
    }

    // Nokta (.) ekleyen metot.
    private void addDot() {
        if (!input.contains(".")) { // Eğer girişte nokta yoksa:
            input += "."; // Noktayı ekler.
            inputData.setText(input); // Ekranı günceller.
        }
    }
}
