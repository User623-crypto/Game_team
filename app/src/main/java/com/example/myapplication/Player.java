package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.myapplication.Game.chair1;
import static com.example.myapplication.Game.divan1;
import static com.example.myapplication.R.drawable.*;

public class Player {

    /*******************Ne Varesi te imazhit te lojtarit**************/
    int leftPadding=50;
    int rightPadding=40;
    int upPadding=0;
    int downPadding=10;
    /****************************Animim*//////////////////////////////
    int testCount=0;
    float runtime=0;float changetime=0;
    float runSpeed=60/40;//shpejtesia
    /*************************************Pjesa e Perplasjes***********************************/
    //????
    private int nr_i_vektorit = 0;
    private  final int tolerance_range=10;//Marzh gabimi
    private final int tolerance_rangeY=10;
    /*********************Pas Kesaj Punon*******************************************************/

    private final float gravity=10;
    private int velocityY=80;
    private float finalPosY=0;
    private int holding;
    private boolean finishjumping=true;



    private boolean didMoveRight=false;
    private boolean didMoveLeft=false;
    private int jumpCount=0;

    private int _velocityX=20;
    private int _velocityY = 700;
    static float acceleration = 1;
    static float move_angle = 0;

    private float pos_X;
    private float pos_Y;
    private int width;
    private int height;

    private Game gameview;

    static boolean isMovingDown;//Nese eshte duke rene
    Bitmap sprite;
    int drawable_image; // Spritesheeti si imazh
    private spriteSheet _spriteSheet;
    private Bitmap spriteShow;
    private int row=12;
    private int col=0;


    public Player(float _X, float _Y, spriteSheet my_spriteSheet)
    {
        this.pos_X = _X;
        this.pos_Y = _Y;

       this.width=my_spriteSheet.width();
       this.height=my_spriteSheet.height();

        this._spriteSheet = my_spriteSheet;


    }
    public Player(float _X, float _Y,int width,int height,Resources res)
    {
        this.pos_X = _X;
        this.pos_Y = _Y;
        this.width=width;
        this.height=height;
        this._spriteSheet = new spriteSheet(width, height, 24,  1,R.drawable.left_to_right,res,true);//124


    }
    private float imgBoundR(){return pos_X+width-rightPadding;}
    private float imgBoundL(){return pos_X+leftPadding;}
    private float imgBoundU(){return pos_Y+upPadding;}
    private float imgBoundD(){return pos_Y+height-downPadding;}

    /*public  void make_decode(Resources res,int drawable_image)
    {
        sprite = BitmapFactory.decodeResource(res,drawable_image);
        sprite = Bitmap.createScaledBitmap(sprite,width,height,false);
    }*/


    public boolean didMoveRight()
    {
        return  didMoveRight;
    }
    public boolean didMoveLeft()
    {
        return  didMoveLeft;
    }

    private void fall(GameObject gameObject[])
    {
        if(this.pos_Y+height<800  && !checkObjcollisionDown(gameObject))
        {
            jumpCount=1;
            pos_Y +=gravity ;




            finalPosY=(pos_Y-((velocityY*velocityY)/(2*gravity)));

        }
        /*
        else if(!finishjumping)
        {
           //my_jump(gameObject);
        }
        else {jumpCount=0;}
         */
    }

   /*
    public void jump()
    {
        jumpCount=1;
        if(pos_X<=finalPosY)
            finishjumping=true;

        if(!finishjumping)
            this.pos_Y= this.pos_Y - (velocityY - (2*gravity));

    }
    */
    public  void move_right(GameObject gameObject[],int X_velocity,float angle)
    {

        if(!checkObjcollisionRight(gameObject))
        {
            this.pos_X = this.pos_X + 20;

        }






    }
    public  void  move_left(final GameObject gameObject[],int X_velocity)
    {

        if(!checkObjcollisionLeft(gameObject))
        {
            this.pos_X = this.pos_X - 20;

        }




    }
    public void my_jump(GameObject gameObject[])
    {

                if(!checkObjcollisionUp(gameObject))
                {
                    this.pos_Y = this.pos_Y - 20;

                }


    }
    public  void move_up_right(GameObject gameObject[])
    {
                my_jump(gameObject);
                move_right(gameObject,_velocityX,0);
                GameObject.X_offset_value = 5;


    }
    public  void move_up_left(GameObject gameObject[])
    {
        my_jump(gameObject);
        move_left(gameObject,_velocityX);
    }

    public int player_width()
    {
        return width;
    }
    public int player_height()
    {
        return height;
    }


    public  float player_x()
    {
        return pos_X;
    }
    public  float player_y()
    {
        return pos_Y;
    }





    public void draw(Canvas canvas)
    {


        //Draw anim
        //_spriteSheet.wheretoDraw().set((int)this.pos_X,(int)this.pos_Y,(int)this.pos_X  + _spriteSheet.width(),(int)this.pos_Y + _spriteSheet.height());

        //draw every frame on screen
        //_spriteSheet.manageCurrentFrame();

        //nuk e vizaton gjitheimazhin per nje pjese te saj
        //canvas.drawBitmap(this.sprite,_spriteSheet.frametoDraw(),_spriteSheet.wheretoDraw(),null);
        canvas.drawBitmap(_spriteSheet.returnSprite(row,col),this.pos_X - Camera.offset_X,this.pos_Y - Camera.offset_Y,null);


    }

    public void playerUpdate(final GameObject gameObject[])
    {


           /* if (testCount > 60){
                testCount = 0;}*/
            testCount++;
            if (Game.moveRight == true && Game.moveUp == true) {



                move_up_right(gameObject);

            } else if (Game.moveLeft == true && Game.moveUp == true) {
                if (this.drawable_image != jumpanim) {
                    _spriteSheet.setCurrentframe(11);
                    _spriteSheet.set_frameLengthinMillisecond(55);
                    this.drawable_image = R.drawable.jumpanim;

                    //make_decode(res,drawable_image);
                }

                move_up_left(gameObject);
            } else if (Game.moveUp == true && Game.moveRight == false && Game.moveLeft == false) {
                //jump();
                my_jump(gameObject);
            } else if (Game.moveRight == true && Game.moveUp == false) {
            /*if(this.drawable_image != left_to_right)
            {
                _spriteSheet.setCurrentframe(13);
                _spriteSheet.set_frameLengthinMillisecond(40);
                this.drawable_image = left_to_right;
               // make_decode(res,drawable_image);
            }*/
            if(testCount>runSpeed) {
                animatePlayerRight();
                testCount=0;

            }

                move_right(gameObject, 20, 30);


            } else if (Game.moveLeft == true && Game.moveUp == false ) {

                if(testCount>runSpeed) {
                    animatePlayerLeft();
                    testCount=0;

                }

                move_left(gameObject, 20);

            } else if (!Game.moveRight && !Game.moveLeft  && !Game.moveUp ) {
                if(row>=12)
                    row=12;
                else{row=11;}

            }

            fall(gameObject);
        }


        /********************Animimi****************************************/
        private void animatePlayerRight()
        {
            if(row<12)
            {
                row=12;
            }
            if (row < 23)
                row++;
            else {
                row = 12;
            }
        }
        private void animatePlayerLeft()
        {
            if(row>11)
                row=11;
            if(row>0)
            {row--;}
            else{
                row=11;
            }

        }





    /*******************************Deri Ketu Funksiononte**********************************/

    /*******************************OBJECT COLLISON CHECK ******************************************************/
    public boolean checkleft( GameObject a )
    {
        return imgBoundL() <= (a.Object_X() + a.getWidth() + tolerance_range) && (imgBoundR()-tolerance_range > a.Object_X()) && (imgBoundD())-tolerance_rangeY > a.Object_Y() && imgBoundU()+tolerance_rangeY < (a.Object_Y() + a.getHeight());
    }

    public boolean checkright(GameObject a)
    {
        return (imgBoundR() + tolerance_range) > a.Object_X() && (imgBoundL() <= a.Object_X()+a.getWidth()-tolerance_range) && (imgBoundD())-tolerance_rangeY > a.Object_Y() && imgBoundU()+tolerance_rangeY < (a.Object_Y() + a.getHeight());
    }

    public boolean checkup(GameObject a)
    {
        return imgBoundU() <= (a.Object_Y() + a.getHeight() + tolerance_rangeY) && ((imgBoundD() - tolerance_rangeY) >= a.Object_Y()) && (imgBoundR()-tolerance_range > a.Object_X() && imgBoundL()+tolerance_range < (a.Object_X() + a.getWidth()));
    }

    public boolean checkdown(GameObject a)
    {
        /********************E RENDESISHME VLERA 5 ESHTE VLERE ABSURDE E DOMOSDOSHME QE RRIT TOLERANCEN NESE KA PROBLEM PERPLASJA DUHET ME U PARE**************************************/
        if((imgBoundD()+tolerance_rangeY)>=(a.Object_Y()) && ((imgBoundU())<=a.Object_Y()-tolerance_rangeY) && (imgBoundR()-tolerance_range>a.Object_X() && imgBoundL()+tolerance_range<(a.Object_X()+a.getWidth())))
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
        for(int i=0;i<a.length;i++){
            nr_i_vektorit=i;
            if(checkdown(a[i])){
                //Log.d("my_tag", "****checkObjcollisionDown: true");
                isMovingDown = false;
                return true;

            }
        }
        isMovingDown = true;
        return false;
    }


}
