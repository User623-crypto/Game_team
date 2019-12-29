package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {

    private int width;
    private  int height;
    private int x;
    private int y;

    private Game game;
    private Bitmap bm;


    public Sprite(Bitmap bm)
    {

        this.bm = bm;

        this.width = bm.getWidth();
        this.height = bm.getHeight();

        this.x = 0;
        this.y = 0;


    }

    public void ondraw(Canvas canvas)
    {
        //Vizaton spritesheet
        Rect src = new Rect(0,0,width,height);
        Rect dst = new Rect(width,height,x + width,y+height);
        canvas.drawBitmap(bm,src,dst,null);
    }

}
