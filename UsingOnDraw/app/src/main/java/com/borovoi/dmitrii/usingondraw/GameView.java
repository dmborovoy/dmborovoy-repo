package com.borovoi.dmitrii.usingondraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by dimas on 12/18/2015.
 */
public class GameView extends View {

    private int viewWidth;
    private int viewHeight;
    private int points = 0;
    private float newX=0;
    private float newY=0;
    private Paint p;

    public GameView(Context context) {
        super(context);
        p = new Paint();
        p.setAntiAlias(true);
        p.setTextSize(55.0f);
        p.setColor(Color.WHITE);
    }
//
//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        viewWidth = w;
//        viewHeight = h;
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        update(canvas);
    }

    private void update(Canvas canvas) {
        canvas.drawARGB(250, 127, 199, 255); // заливаем цветом
        canvas.drawCircle(newX, newY, 100, p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();

        if (eventAction == MotionEvent.ACTION_DOWN) {
            newX = event.getX();
            newY = event.getY();
        }
        invalidate();
        return true;
    }
}
