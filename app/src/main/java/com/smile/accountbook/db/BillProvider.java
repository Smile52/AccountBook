package com.smile.accountbook.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.smile.accountbook.bean.Bill;

import java.lang.ref.PhantomReference;

/**对单个账单数据库的操作
 * Created by 27270 on 2016/1/29.
 */
public class BillProvider extends ContentProvider {
    private static final String AUTHORITY="com.smile.accountbook.provider.BillProvider";
    public static final Uri URI_BILL=Uri.parse("content://"+AUTHORITY+"/bill");
    private static UriMatcher matcher;
    private static final int BILL_ALL=0;
    private static final int BILL_ONE=1;

    private DbOpenHelper mHelper;
    private SQLiteDatabase mdb;
    static {
        matcher=new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY,"bill",BILL_ALL);
        matcher.addURI(AUTHORITY,"bill/#",BILL_ONE);
    }
    @Override
    public boolean onCreate() {
        mHelper=DbOpenHelper.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int match=matcher.match(uri);
        switch (match){
            case BILL_ALL:
                break;
            case BILL_ONE:
                long id= ContentUris.parseId(uri);
                selection="_id=?";
                selectionArgs=new String[]{String.valueOf(id)};
                break;
            default:
                throw new IllegalArgumentException("Wrong URI"+ uri);

        }
        mdb=mHelper.getReadableDatabase();
        Cursor cursor=mdb.rawQuery("SELECT * FROM tb_bill ORDER BY _id DESC",null);
        cursor.setNotificationUri(getContext().getContentResolver(),URI_BILL);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match=matcher.match(uri);
        if(match!=BILL_ALL){
            throw new IllegalArgumentException("Wrong URI"+ uri);
        }
        mdb=mHelper.getWritableDatabase();
        long rowId=mdb.insert(Bill.TABLE_NAME, null, values);
        if(rowId>0){
            notifyDataSetChanged();
            return ContentUris.withAppendedId(uri,rowId);
        }
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
    private void notifyDataSetChanged() {
        getContext().getContentResolver().notifyChange(URI_BILL,null);
    }
}
