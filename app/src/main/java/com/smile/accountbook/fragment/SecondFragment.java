package com.smile.accountbook.fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.smile.accountbook.activity.AddBillActivity;
import com.smile.accountbook.bean.Bill;
import com.smile.accountbook.db.BillProvider;
import com.smile.accountbook.view.SelectTimeDialog;

import accountbook.smile.com.accountbook.R;


/**记录账单页面
 * Created by 27270 on 2016/1/20.
 */
public class SecondFragment extends Fragment {
    public static SecondFragment secondIntances;
    private FloatingActionButton actionButton;
   // private DayBillDao dayBillDao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.secondfragment,container,false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        secondIntances=this;
        //initViews(view);
        actionButton= (FloatingActionButton) view.findViewById(R.id.id_open);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddBillActivity.class));
                getActivity().overridePendingTransition(R.anim.push_up_in,0);
            }
        });
    }









}
