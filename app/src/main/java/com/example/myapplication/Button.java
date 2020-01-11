package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Button {
    private float x;
    private float y;
    private int width;
    private int height;
    private int drawable;
    Bitmap button;
    public Button(float _X,float _Y, int width, int height,Resources res,int _drawable)
    {
        this.x = _X;
        this.y = _Y;
        this.drawable = _drawable;
        this.width = width;
        this.height = height;

        button = BitmapFactory.decodeResource(res,drawable);
        button = Bitmap.createScaledBitmap(button,width,height,false);





    }
    public  float button_X()
    {
        return x;

    }
    public  float button_Y()
    {
        return y;
    }

    public  int button_width()
    {
        return  width;
    }
    public  int button_height()
    {
        return  height;
    }
    public  boolean touched(float x_cor,float y_cor)
    {
        if((x_cor > button_X() && x_cor < button_X() + width) && (y_cor > button_Y() && y_cor < button_Y() + height))
        {
            return true;
        }
        return false;
    }
}
