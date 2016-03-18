package com.smile.accountbook.db;

import android.util.Log;

import com.smile.accountbook.activity.MainActivity;
import com.smile.accountbook.bean.DayBill;

import junit.framework.TestCase;

/**
 * Created by 27270 on 2016/2/17.
 */
public class DBDayDaoTest extends TestCase {

    public void testInsert() throws Exception {

    }

    public void testUpdate() throws Exception {
        DBDayDao dao=new DBDayDao();
        DayBill bill=new DayBill();
        bill.setId(1);
        bill.setYear(2015);
        bill.setMonth(01);
        bill.setDay(26);
        bill.setMoney(56);
        dao.update(bill);
    }
}