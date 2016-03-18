package com.smile.accountbook.fragment;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.smile.accountbook.activity.MainActivity;
import com.smile.accountbook.adapter.BillAdapter;
import com.smile.accountbook.bean.Bill;
import com.smile.accountbook.db.BillProvider;
import com.smile.accountbook.view.ChartView;

import java.util.ArrayList;
import java.util.List;


import accountbook.smile.com.accountbook.R;


/**首页
 * Created by 27270 on 2016/1/20.
 */
public class HomeFragment extends Fragment {
    private  ListView mListview;
    private View inflater;
    private View footView;//listview下面的布局
    private HorizontalScrollView horizontalScrollView;//listview头布局容器
    public static final Uri uri=Uri.parse("content://com.smile.accountbook..bill");
    private CursorAdapter mCurorAdapter;
    private LayoutInflater mInflater;
    private static final int LOADER_ID=1;
    private int phoneWidth,phoneHeight;//屏幕宽高度

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DisplayMetrics metrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        phoneHeight=metrics.heightPixels;
        phoneWidth=metrics.widthPixels;
        Log.i("dandy",""+phoneWidth);
        return inflater.inflate(R.layout.homefragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mInflater=LayoutInflater.from(getContext());
        mListview = (ListView) view.findViewById(R.id.id_listview);
        ChartView chartView = new ChartView(getContext());//折线图
        chartView.setViewInfo(phoneWidth,phoneHeight,null,null,null,null);//给折线图设置相关数据
        inflater = LayoutInflater.from(getContext()).inflate(R.layout.listviewheader, null, false);
        horizontalScrollView = (HorizontalScrollView) inflater.findViewById(R.id.id_hscrollview);
        horizontalScrollView.addView(chartView);//动态添加自定义view进去
        initLoader();
        initAdapter();
        footView = View.inflate(getContext(), R.layout.loading, null);
        mListview.addHeaderView(inflater);
        //mListview.addFooterView(footView);
        mListview.setAdapter(mCurorAdapter);

    }

    //初始化Loader
    private void initLoader() {
        getLoaderManager().initLoader(LOADER_ID, null, new android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor>() {
            @Override
            public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
                android.support.v4.content.CursorLoader loader = new android.support.v4.content.CursorLoader(getActivity(), BillProvider.URI_BILL, null, null, null, null);
                return loader;

            }

            @Override
            public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor data) {
                if (loader.getId() == LOADER_ID) {
                    mCurorAdapter.swapCursor(data);
                }
            }

            @Override
            public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {
                mCurorAdapter.swapCursor(null);
            }
        });

    }

    private void initAdapter() {
        mCurorAdapter=new CursorAdapter(getActivity(),null,false) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                View view=mInflater.inflate(R.layout.listview_item,parent,false);
                return view;
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView detail= (TextView) view.findViewById(R.id.id_tv_theme);
                TextView time= (TextView) view.findViewById(R.id.id_tv_time);
                TextView money= (TextView) view.findViewById(R.id.id_tv_money);
                //赋值
                detail.setText(cursor.getString(cursor.getColumnIndex(Bill.COLUMN_BILLDETAIL)));
                time.setText(cursor.getString(cursor.getColumnIndex(Bill.COLUMN_BILLTIME)));
                money.setText(""+cursor.getDouble(cursor.getColumnIndex(Bill.COLUMN_BILLMONEY)));

            }
        };
    }


}




