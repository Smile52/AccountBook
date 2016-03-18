package com.smile.accountbook.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import accountbook.smile.com.accountbook.R;

/**折线图控件
 * Created by 27270 on 2016/1/21.
 */
public class ChartView extends View {
    private int XPoint,YPoint;//折线图X,Y原点坐标
    private int XScale,YScale;//X,Y刻度
    private String[] XLable;//X轴的刻度
    private String[] YLable;//Y轴的刻度
    private double[] money;//消费金额，也就是数据
    private String title;//标题
    private int phoneWidth,phoneHeight;//屏幕分辨率


    public ChartView(Context context) {
        super(context);

    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    /**
     * 设置控件的相关信息（数据）
     */
    public void setViewInfo(int width,int height, String[] XLable,String[] YLable,double[] money,String title){
        this.phoneHeight=height;
        this.phoneWidth=width;

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         *horizontalScrollview里面嵌套自定义view或者系统控件必须给定这个控件的宽高，否则不显示出来
         */
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //手动设置宽高
        int width=1700;
        int height=1000;
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Log.i("dandy", "执行了");
        Paint paint=new Paint();
        paint.setColor(Color.rgb(125,230,249));
        paint.setStrokeWidth(5);//设置线的宽度
        paint.setAntiAlias(true);//抗锯齿
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawLine(80, 950, 2000, 950, paint);
        canvas.drawLine(80,950,80,85,paint);//画竖线

        canvas.drawLine(80,85,65,100,paint);
        canvas.drawLine(80, 85, 95, 100, paint);

    }

}
