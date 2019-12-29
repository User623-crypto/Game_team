package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Player {
    public int pos_X;
    public int pos_Y;
    private int width;
    private int height;

    private Game gameview;


    //Jump
    private int _jumppower=5;
    private int holding;
    private boolean finishjumping=false;


    private int image_turn = 1;
    private boolean move_left;
    private boolean move_up;
    //Bitmap player_bitmap,player_walk1,player_walk2,walk1_reverse,walk2_reverse;
        Bitmap sprite,reverse_sprite;
    //private Bitmap[] right_anim;
    //private Bitmap[] left_anim;
    //private Bitmap[] jump_anim;
    public Player(int width,int height,Resources res)
    {
        this.width = width;
        this.height = height;
        sprite = BitmapFactory.decodeResource(res,R.drawable.left_to_right);
        sprite = Bitmap.createScaledBitmap(sprite,width,height,false);
        reverse_sprite = BitmapFactory.decodeResource(res,R.drawable.running_glitch_left);
        reverse_sprite = Bitmap.createScaledBitmap(sprite,width,height,false);
    }
    //public Player(int _X, int _Y, int _width, int _height, Resources res)
    //{

       /*
        this.pos_X = _X;
        this.pos_Y = _Y;
        this.width = _width;
        this.height = _height;

        player_bitmap = BitmapFactory.decodeResource(res,R.drawable.alienblue);
        //sprite = new Sprite(player_bitmap);
        player_walk1 = BitmapFactory.decodeResource(res,R.drawable.alien_walk1);
        player_walk2 = BitmapFactory.decodeResource(res,R.drawable.alien_walk2);
        walk1_reverse = BitmapFactory.decodeResource(res,R.drawable.alien_walk1_reverse);
        walk2_reverse = BitmapFactory.decodeResource(res,R.drawable.alien_walk2_reverse);

        player_bitmap = Bitmap.createScaledBitmap(player_bitmap,width,height,false);
        player_walk1 = Bitmap.createScaledBitmap(player_walk1,width,height,false);
        player_walk2 = Bitmap.createScaledBitmap(player_walk2,width,height,false);
        walk1_reverse = Bitmap.createScaledBitmap(player_walk1,width,height,false);
        walk2_reverse = Bitmap.createScaledBitmap(player_walk2,width,height,false);

        right_anim = new Bitmap[]{player_walk1,player_walk1,player_walk2,player_walk2};
        left_anim = new Bitmap[]{walk1_reverse,walk1_reverse,walk2_reverse,walk2_reverse};
        //jump_anim = new Bitmap[]{}
        */




    //}


   /*
    public boolean to_left()
    {
        if(pos_X < 11)
            return false;

        return  true;

    }

    public  boolean to_up()
    {
        if(pos_Y < 11)
            return false;
        return true;
    }

    public  boolean to_bottom()
    {
        if(pos_Y > player_bitmap.getHeight() - 11)
            return false;
        return true;
    }

    public int player_X()
    {
        return pos_X;

    }

    public void jump()
    {

        if(_jumppower<13 && !finishjumping)
        {
            pos_Y-=15; _jumppower++;
        }
        else if ((_jumppower>=13 )&&pos_Y<=holding)
        {
            pos_Y+=7;
            finishjumping=true;
        }
        else {
            finishjumping=false;_jumppower=5;
        }
    }

    public  int player_Y()
    {
        return pos_Y;
    }

    public  void move_right()
    {

            pos_X +=2;
            jump();
    }
    public  void  move_left()
    {

            pos_X -=10;
            jump();

    }
    public  void move_up()
    {
        pos_Y -= 2;
    }

    public  void move_down()
    {
        pos_Y += 2;
    }

    public int player_width()
    {
        return player_bitmap.getWidth();
    }
    public int player_height()
    {
        return player_bitmap.getHeight();
    }
    */
    /*
    Bitmap getImage()
    {
        if(image_turn == 1)
        {
            image_turn++;
            return player_bitmap;

        }
        if(image_turn == 2)
        {
            image_turn ++;
            return player_walk1;

        }
        if(image_turn == 3)
        {
            image_turn ++;
            return player_walk1;

        }

        if(image_turn == 4)
        {
            image_turn ++;
            return player_walk2;

        }
        if(image_turn == 5)
        {
            image_turn ++;
            return player_walk2;

        }
        if(image_turn == 6)
        {
            image_turn ++;
            return walk1_reverse;

        }
        if(image_turn == 7)
        {
            image_turn ++;
            return walk1_reverse;

        }

        if(image_turn == 8)
        {
            image_turn ++;
            return walk2_reverse;

        }

        image_turn = 1;
        return walk2_reverse;
    }
     */




   /*
    public void start_right_anim(Canvas canvas)
    {
       for(int i =0;i<right_anim.length;i++)
       {
           canvas.drawBitmap(right_anim[i],pos_X,pos_Y,null);
       }
    }

    public void start_left_anim(Canvas canvas)
    {
        for(int i =0;i<left_anim.length;i++)
        {
            canvas.drawBitmap(left_anim[i],pos_X,pos_Y,null);
        }
    }

    */
}
