package com.smile.accountbook.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.smile.accountbook.bean.DayBill;

/**将数据库操作封装起来，针对每天统计数据表
 * Created by 27270 on 2016/2/17.
 */
public class DBDayDao {
    private Context mContext;
    private DBDayMoneyOpenHelper openHelper;
    private SQLiteDatabase db;

    public DBDayDao(Context context) {
        this.mContext=context;
        openHelper=DBDayMoneyOpenHelper.getInstance(mContext);
    }

    public DBDayDao() {
        super();
    }

    /**
     * 添加当天的数据
     * @param dayBill
     * @return
     */
    public int insert(DayBill dayBill){

        db=openHelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(""+DayBill.COLUMN_YEAR,dayBill.getYear());
        cv.put(""+DayBill.COLUMN_MONTH,dayBill.getMonth());
        cv.put("" + DayBill.COLUMN_DAY, dayBill.getDay());
        //Log.i("dandy", "存入" + dayBill.getDay());
        //Log.i("dandy","存入的月份"+dayBill.getMonth());
        cv.put("" + DayBill.COLUMN_MONEY, dayBill.getMoney());
        try {
            db.insert(DayBill.TABLE_NAME, null, cv);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    /**
     * 因为每天可能有几个消费账单，所以每天总额账单的金额是要更新
     * @param dayBill
     * @return 返回结果，0为失败，1为更新成功
     */
    public int update(DayBill dayBill){
        db=openHelper.getWritableDatabase();
        String sql="update "+ DayBill.TABLE_NAME +" set "+DayBill.COLUMN_MONEY+"="+dayBill.getMoney()+" where "+DayBill.COLUMN_YEAR+"="+dayBill.getYear()+" and "
                +DayBill.COLUMN_MONTH+"="+dayBill.getMonth()+" and "+DayBill.COLUMN_DAY+"="+dayBill.getDay()+"";
       //System.out.println(sql);
        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public double query(DayBill dayBill) {
        double money = 0;
        Log.i("dandy","ww"+dayBill.toString());
        db=openHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT dayMoney FROM tb_daybill WHERE dayYear=? AND dayMonth=? AND dayDay=?",
                new String[]{"" + dayBill.getYear(), "" + dayBill.getMonth(), "" + dayBill.getDay()});

        while (c.moveToNext()) {
            money = Double.parseDouble(c.getString(0));
            Log.i("dandy", "德玛西亚" + money);



        }
        Log.i("dandy", "返回" + money);
        return money;
    }

}
