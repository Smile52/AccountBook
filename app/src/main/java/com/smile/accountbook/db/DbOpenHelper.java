package com.smile.accountbook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.smile.accountbook.bean.Bill;

/**单笔消费存储
 * Created by 27270 on 2016/1/28.
 */
public class DbOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="bill.db";
    private static final int DB_VERSION=1;
    private DbOpenHelper(Context context) {
        super(context.getApplicationContext(), DB_NAME, null, DB_VERSION);
    }
    private static DbOpenHelper dbOpenHelper;
    public static DbOpenHelper getInstance(Context context){
        if(dbOpenHelper==null){
            synchronized (DbOpenHelper.class){
                if(dbOpenHelper==null){
                    dbOpenHelper=new DbOpenHelper(context);
                }
            }
        }
        return dbOpenHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE "+ Bill.TABLE_NAME+"(" +
                    Bill.COLUMN_BILLID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                    Bill.COLUMN_BILLDETAIL + " TEXT ,"+
                    Bill.COLUMN_BILLMONEY + " TEXT ,"+
                    Bill.COLUMN_BILLTIME + " TEXT ,"+
                    Bill.COLUMN_BILLDAY + " TEXT"+")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
