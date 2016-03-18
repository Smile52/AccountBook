package com.smile.accountbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smile.accountbook.bean.Bill;

import java.util.List;

import accountbook.smile.com.accountbook.R;

/**
 * Created by 27270 on 2016/1/28.
 */
public class BillAdapter extends BaseAdapter {
    private List<Bill> bills;
    private Context context;
    private LayoutInflater inflater;

    public BillAdapter(List<Bill> bills, Context context) {
        this.bills = bills;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return bills.size();
    }

    @Override
    public Object getItem(int position) {
        return bills.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.listview_item,null);
            viewHolder=new ViewHolder();
            viewHolder.mTheme_tv= (TextView) convertView.findViewById(R.id.id_tv_theme);
            viewHolder.mTime_tv= (TextView) convertView.findViewById(R.id.id_tv_time);
            viewHolder.mMoney_tv= (TextView) convertView.findViewById(R.id.id_tv_money);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Bill bill=bills.get(position);
            viewHolder.mTheme_tv.setText(bill.getDetail());
            viewHolder.mTime_tv.setText(bill.getTime());
            viewHolder.mMoney_tv.setText(""+bill.getMoney());


        return convertView;
    }

    class ViewHolder {
        private TextView mTheme_tv;
        private TextView mTime_tv;
        private TextView mMoney_tv;

    }
}
