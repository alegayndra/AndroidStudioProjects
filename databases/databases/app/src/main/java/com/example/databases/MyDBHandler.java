package com.example.databases;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDB.db";
    public static final String TABLE_PRODUCTS = "products";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "productname";
    public static final String COLUMN_QUANTITY = "quantity";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory,int version ){
        super(context,DATABASE_NAME,factory,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_PRODUCT_TABLE = "CREATE TABLE " +  TABLE_PRODUCTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_PRODUCTNAME + " TEXT,"
                + COLUMN_QUANTITY + " INTEGER" + ")";

        db.execSQL(CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }


    // Este metodo agrega el producto a la tabla products
    public  void addProduct(Product product){

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME,product.getProductName());
        values.put(COLUMN_QUANTITY,product.getQuantity());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_PRODUCTS,null,values);
        db.close();

    }

    public Product findProduct(String productname){

        String query = "SELECT * FROM  " + TABLE_PRODUCTS + " WHERE " +
                COLUMN_PRODUCTNAME  + " = \"" +  productname +  "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query,null);

        Product product = new Product();

        if (cursor.moveToFirst()){

            product.setID(Integer.parseInt(cursor.getString(0)));
            product.setProductName(cursor.getString(1));
            product.setQuantity(Integer.parseInt(cursor.getString(2)));
        }
        else {
            product = null;
        }
        cursor.close();
        db.close();

            return product;
    }

    public boolean deleteProduct(String productname){

        boolean result = false;

        String query = "SELECT * FROM  " + TABLE_PRODUCTS + " WHERE " +
                COLUMN_PRODUCTNAME  + " = \"" +  productname +  "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query,null);

        Product product = new Product();

        if (cursor.moveToFirst()){
            product.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_PRODUCTS,COLUMN_ID  + " = ?", new String[] {String.valueOf(product.getID())});
            result = true;
        }
        cursor.close();

        return result;
    }


}
