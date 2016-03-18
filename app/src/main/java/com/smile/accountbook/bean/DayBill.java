package com.smile.accountbook.bean;

/**一天整个消费情况
 * Created by 27270 on 2016/1/28.
 */
public class DayBill {
    private int id;
    private String year;
    private String day;
    private String month;
    private String money;
    public static final String TABLE_NAME="tb_daybill";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_YEAR="dayYear";
    public static final String COLUMN_MONTH="dayMonth";
    public static final String COLUMN_DAY="dayDay";
    public static final String COLUMN_MONEY="dayMoney";

    public DayBill() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "DayBill{" +
                "id=" + id +
                ", year='" + year + '\'' +
                ", day='" + day + '\'' +
                ", month='" + month + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}
