package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameObject {

    private int pos_X;
    private int pos_Y;
    private  int width;
    private int height;


    Bitmap object;
    //Constructor;

    public  GameObject(int _X, int _Y, int _width, int _height, Resources res)
    {
        this.pos_X = _X;
        this.pos_Y = _Y;
        this.width = _width;
        this.height = _height;

        object = BitmapFactory.decodeResource(res,R.drawable.wall);

        object = Bitmap.createScaledBitmap(object,width,height,false);


    }

    public int Object_X()
    {
        return  pos_X;

    }
    public  int Object_Y()
    {
        return  pos_Y;
    }
    public  void move_right()
    {
        pos_X +=10;//Leviz me te njejten madhesi me background

    }
    public  void  move_left()
    {
        pos_X -=15;
    }
    public  void  move_up()
    {
        pos_Y -=5;
    }
    public  void  move_down()
    {
        pos_Y +=5;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void update_object(int x_amount,int y_amount,Player player)
    {
        if(Game.moveRight == true) {
            pos_X = pos_X - x_amount - 10;
        }
        if(Game.moveLeft == true) {
            pos_X = pos_X - x_amount + 10;
        }

        if(Player.isMovingDown == true && player.player_y() > 210)
            pos_Y = pos_Y - y_amount;
    }
}
