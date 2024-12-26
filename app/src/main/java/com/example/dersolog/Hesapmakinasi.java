package com.example.dersolog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Hesapmakinasi extends AppCompatActivity {
    private EditText inputData;
    private String input = "";
    private String operator = "";
    private double firstNumber = 0;
    private boolean isOperatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hesapmakinasi);

        inputData = findViewById(R.id.inputData);

        // Numara butonları
        int[] numericButtonIds = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3,
                R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7,
                R.id.btn_8, R.id.btn_9
        };

        for (int id : numericButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(this::onNumericButtonClick);
        }

        // İşlem butonları
        int[] operatorButtonIds = {
                R.id.btn_plus, R.id.btn_minus, R.id.btn_multiply, R.id.btn_divide
        };

        for (int id : operatorButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(this::onOperatorButtonClick);
        }

        findViewById(R.id.btn_clear).setOnClickListener(v -> clearInput());
        findViewById(R.id.btn_delete).setOnClickListener(v -> deleteLastCharacter());
        findViewById(R.id.btn_dot).setOnClickListener(v -> addDot());
        findViewById(R.id.btn_equals).setOnClickListener(v -> calculateResult());
    }

    private void onNumericButtonClick(View view) {
        Button button = (Button) view;
        if (isOperatorPressed) {
            input = "";
            isOperatorPressed = false;
        }
        input += button.getText().toString();
        inputData.setText(input);
    }

    private void onOperatorButtonClick(View view) {
        Button button = (Button) view;
        if (!input.isEmpty()) {
            firstNumber = Double.parseDouble(input);
            operator = button.getText().toString();
            isOperatorPressed = true;
        }
    }

    private void calculateResult() {
        if (!input.isEmpty() && !operator.isEmpty()) {
            double secondNumber = Double.parseDouble(input);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        inputData.setText("Error");
                        return;
                    }
                    break;
            }

            input = String.valueOf(result);
            inputData.setText(input);
            operator = "";
        }
    }

    private void clearInput() {
        input = "";
        operator = "";
        firstNumber = 0;
        inputData.setText("0");
    }

    private void deleteLastCharacter() {
        if (!input.isEmpty()) {
            input = input.substring(0, input.length() - 1);
            inputData.setText(input.isEmpty() ? "0" : input);
        }
    }

    private void addDot() {
        if (!input.contains(".")) {
            input += ".";
            inputData.setText(input);
        }
    }
}
