package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Background {

    public int x = 0;
    public int y = 0;

    public  int _drawable;
    private static int BACKGROUND_HEIGHT;
    Bitmap my_background;
    Bitmap my_reverse_background;
    private int count =1;
    private int width;
    private int screenX;


    //drawble variable set image name...

    Background(int screenX, int screenY, Resources res,int _drawable)
    {


        this._drawable = _drawable;
        //Rresources res -> Perdoret per ti bere decode bitmapit
        this.screenX=screenX;

        my_background = BitmapFactory.decodeResource(res,_drawable);
        //my_reverse_background = BitmapFactory.decodeResource(res,idle_reverse);

        //Ben resize te imazhit
        my_background = Bitmap.createScaledBitmap(my_background,screenX,screenY,false);
        //my_reverse_background = Bitmap.createScaledBitmap(my_reverse_background,screenX,screenY,false);
        BACKGROUND_HEIGHT = my_background.getHeight();
        width=my_background.getWidth();


    }

    public int background_X()
    {
        return x;
    }
    public int background_Y()
    {
        return y;
    }

    public void updateRight(int a)
    {
        if (a < BACKGROUND_HEIGHT/2) {
            x -= 10;

        }

        if (a > BACKGROUND_HEIGHT/2) {
            x -= 12;

        }
        if (x + width < 0) {
            //call background 1
            x = screenX + 5;
        }

    }



    public void update_bakground(int x_amount,int y_amount,Player player)
    {

           if(Game.moveRight == true)
               x = x - x_amount - 10;
           if(Game.moveLeft == true && player.player_x() > 10)
               x = x - x_amount + 10;


           //Per te levizur kamera vertikalisht
        if(y != 0 && y > -500 && y < 300)
        {

                y = y - y_amount;


        }




}

}
