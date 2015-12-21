package com.borovoi.dmitrii.usingthread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    GameView game;
    static final String TAG = "GAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        game = new GameView(this);
        setContentView(game);
    }

    @Override
    protected void onPause() {
        super.onPause();
        game.pause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        game.resume();
        Log.i(TAG, "onResume");
    }




}
