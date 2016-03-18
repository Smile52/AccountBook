package com.smile.beisaier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by qq272 on 2016/2/29.
 */
public class BeiSaiEr extends View {
    int mSupx,mSupy;
    public BeiSaiEr(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BeiSaiEr(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p=new Paint();
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);
        Path path=new Path();
        path.moveTo(200,200);
        path.quadTo(mSupx, mSupy, 500, 200);
        canvas.drawPath(path,p);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mSupx= (int) event.getX();
                mSupy= (int) event.getY();
                invalidate();
        }
        return true;
    }
}
