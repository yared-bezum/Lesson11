package com.example.yaredbezu.myfirstgame;

/**
 * Created by yared.bezu on 12/3/2015.
 */
import android.graphics.Canvas;

public class MyThread extends Thread {

    MySurfaceView myView;
    private boolean running = false;

    public MyThread(MySurfaceView view) {
        myView = view;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        while(running){

            Canvas canvas = myView.getHolder().lockCanvas();

            if(canvas != null){
                synchronized (myView.getHolder()) {
                    myView.drawSomething(canvas);
                }
                myView.getHolder().unlockCanvasAndPost(canvas);
            }

            try {
                sleep(30);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

}