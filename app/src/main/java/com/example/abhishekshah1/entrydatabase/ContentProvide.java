package com.example.abhishekshah1.entrydatabase;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by Abhishek.Shah1 on 7/7/2015.
 */
public class ContentProvide extends ContentProvider {
    private UriMatcher sUriMatcher;
    private static final int All_NAME = 1;
    private static final int SINGLE_NAME = 2;

    public static final String AUTHORITY = "com.example.abhishekshah1.entrydatabase.provider";
    private DataBase dbHelper;
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY+ "/STUDENT");

    public ContentProvide() {
    }

   public ContentProvide(Context context) {
       dbHelper = new DataBase(context);
   }

    @Override
    public boolean onCreate() {
        dbHelper = new DataBase(getContext());
        return false;
    }

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "STUDENT",All_NAME);
        uriMatcher.addURI(AUTHORITY, "STUDENT/#",SINGLE_NAME);
    }
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

            SQLiteDatabase db = dbHelper.getReadableDatabase();
            SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
            queryBuilder.setTables(DataBase.TABLE_NAME);
        switch (uriMatcher.match(uri)) {
            case All_NAME:
                //do nothing
                break;
            case SINGLE_NAME:
                String id = uri.getPathSegments().get(1);
                queryBuilder.appendWhere(DataBase.KEY_ROWID + "=" + id);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        int code = uriMatcher.match(uri);
        switch (code) {
            case All_NAME:
                return "vnd.android.cursor.dir/vnd.com.example.abhishekshah1.entrydatabase.provider.STUDENT";
            case SINGLE_NAME:
                return "vnd.android.cursor.item/vnd.com.example.abhishekshah1.entrydatabase.provider.STUDENT";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
