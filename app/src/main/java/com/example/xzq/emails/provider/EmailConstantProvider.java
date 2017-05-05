package com.example.xzq.emails.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.xzq.emails.utils.DButil;

/**
 * Created by Xzq on 2017/4/11.
 */

public class EmailConstantProvider extends ContentProvider {

    private DButil util;

    @Override
    public boolean onCreate() {
        util = new DButil(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, @Nullable String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = util.getReadableDatabase();
        Cursor c = db.query("email", null, selection, selectionArgs, null, null, null);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = util.getWritableDatabase();
        long id = db.insert("email", null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = util.getWritableDatabase();
        db.delete("email", selection, selectionArgs);
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = util.getWritableDatabase();
        db.update("email", values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return 0;
    }
}
