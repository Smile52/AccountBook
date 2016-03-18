package com.smile.accountbook.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.smile.accountbook.activity.AddBillActivity;
import com.smile.accountbook.fragment.SecondFragment;

import java.util.Calendar;

import accountbook.smile.com.accountbook.R;

/**选择时间对话框
 * Created by 27270 on 2016/1/27.
 */
public class SelectTimeDialog extends android.support.v4.app.DialogFragment {
    private DatePicker datePicker;
    private TimePicker timePicker;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minut;
    //获取到的最终时间
    private String date_str;
    private String time_str;
    //定义一个接口，实现数据回传
    public interface getSelectTimeListener{
        void getSlectTime(String date,String time);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.timedialog, null);
        getTime(view);
        datePicker= (DatePicker) view.findViewById(R.id.id_datepicker);
        timePicker= (TimePicker) view.findViewById(R.id.id_timepicker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Log.i("dandy","小时"+hourOfDay+":"+minute);
                time_str=""+hourOfDay+":"+minute;
                Log.i("dandy","eee"+time_str);
            }
        });
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date_str=""+year+" "+monthOfYear+1 +"."+dayOfMonth;
                Log.i("dandy","我执行了");
            }
        });


        builder.setView(view).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //实现这个接口
                getSelectTimeListener listener= AddBillActivity.addBillIntances;
                //Log.i("dandy","选择后的时间"+date_str+"   "+time_str);
                listener.getSlectTime(date_str, time_str);

            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }

    /**
     * 获取选择的时间
     * @param view
     */
    private void getTime(View view) {
        datePicker= (DatePicker) view.findViewById(R.id.id_datepicker);
        timePicker= (TimePicker) view.findViewById(R.id.id_timepicker);
        //获取当前日期
        Calendar ca = Calendar.getInstance();
        year = ca.get(Calendar.YEAR);
        month = ca.get(Calendar.MONTH)+1;
        day = ca.get(Calendar.DAY_OF_MONTH);
        Log.i("dandy","天"+day);
        hour=ca.get(Calendar.HOUR_OF_DAY);//24小时制
        minut=ca.get(Calendar.MINUTE);
        date_str=""+year+" "+month+"."+day;
        time_str=""+hour+":"+minut;
        //Log.i("dandy","默认"+hour+minut);
        //Log.i("dandy","默认日期"+year+month+day);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Log.i("dandy","小时"+hourOfDay+":"+minute);
                time_str=""+hourOfDay+":"+minute;
                Log.i("dandy","eee"+time_str);
            }
        });
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date_str=""+year+" "+monthOfYear+1 +"."+dayOfMonth;
                Log.i("dandy","我执行了");
            }
        });

    }
}
