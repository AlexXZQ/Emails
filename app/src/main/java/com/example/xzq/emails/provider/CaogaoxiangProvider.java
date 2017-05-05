package com.example.xzq.emails.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.xzq.emails.utils.DButil;

/**
 * Created by Xzq on 2017/4/11.
 */

public class CaogaoxiangProvider extends ContentProvider {

    private DButil util;

    @Override
    public boolean onCreate() {
        util = new DButil(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = util.getReadableDatabase();
        Cursor c = db.query("caogaoxiang", null, selection, selectionArgs, null, null, null);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = util.getWritableDatabase();
        long id = db.insert("caogaoxiang", null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = util.getWritableDatabase();
        db.delete("caogaoxiang", selection, selectionArgs);
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
