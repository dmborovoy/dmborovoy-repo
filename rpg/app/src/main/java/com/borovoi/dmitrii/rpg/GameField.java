package com.borovoi.dmitrii.rpg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.borovoi.dmitrii.rpg.model.area.Board;

/**
 * Created by dimas on 12/8/2015.
 */
public class GameField extends SurfaceView implements Runnable {


    Thread t;
    SurfaceHolder holder;
    Bitmap grassBitmap;
    static final String TAG = "GAME";
    Board board;
    int dx=50;

    public GameField(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

//    public GameField(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    public GameField(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    public GameField(Context context) {
        super(context);
        init();
    }

    private void init() {
        board = new Board(10, 10);
        holder = getHolder();
        grassBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.grass50);
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            if (holder.getSurface().isValid()) {
                Canvas canvas = holder.lockCanvas();
                paint(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    private void paint(Canvas canvas) {
        canvas.drawColor(Color.BLUE);

        Paint p = new Paint();
//        p.setColor(Color.YELLOW);
//        p.setAntiAlias(true);
//

        canvas.drawBitmap(grassBitmap, new Rect(0, 0, dx, dx), new Rect(0, 0, dx, dx), p);
        canvas.drawBitmap(grassBitmap, new Rect(dx, dx, dx + dx, dx + dx), new Rect(dx, dx, dx + dx, dx + dx), p);

        Log.i(this.TAG, "painting...");
    }
}
