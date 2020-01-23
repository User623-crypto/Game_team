package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.RectF;

import static com.example.myapplication.Game.moveLeft;
import static com.example.myapplication.Game.moveRight;
import static com.example.myapplication.Game.moveUp;

public class spriteSheet {
    //Sprite sheet

    //public static int frameWidth = 180,frameHeight = 260;
    private int frameWidth ,frameHeight;
    private int frameCountX; //Ndahet  Spritesheet ne 12 pjese te barabarta
    private int frameCountY;
    private int currentFrame;
    static long fps;
    private long timeThisFrame;
    private long lastFrameChangeTime = 0;
    private int frameLengthinMillisecond = 40; //Shpejtesia e levizjes se objektit . P.sh Levizin me shpejt kembet


    private Rect frametoDraw ;//Ku fillon ndarja e framave. Eshte frame qe shfaqet qe shohim ne.nese e vendos psh top : 200 ath zdhuket

    //Poicioni se ku do shfaqet frama
    private RectF wheretoDraw;
    private int drawable;
    int width,height;
    //SpriteSheeti
    Bitmap spriteSheet;
      public spriteSheet(int frame_width, int frame_height, int frameCountX,int frameCountY, int drawable,Resources res)

    {
        this.frameWidth = frame_width;
        this.frameHeight = frame_height;
        this.frameCountX = frameCountX;
        this.frameCountY=frameCountY;
         width=frameWidth/frameCountX;
         height=frameHeight/frameCountY;
        spriteSheet = BitmapFactory.decodeResource(res,drawable);
        spriteSheet = Bitmap.createScaledBitmap(spriteSheet,frameWidth,frameHeight,false);



    }
    public spriteSheet(int width, int height, int frameCountX,int frameCountY,int drawable,Resources res,boolean a)
    {
        this.frameCountX = frameCountX;
        this.frameCountY=frameCountY;
        this.width=width;
        this.height=height;
        this.frameWidth=this.width*frameCountX;
        this.frameHeight=this.height*frameCountY;
        spriteSheet = BitmapFactory.decodeResource(res,drawable);
        spriteSheet = Bitmap.createScaledBitmap(spriteSheet,frameWidth,frameHeight,false);
    }




    public Bitmap returnSprite(int a,int b)
    {
        return Bitmap.createBitmap(spriteSheet,(width*a),(height*b),width,height);
    }


    public int height()
    {
        return  height;

    }
    public int width()
    {
        return  width;

    }
    public  int framecountX()
    {
        return  frameCountX;
    }
    public void  set_frameLengthinMillisecond(int frame_speed)
    {
        frameLengthinMillisecond = frame_speed;
    }
    public  void setCurrentframe(int frame)
    {
        currentFrame = frame;
    }
    public void on_run(long startFrameTime)
    {

        timeThisFrame = System.currentTimeMillis() - startFrameTime;
        if(timeThisFrame >=1)
        {
            fps = 1000/timeThisFrame;
        }
    }


    public void manageCurrentFrame()
    {

        long time = System.currentTimeMillis();
        if(moveLeft != false || moveRight != false)
        {
            if(time > lastFrameChangeTime + frameLengthinMillisecond)
            {
                lastFrameChangeTime = time;
                if(Game.moveRight == true && moveUp == false)
                {
                    currentFrame++;
                    if(currentFrame >= frameCountX)
                    {
                        currentFrame = 12;

                    }
                }

                else if(Game.moveLeft == true && moveUp == false)
                {
                    currentFrame--;
                    if(currentFrame < 1)
                    {
                        currentFrame = 11;

                    }
                }

                else if(Game.moveUp && Game.moveRight == true)
                {

                    currentFrame++;

                    if(currentFrame >=frameCountX)
                    {
                        currentFrame = 13;
                        moveUp = false;

                    }
                }

                else if(Game.moveUp == true && Game.moveLeft == true)
                {

                    currentFrame--;
                    if(currentFrame < 1)
                    {
                        currentFrame = 11;
                        moveUp = false;

                    }
                }

            }
        }


        frametoDraw.left = currentFrame*frameWidth;

        frametoDraw.right = frametoDraw.left + frameWidth;
    }


}
