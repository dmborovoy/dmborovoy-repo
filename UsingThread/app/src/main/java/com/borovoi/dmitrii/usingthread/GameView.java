package com.borovoi.dmitrii.usingthread;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by dimas on 12/21/2015.
 */
public class GameView extends SurfaceView implements Runnable {
    SurfaceHolder holder;
    Thread t;
    boolean drawing = true;
    boolean dragging;
    Paint p;
    Paint p1;

    // Экран
    int width, height;

    //    Bitmap ball;
    // Круг
    float x, y;
    float r = 100;
    float bX, bY;
    float vX = 20, vY = 15;

    public GameView(Context context) {
        super(context);
        holder = getHolder();
        p = new Paint();
        p1 = new Paint();
        p.setColor(Color.YELLOW);
        p1.setColor(Color.GREEN);
        p.setStyle(Paint.Style.FILL);
        bX = bY = 200;
        x = y = 100;
    }


    public void resume() {
        drawing = true;
        t = new Thread(this);
        t.start();
    }

    public void pause() {
        drawing = false;
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (drawing) {
            if (holder.getSurface().isValid()) {
                Canvas canvas = holder.lockCanvas();
                width = canvas.getWidth();
                height = canvas.getHeight();

                update();
                paint(canvas);

                Log.i(MainActivity.TAG, "painting...");
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    private void paint(Canvas canvas) {
        canvas.drawARGB(250, 127, 199, 255); // заливаем цветом
        canvas.drawCircle(bX, bY, r, p);
        canvas.drawCircle(x, y, r, p);
    }

    private void update() {
        bX = bX + vX;
        bY = bY + vY;

        if (bX < r) {
            bX = r;
            vX = -vX;
        } else if (bX > width - r) {
            bX = width - r;
            vX = -vX;
        }

        if (bY < r) {
            bY = r;
            vY = -vY;
        } else if (bY > height - r) {
            bY = height - r;
            vY = -vY;
        }
    }

    private boolean insideCircle (int eventX, int eventY) {
        return Math.sqrt(Math.pow(x - eventX,2) + Math.pow(y - eventY,2)) < r;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                if (insideCircle((int)event.getX(), (int)event.getY())) {
                    dragging = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (dragging) {
                    x = event.getX();
                    y = event.getY();
                }
                break;
            case MotionEvent.ACTION_UP:
                dragging = false;
                break;
        }
        return true;
    }
}
