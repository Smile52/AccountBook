package com.smile.accountbook.activity;

import android.content.ContentValues;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.smile.accountbook.bean.Bill;
import com.smile.accountbook.bean.DayBill;
import com.smile.accountbook.db.BillProvider;
import com.smile.accountbook.db.DBDayDao;
import com.smile.accountbook.view.SelectTimeDialog;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.Calendar;

import accountbook.smile.com.accountbook.R;

/**
 * 添加账单信息activity
 */
public class AddBillActivity extends AutoLayoutActivity implements View.OnClickListener,SelectTimeDialog.getSelectTimeListener{
    private EditText et_content,et_time,et_money;
    private String mTheme_str,mTime_str;//账单消费信息
    private Double mMoney_str;
    private TextView mChange_tv;//时间修改
    private Button btn_Submint;//确定按钮
    private String mDay;
    public static  AddBillActivity addBillIntances;
    private Toolbar titleBar;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minut;
    private String time_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);
        addBillIntances=this;
        getTime();
        initViews();

    }

    /**
     * 初始化控件
     */
    private void initViews() {
        et_content= (EditText) findViewById(R.id.id_et_theme);
        et_money= (EditText) findViewById(R.id.id_et_money);
        et_time= (EditText) findViewById(R.id.id_et_time);
        btn_Submint= (Button) findViewById(R.id.id_btn_submint);
        mChange_tv= (TextView) findViewById(R.id.id_tv_change);
        mChange_tv.setOnClickListener(this);
        btn_Submint.setOnClickListener(this);
        titleBar= (Toolbar) findViewById(R.id.id_toolbar);
        titleBar.setTitle("添加新账单");
        et_time.setText(time_str);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(0, R.anim.push_up_out);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_btn_submint:
                getEditText();
                ContentValues values=new ContentValues();
                values.put(Bill.COLUMN_BILLDETAIL,mTheme_str);
                values.put(Bill.COLUMN_BILLMONEY,mMoney_str);
                values.put(Bill.COLUMN_BILLTIME,mTime_str);
                values.put(Bill.COLUMN_BILLDAY, mDay);
                getApplicationContext().getContentResolver().insert(BillProvider.URI_BILL, values);
                DBDayDao dayDao=new DBDayDao(getApplicationContext());
                DayBill dayBill=new DayBill();
                dayBill.setYear("" + year);
                dayBill.setMonth("" + month);
                dayBill.setMoney("" + mMoney_str);
                dayBill.setDay("" + day);
                double daymoney=dayDao.query(dayBill);
                Log.i("dandy","我擦"+daymoney);
                if(daymoney>0.0){
                    dayBill.setMoney(""+daymoney+mMoney_str);
                    dayDao.update(dayBill);
                }else if(daymoney==0.0){
                    dayDao.insert(dayBill);
                }

                //dayDao.query(dayBill);
                break;
            case R.id.id_tv_change:
                SelectTimeDialog selectTimeDialog=new SelectTimeDialog();
                selectTimeDialog.show(getSupportFragmentManager(), "selectTime");

                break;
            default:
                break;
        }
    }

    /**
     * 获取文本编辑框的内容
     */
    private void getEditText(){
        mTheme_str=et_content.getText().toString();
        mMoney_str=Double.parseDouble(et_money.getText().toString());
        mTime_str=et_time.getText().toString();
    }

    /**
     *
     * @param date
     * @param time
     */
    @Override
    public void getSlectTime(String date, String time) {
        mTime_str=""+date+" "+time;
        mDay=date;
        et_time.setText(mTime_str);
        Log.i("dandy", "www" + mTime_str);
    }

    /**
     * 获取当前时间
     */
    private void getTime(){
        Calendar ca = Calendar.getInstance();
        year = ca.get(Calendar.YEAR);
        month = ca.get(Calendar.MONTH)+1;
        day = ca.get(Calendar.DAY_OF_MONTH)+1;
        Log.i("dandy","天"+day);
        hour=ca.get(Calendar.HOUR_OF_DAY);//24小时制
        minut=ca.get(Calendar.MINUTE);
        time_str=""+year+" "+month+"."+day+"  "+hour+":"+minut;
        Log.i("dandy","shijian"+time_str);
    }
}
