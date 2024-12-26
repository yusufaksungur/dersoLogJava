package com.example.dersolog;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Notdefteri extends AppCompatActivity {

    private EditText noteEditText;
    private Button saveButton, deleteButton; // Sil butonu eklendi
    private ListView noteListView;
    private DatabaseHelper databaseHelper;
    private ArrayList<String> notesList;
    private ArrayAdapter<String> adapter;
    private int selectedNoteIndex = -1; // Seçilen notun indeksi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notdefteri);

        // View bileşenlerini tanımlama
        noteEditText = findViewById(R.id.noteEditText);
        saveButton = findViewById(R.id.saveButton);
        deleteButton = findViewById(R.id.deleteButton); // Sil butonu
        noteListView = findViewById(R.id.noteListView);

        // Veritabanı ve liste oluşturma
        databaseHelper = new DatabaseHelper(this);
        notesList = databaseHelper.getAllNotes(); // Tüm notları veritabanından al
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        noteListView.setAdapter(adapter);

        // Kaydet butonuna tıklama olayı
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note = noteEditText.getText().toString().trim();
                if (!note.isEmpty()) {
                    if (selectedNoteIndex == -1) {
                        // Yeni not ekleme
                        if (databaseHelper.insertData(note)) {
                            notesList.add(note); // Notu listeye ekleme
                            Toast.makeText(Notdefteri.this, "Not kaydedildi!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Notdefteri.this, "Not kaydedilemedi!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Seçilen notu güncelleme
                        notesList.set(selectedNoteIndex, note); // Listeyi güncelle
                        databaseHelper.deleteAllNotes(); // Tüm notları sil
                        for (String n : notesList) {
                            databaseHelper.insertData(n); // Güncellenmiş notları tekrar ekle
                        }
                        Toast.makeText(Notdefteri.this, "Not güncellendi!", Toast.LENGTH_SHORT).show();
                    }
                    adapter.notifyDataSetChanged(); // Listeyi güncelleme
                    noteEditText.setText(""); // EditText'i temizleme
                    selectedNoteIndex = -1; // Seçimi sıfırlama
                } else {
                    Toast.makeText(Notdefteri.this, "Not boş olamaz!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Notlara tıklama olayı
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedNoteIndex = position; // Seçilen notun indeksini al
                noteEditText.setText(notesList.get(position)); // Notu EditText'e yerleştir
            }
        });

        // Sil butonuna tıklama olayı
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedNoteIndex != -1) {
                    String noteToDelete = notesList.get(selectedNoteIndex);
                    notesList.remove(selectedNoteIndex); // Listeden notu kaldır
                    databaseHelper.deleteAllNotes(); // Tüm notları sil
                    for (String n : notesList) {
                        databaseHelper.insertData(n); // Kalan notları tekrar ekle
                    }
                    adapter.notifyDataSetChanged(); // Listeyi güncelle
                    Toast.makeText(Notdefteri.this, "Not silindi: " + noteToDelete, Toast.LENGTH_SHORT).show();
                    noteEditText.setText(""); // EditText'i temizleme
                    selectedNoteIndex = -1; // Seçimi sıfırlama
                } else {
                    Toast.makeText(Notdefteri.this, "Silinecek not seçilmedi!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
