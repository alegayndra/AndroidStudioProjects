package com.example.databases.provider;

import com.example.databases.MyDBHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.text.TextUtils;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.content.UriMatcher;

public class MyContentProvider extends ContentProvider {

    private MyDBHandler myDB;

    private static final String AUTHORITY = "com.example.databases.provider.MyContentProvider";
    private static final String PRODUCTS_TABLE = "products";

    private static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PRODUCTS_TABLE);

    public static final int PRODUCTS = 1;
    public static final int PRODUCTS_ID = 2;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, PRODUCTS_TABLE, PRODUCTS);
        sURIMatcher.addURI(AUTHORITY, PRODUCTS_TABLE + "/#", PRODUCTS_ID);
    }

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        // throw new UnsupportedOperationException("Not yet implemented");

        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = myDB.getWritableDatabase();

        int rowsDeleted = 0;

        switch (uriType) {
            case PRODUCTS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(MyDBHandler.TABLE_PRODUCTS, MyDBHandler.COLUMN_ID + "=" + id, null);
                } else {
                    rowsDeleted = sqlDB.delete(MyDBHandler.TABLE_PRODUCTS, MyDBHandler.COLUMN_ID + "=" + id + " and " + selection, selectionArgs);
                }
                break;
            case PRODUCTS:
                rowsDeleted = sqlDB.delete(MyDBHandler.TABLE_PRODUCTS, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        // TOODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TOODO: Implement this to handle requests to insert a new row.
        // throw new UnsupportedOperationException("Not yet implemented");

        int uriType = sURIMatcher.match(uri);

        SQLiteDatabase sqlDB = myDB.getWritableDatabase();

        long id = 0;

        switch (uriType) {
            case PRODUCTS:
                id = sqlDB.insert(MyDBHandler.TABLE_PRODUCTS, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        return Uri.parse(PRODUCTS_TABLE + "/" + id);
    }

    @Override
    public boolean onCreate() {
        // TODOO: Implement this to initialize your content provider on startup.

        myDB = new MyDBHandler(getContext(), null, null, 1);

        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // TOODO: Implement this to handle query requests from clients.
        // throw new UnsupportedOperationException("Not yet implemented");

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(MyDBHandler.TABLE_PRODUCTS);

        int uriType = sURIMatcher.match(uri);

        switch (uriType) {
            case PRODUCTS_ID:
                queryBuilder.appendWhere(MyDBHandler.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;
            case PRODUCTS:
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        Cursor cursor = queryBuilder.query(myDB.getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // TOODO: Implement this to handle requests to update one or more rows.
        // throw new UnsupportedOperationException("Not yet implemented");

        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = myDB.getWritableDatabase();

        int rowsUpdated = 0;

        switch (uriType) {
            case PRODUCTS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(MyDBHandler.TABLE_PRODUCTS, values, MyDBHandler.COLUMN_ID + "=" + id, null);
                } else {
                    rowsUpdated = sqlDB.update(MyDBHandler.TABLE_PRODUCTS, values, MyDBHandler.COLUMN_ID + "=" + id + " and " + selection, selectionArgs);
                }
                break;
            case PRODUCTS:
                rowsUpdated = sqlDB.update(MyDBHandler.TABLE_PRODUCTS, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return rowsUpdated;
    }
}
