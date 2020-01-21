package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameObject {

    private float original_X_POS;
    private float original_Y_POS;
    private float pos_X;
    private float pos_Y;
    private  int width;
    private int height;

    static  float X_offset_value = 0;
    static  float Y_offset_value = 0;
    private int drawable;


    Bitmap object;
    //Constructor;

    public  GameObject(float _X, float _Y, int _width, int _height, Resources res,int _drawable)
    {
        this.original_X_POS = _X;
        this.original_Y_POS = _Y;
        this.pos_X = _X;
        this.pos_Y = _Y;
        this.width = _width;
        this.height = _height;

        this.drawable = _drawable;
        object = BitmapFactory.decodeResource(res,drawable);

        object = Bitmap.createScaledBitmap(object,width,height,false);


    }

    public float Original_X()
    {
        return original_X_POS;
    }
    public  float Original_Y()
    {
        return  original_Y_POS;
    }


    public float Object_X()
    {
        return  pos_X;

    }
    public  float Object_Y()
    {
        return  pos_Y;
    }

    static float Object_X(GameObject object)
    {
        return object.Object_X();
    }



    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void update_object(float x_amount,int y_amount,Player player)
    {
        if(Game.moveRight == true) {
            pos_X = pos_X - x_amount ;

        }
        else if(Game.moveLeft == true) {
            pos_X = pos_X - x_amount;
        }



        else if(Game.moveUp == true && Game.moveRight == false && Game.moveLeft ==false)
        {
           pos_Y = pos_Y - y_amount + 5;
        }
        else if(Game.moveUp == true && (Game.moveLeft || Game.moveRight))
            pos_Y = pos_Y + 2;

        else if(Player.isMovingDown == true && player.player_y() > 210)
            pos_Y = pos_Y - y_amount - 5;

    }
}
