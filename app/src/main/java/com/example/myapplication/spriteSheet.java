package com.example.myapplication;

import android.graphics.Rect;
import android.graphics.RectF;

import static com.example.myapplication.Game.moveLeft;
import static com.example.myapplication.Game.moveRight;
import static com.example.myapplication.Game.moveUp;

public class spriteSheet {
    //Sprite sheet
    private boolean isMoving;
    private float runSpeedPerSecond = 200;
    private float Xpos;//poszicioni i x ku nis levizja
    private float Ypos;//poszicioni i y ku nis levizja
    //public static int frameWidth = 180,frameHeight = 260;
    private int frameWidth ,frameHeight;
    private int frameCount; //Ndahet  Spritesheet ne 12 pjese te barabarta
    private int currentFrame;
    public long fps;
    private long timeThisFrame;
    private long lastFrameChangeTime = 0;
    private int frameLengthinMillisecond = 40; //Shpejtesia e levizjes se objektit . P.sh Levizin me shpejt kembet
    //Percakton size e frames qe do te merret

    private Rect frametoDraw ;//Ku fillon ndarja e framave. Eshte frame qe shfaqet qe shohim ne.nese e vendos psh top : 200 ath zdhuket

    //Poicioni se ku do shfaqet frama
    private RectF wheretoDraw;

    public  spriteSheet(float x_pos,float y_pos,int frame_width,int frame_height,int frameCount)

    {
        this.Xpos = x_pos;
        this.Ypos = y_pos;
        this.frameWidth = frame_width;
        this.frameHeight = frame_height;
        this.frameCount = frameCount;
        frametoDraw = new Rect(0,0,frameWidth,frameHeight);
        wheretoDraw = new RectF(Xpos,Ypos,Xpos+frameWidth,frameHeight);
    }

    public float X_pos()
    {
        return  Xpos;

    }
    public float Y_pos()
    {
        return  Ypos;

    }

    public void set_X_pos(float _x)
    {
        Xpos = _x;

    }
    public void  set_Y_pos(float _y)
    {
        Ypos = _y;

    }
    public int height()
    {
        return  frameHeight;

    }
    public int width()
    {
        return  frameWidth;

    }
    public  int framecount()
    {
        return  frameCount;
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

    public Rect frametoDraw()
    {
        return frametoDraw;
    }
    public  RectF wheretoDraw()
    {
        return  wheretoDraw;
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
                    if(currentFrame >= frameCount)
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

                    if(currentFrame >=frameCount)
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
