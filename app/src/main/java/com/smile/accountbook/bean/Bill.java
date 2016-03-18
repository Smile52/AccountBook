package com.smile.accountbook.bean;

import java.net.PortUnreachableException;

/**账单实体类
 * Created by 27270 on 2016/1/28.
 */
public class Bill {
    private int id;
    private String detail;
    private String time;
    private String money;
    private String day;
    public static final String TABLE_NAME="tb_bill";
    public static final String COLUMN_BILLID="_id";
    public static final String COLUMN_BILLDETAIL="billDetail";
    public static final String COLUMN_BILLTIME="billTime";
    public static final String COLUMN_BILLMONEY="billMoney";
    public static final String COLUMN_BILLDAY="billDay";
    public Bill() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
