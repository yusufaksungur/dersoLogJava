package com.example.dersolog;


import android.content.ContentValues; // Veritabanına veri eklemek için kullanılan bir sınıf.
import android.content.Context; // Uygulama içeriğini temsil eden sınıf.
import android.database.Cursor; // Veritabanı sorgularının sonuçlarını işlemek için kullanılan sınıf.
import android.database.sqlite.SQLiteDatabase; // Veritabanı işlemleri için kullanılan sınıf.
import android.database.sqlite.SQLiteOpenHelper; // SQLite veritabanı işlemlerini kolaylaştırmak için kullanılan sınıf.

import java.util.ArrayList; // Notları saklamak için kullanılan dinamik liste sınıfı.

// Veritabanı yönetimi için bir sınıf tanımlanır ve SQLiteOpenHelper sınıfından türetilir.
public class DatabaseHelper extends SQLiteOpenHelper {

    // Veritabanı ve tabloyla ilgili sabitler tanımlanır.
    private static final String DATABASE_NAME = "notes.db"; // Veritabanı adı.
    private static final String TABLE_NAME = "notes"; // Tablo adı.
    private static final String COL_1 = "ID"; // ID sütunu.
    private static final String COL_2 = "NOTE"; // NOTE sütunu.

    // Yapıcı metot, veritabanı oluşturulurken veya açılırken çağrılır.
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1); // Veritabanı adı ve versiyonu belirtilir.
    }

    // Tablo oluşturma işlemi yapılır. Bu metot veritabanı ilk kez oluşturulduğunda çağrılır.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOTE TEXT)");
        // NOTLAR tablosu oluşturulur. ID otomatik artan birincil anahtar, NOTE metin sütunudur.
    }

    // Veritabanı yükseltme işlemi yapılır. Örneğin, tablo yapısı değiştirildiğinde kullanılır.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); // Mevcut tablo silinir.
        onCreate(db); // Yeni tablo oluşturulur.
    }

    // Yeni bir not eklemek için kullanılan metot.
    public boolean insertData(String note) {
        SQLiteDatabase db = this.getWritableDatabase(); // Veritabanı yazılabilir moda alınır.
        ContentValues contentValues = new ContentValues(); // Veri eklemek için bir ContentValues nesnesi oluşturulur.
        contentValues.put(COL_2, note); // NOTE sütununa değer atanır.
        long result = db.insert(TABLE_NAME, null, contentValues); // Veritabanına ekleme işlemi yapılır.
        return result != -1; // Başarılıysa true, aksi halde false döner.
    }

    // Tüm notları getiren metot.
    public ArrayList<String> getAllNotes() {
        ArrayList<String> notesList = new ArrayList<>(); // Notları saklamak için bir liste oluşturulur.
        SQLiteDatabase db = this.getReadableDatabase(); // Veritabanı okunabilir moda alınır.
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null); // Tüm tabloyu seçen bir sorgu çalıştırılır.

        if (cursor.moveToFirst()) { // Sorgunun ilk sonucuna gidilir.
            do {
                notesList.add(cursor.getString(1)); // NOTE sütunundaki değer listeye eklenir.
            } while (cursor.moveToNext()); // Bir sonraki satıra geçilir.
        }
        cursor.close(); // Cursor kapatılır.
        return notesList; // Notlar döndürülür.
    }

    // Tüm notları silen metot.
    public void deleteAllNotes() {
        SQLiteDatabase db = this.getWritableDatabase(); // Veritabanı yazılabilir moda alınır.
        db.execSQL("DELETE FROM " + TABLE_NAME); // Tüm tablo verileri silinir.
    }
}
