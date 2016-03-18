package com.smile.accountbook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.smile.accountbook.bean.DayBill;

/**一天消费总额存储
 * Created by 27270 on 2016/2/17.
 */
public class DBDayMoneyOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="daybill.db";
    private static final int DB_VERSION=1;
    private static DBDayMoneyOpenHelper openHelper;
    private DBDayMoneyOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * 单例模式
     * @param context
     * @return
     */
    public static DBDayMoneyOpenHelper getInstance(Context context){
        if(openHelper==null){
            synchronized (DBDayMoneyOpenHelper.class){
                if(openHelper==null){
                    openHelper=new DBDayMoneyOpenHelper(context);
                }
            }
        }
        return openHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE "+ DayBill.TABLE_NAME+"(" +
                DayBill.COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                DayBill.COLUMN_YEAR +" TEXT ," +
                DayBill.COLUMN_MONTH +" TEXT ," +
                DayBill.COLUMN_DAY +" TEXT ," +
                DayBill.COLUMN_MONEY +" TEXT"+")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
