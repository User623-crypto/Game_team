package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

public class Player {

    /*************************************Pjesa e Perplasjes***********************************/
    //????
    int nr_i_vektorit=0;
    private  final int tolerance_range=-37;//Marzh gabimi
    private final int tolerance_rangeY=2;
    /*********************Pas Kesaj Punon*******************************************************/

    private final int gravity=15;
    private int velocityY=80;
    private int finalPosY=0;
    private int holding;
    private boolean finishjumping=true;


    /**********************Testing Sprites********************************************************/

    private boolean moveRight=false;
    private boolean moveLeft=false;
    private boolean didMoveRight=false;
    private boolean didMoveLeft=false;
    private int jumpCount=0;

    private long lastFrameChangeTime=0;
    private long frameLengthinMillisecond=20;
    private int currentFrame=12;
    private int frameCount=24;


    public static int frameWidth = 180,frameHeight = 260;
    private Rect frametoDraw = new Rect(0,0,frameWidth,frameHeight);
    private RectF wheretoDraw = new RectF(this.pos_X,this.pos_Y,this.pos_X+frameWidth,frameHeight);

    /************************************************************************************************/

    /*********************************VAR NEEDED FOR THE UPDATE*************************************/
    private int _velocityX=15;


    private int pos_X;
    private int pos_Y;
    private int width;
    private int height;
    private int trueWidth;

    private Game gameview;


    private boolean move_left;
    private boolean move_up;

    Bitmap sprite,reverse_sprite;

    public Player(int _X,int _Y,int width,int height,Resources res)
    {
        this.pos_X = _X;
        this.pos_Y = _Y;
        this.width = width;
        this.height = height;
        this.trueWidth=width/frameCount;

        sprite = BitmapFactory.decodeResource(res,R.drawable.left_to_right);
        sprite = Bitmap.createScaledBitmap(sprite,width,height,false);
        reverse_sprite = BitmapFactory.decodeResource(res,R.drawable.running_glitch_left);
        reverse_sprite = Bitmap.createScaledBitmap(sprite,width,height,false);


        this.holding=pos_Y;
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

    /*****************************************GETTERS******************************************/
    public int player_X()
    {
        return pos_X;

    }
    public  int player_Y()
    {
        return pos_Y;
    }

    public int player_width()
    {
        return frameWidth;
    }
    public int player_height()
    {
        return frameHeight;
    }

    public int playerTruewidth()
    {return trueWidth;}

    public boolean didMoveRight()
    {
        return  didMoveRight;
    }
    public boolean didMoveLeft()
    {
        return  didMoveLeft;
    }
    /********************************Movement Functions********************************************/

    public void fall(GameObject gameObject[])
    {
        if(pos_Y+height<1080 && finishjumping && !checkObjcollisionDown(gameObject))
        {
            jumpCount=1;
            pos_Y+=gravity;

            finalPosY=(pos_Y-((velocityY*velocityY)/(2*gravity)));

        }else if(!finishjumping)
        {jump(gameObject);}
        else {jumpCount=0;}
    }

    public void jump(GameObject gameObject[])
    {
        jumpCount=1;
        if(pos_Y<=finalPosY || checkObjcollisionUp(gameObject))
            finishjumping=true;

        if(!finishjumping)
            pos_Y-=velocityY-(2*gravity);
    }

    public  void move_right()
    {

            this.pos_X +=_velocityX;

    }
    public  void  move_left()
    {

            pos_X -=10;


    }

    public void playerUpdate(GameObject gameObject[],int a)
    {

        switch (a)
        {
            //E ben te levizi djathtas
            case 1:

                moveRight=true;moveLeft=false;
                if(!checkObjcollisionRight(gameObject))
                { move_right(); didMoveRight=true;didMoveLeft=false;}
                break;
                //Jump
            case 2:

            moveLeft=false;moveRight=false;
                didMoveLeft=true;didMoveRight=false;

            if(jumpCount==0)
                finishjumping=false;
                break;
            //Move left
                case 3:

                moveLeft=true;moveRight=false;
                if(!checkObjcollisionLeft(gameObject))
                { move_left(); didMoveLeft=true;didMoveRight=false;}
                break;

             default:


                 moveLeft=false;moveRight=false;
                 didMoveLeft=true;didMoveRight=false;
                 break;
        }
        fall(gameObject);



    }




    /**********************************TESTING******************************************/

    public void manageCurrentFrame()
    {
        long time = System.currentTimeMillis();

            if(time > lastFrameChangeTime + frameLengthinMillisecond)
            {
                lastFrameChangeTime = time;
                if(moveRight)
                {
                    currentFrame++;
                    if(currentFrame >= frameCount)
                    {
                        currentFrame = 13;

                    }
                }

                if(moveLeft)
                {
                    currentFrame--;
                    if(currentFrame < 1)
                    {
                        currentFrame = 11;

                    }
                }
                if(!moveLeft && !moveRight) {
                    //Log.d("Testing","Ekzekutohet kjo  apo jo");
                    currentFrame=13;}

        }



        frametoDraw.left = currentFrame*frameWidth;

        frametoDraw.right = frametoDraw.left + frameWidth;
    }


    /*********************************Drawing************************************************/
    public void draw(Canvas canvas)
    {


        //Draw anim
        wheretoDraw.set((int)this.pos_X,(int)this.pos_Y,(int)this.pos_X  + frameWidth,(int)this.pos_Y + frameHeight);

        //draw every frame on screen
        manageCurrentFrame();

        //nuk e vizaton gjitheimazhin per nje pjese te saj
        canvas.drawBitmap(this.sprite,frametoDraw,wheretoDraw,null);


    }



    /*******************************Deri Ketu Funksiononte**********************************/

    /*******************************OBJECT COLLISON CHECK ******************************************************/
    public boolean checkleft( GameObject a )
    {
        return this.pos_X <= (a.Object_X() + a.getWidth() + tolerance_range) && ((this.pos_X + tolerance_range) > a.Object_X()) && (this.pos_Y + this.height) > a.Object_Y() && this.pos_Y < (a.Object_Y() + a.getHeight());
    }

    public boolean checkright(GameObject a)
    {
        return (this.pos_X + this.trueWidth + tolerance_range) > a.Object_X() && ((this.pos_X) <= a.Object_X() + tolerance_range) && (this.pos_Y + this.height) > a.Object_Y() && this.pos_Y < (a.Object_Y() + a.getHeight());
    }

    public boolean checkup(GameObject a)
    {
        return this.pos_Y <= (a.Object_Y() + a.getHeight() + tolerance_rangeY) && ((this.pos_Y + tolerance_rangeY) >= a.Object_Y()) && (this.pos_X + this.trueWidth) + tolerance_range - 5 > a.Object_X() && (this.pos_X) < (a.Object_X() + a.getWidth() + tolerance_range - 5);
    }

    public boolean checkdown(GameObject a)
    {
        /********************E RENDESISHME VLERA 5 ESHTE VLERE ABSURDE E DOMOSDOSHME QE RRIT TOLERANCEN NESE KA PROBLEM PERPLASJA DUHET ME U PARE**************************************/
        if((this.pos_Y+this.height+tolerance_rangeY)>=(a.Object_Y()) && ((this.pos_Y)<=a.Object_Y()+tolerance_rangeY) && (this.pos_X+this.trueWidth)+tolerance_range-5>a.Object_X() && (this.pos_X)<(a.Object_X()+a.getWidth()+tolerance_range-5))
            return true;

        return false;
    }


    public boolean checkObjcollisionRight(GameObject [] a )
    {
        for(int i=0;i<a.length;i++){
            {nr_i_vektorit=i;
                if(checkright(a[i])){return true;}}}
        return false;
    }
    public boolean checkObjcollisionLeft(GameObject [] a )
    {
        for(int i=0;i<a.length;i++){nr_i_vektorit=i;
            if(checkleft(a[i])){return true;}}

        return false;
    }

    public boolean checkObjcollisionUp(GameObject [] a )
    {
        for(int i=0;i<a.length;i++){nr_i_vektorit=i;
            if(checkup(a[i])){return true;}}

        return false;
    }

    public boolean checkObjcollisionDown(GameObject [] a )
    {
        for(int i=0;i<a.length;i++){nr_i_vektorit=i;
            if(checkdown(a[i])){return true;}}

        return false;
    }
}


