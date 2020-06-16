package al.tareas.tareadb;


import android.app.Presentation;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "notaDB.db";
    public static final String TABLE_NOTAS = "notas";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOTA = "nota";
    public static final String COLUMN_FECHA = "fecha";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_NOTAS + " ("
                + COLUMN_ID     + " INTEGER PRIMARY KEY,"
                + COLUMN_NOTA   + " TEXT,"
                + COLUMN_FECHA  + " INTEGER"
                + ")";
        db.execSQL(CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTAS);
        onCreate(db);
    }

    public void addNota(Nota product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTA, product.getNota());
        values.put(COLUMN_FECHA, product.getFecha());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_NOTAS, null, values);
        db.close();
    }

    public Nota findNota() {
        String query = "SELECT * FROM " + TABLE_NOTAS;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Nota nota = new Nota();

        if (cursor.moveToFirst()) {
            nota.setID(Integer.parseInt(cursor.getString(0)));
            nota.setNota(cursor.getString(1));
            nota.setFecha(Long.parseLong(cursor.getString(2)));
        } else {
            nota = null;
        }

        cursor.close();
        db.close();

        return nota;
    }

    public void updateNota(Nota nota) {
        String query = "UPDATE " + TABLE_NOTAS
                + " SET "   + COLUMN_NOTA   + " = " +  "\"" + nota.getNota() + "\", "
                            + COLUMN_FECHA  + " = " + nota.getFecha()
                + " WHERE " + COLUMN_ID     + " = " + 1;

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
        db.close();

    }

    public boolean deleteNota() {
        boolean found = false;
        String query = "SELECT * FROM " + TABLE_NOTAS;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            db.delete(TABLE_NOTAS, null, null);
            found = true;
        }

        cursor.close();
        db.close();

        return found;
    }
}
