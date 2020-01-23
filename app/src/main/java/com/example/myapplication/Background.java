package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;


public class Background {

    public float x = 0;
    public int y = 0;

    private  int _drawable;

    private Bitmap my_background;


    private int width;
    private int screenX;


    //drawble variable set image name...

    Background(int screenX, int screenY, Resources res, int _drawable)
    {


        this._drawable = _drawable;
        //Rresources res -> Perdoret per ti bere decode bitmapit
        this.screenX=screenX;

        my_background = BitmapFactory.decodeResource(res,_drawable);
        //my_reverse_background = BitmapFactory.decodeResource(res,idle_reverse);

        //Ben resize te imazhit
        my_background = Bitmap.createScaledBitmap(my_background,screenX,screenY,false);
        //my_reverse_background = Bitmap.createScaledBitmap(my_reverse_background,screenX,screenY,false);
        width=my_background.getWidth();


    }

    public void  draw_background(Canvas canvas)
    {
        canvas.drawBitmap(this.my_background,this.x - Camera.offset_X,this.y - Camera.offset_Y,null);
    }



}
