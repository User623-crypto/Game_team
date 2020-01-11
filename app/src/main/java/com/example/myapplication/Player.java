package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import static com.example.myapplication.R.drawable.*;

public class Player {


    /*************************************Pjesa e Perplasjes***********************************/
    //????
    int nr_i_vektorit=0;
    private  final int tolerance_range=-37;//Marzh gabimi
    private final int tolerance_rangeY=2;
    /*********************Pas Kesaj Punon*******************************************************/

    private final int gravity=10;
    private int velocityY=80;
    private int finalPosY=0;
    private int holding;
    private boolean finishjumping=true;



    private boolean didMoveRight=false;
    private boolean didMoveLeft=false;
    private int jumpCount=0;

    private int _velocityX=15;

    private float pos_X;
    private float pos_Y;
    private int width;
    private int height;
    private int trueWidth;

    private Game gameview;

    static boolean isMovingDown;//Nese eshte duke rene
    Bitmap sprite;
    int drawable_image; // Spritesheeti si imazh
    private spriteSheet _spriteSheet;

    public Player(float _X, float _Y, int width, int height, Resources res, int drawable_image,spriteSheet my_spriteSheet)
    {
        this.pos_X = _X;
        this.pos_Y = _Y;
        this.width = width;
        this.height = height;
        this.trueWidth=width/24;
        this.drawable_image = drawable_image;

        this._spriteSheet = my_spriteSheet;
        this.trueWidth = width/_spriteSheet.framecount();

        make_decode(res,drawable_image);


        this.holding = (int)pos_Y;

    }
    public  void make_decode(Resources res,int drawable_image)
    {
        sprite = BitmapFactory.decodeResource(res,drawable_image);
        sprite = Bitmap.createScaledBitmap(sprite,width,height,false);
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

    public void fall(GameObject gameObject[])
    {
        if(this.pos_Y+height<800  && !checkObjcollisionDown(gameObject))
        {
            jumpCount=1;
            pos_Y = pos_Y + gravity;
            _spriteSheet.set_Y_pos(pos_Y);
            set_player_y(pos_Y);


            finalPosY=((int)pos_Y-((velocityY*velocityY)/(2*gravity)));

        }
        /*
        else if(!finishjumping)
        {
           //my_jump(gameObject);
        }
        else {jumpCount=0;}
         */
    }

    public void jump()
    {
        jumpCount=1;
        if(pos_X<=finalPosY)
            finishjumping=true;

        if(!finishjumping)
            this.pos_Y= this.pos_Y - (velocityY - (2*gravity));

    }

    public  void move_right(GameObject gameObject[],int X_velocity)
    {


        Log.d("my_tag", "X_POS : "+ this.pos_X);
        Log.d("my_tag", "X_POS_SPRITESHEET : "+ _spriteSheet.X_pos());
        Log.d("my_tag", "X_POS_player_x() : "+ player_x());

        if(!checkObjcollisionRight(gameObject))
        {
            this.pos_X = this.pos_X + X_velocity/(int)_spriteSheet.fps;
            _spriteSheet.set_X_pos(this.pos_X);

        }
    }
    public  void  move_left(GameObject gameObject[],int X_velocity)
    {
        /*
        Log.d("my_tag", "X_POS : "+ this.pos_X);
        Log.d("my_tag", "X_POS_SPRITESHEET : "+ _spriteSheet.X_pos());
         */
        if(!checkObjcollisionLeft(gameObject))
        {
            Log.d("my_tag", "move_left: true");
            this.pos_X = this.pos_X - X_velocity/(int)_spriteSheet.fps;
            _spriteSheet.set_X_pos(this.pos_X);

        }
        else
            Log.d("my_tag", "move_left: false");

    }
    public void my_jump(GameObject gameObject[])
    {

                this.pos_Y = this.pos_Y - 800 / _spriteSheet.fps;
                _spriteSheet.set_Y_pos(this.pos_Y);

    }
    public  void move_up_right(GameObject gameObject[])
    {
                my_jump(gameObject);
                move_right(gameObject,100);

    }
    public  void move_up_left(GameObject gameObject[])
    {
        my_jump(gameObject);
        move_left(gameObject,100);
    }

    public int player_width()
    {
        return _spriteSheet.width();
    }
    public int player_height()
    {
        return _spriteSheet.height();
    }


    public  float player_x()
    {
        return pos_X;
    }
    public  float player_y()
    {
        return pos_Y;
    }
    public  void set_player_x(float _x)
    {
        this.pos_X = _x;
    }
    public  void  set_player_y(float _y)
    {
        this.pos_Y = _y;
    }

    public  void setcurrentframe(int frame)
    {
        _spriteSheet.setCurrentframe(frame);
    }



    public void playerUpdate(GameObject gameObject[],Resources res)
    {
        long my_time = System.currentTimeMillis();

        if(Game.moveRight == true && Game.moveUp == true)
        {
            if(this.drawable_image != jumpanim)
            {
                setcurrentframe(12);
                _spriteSheet.set_frameLengthinMillisecond(65);
                this.drawable_image = R.drawable.jumpanim;
                make_decode(res,drawable_image);
            }

            move_up_right(gameObject);
          /*
            if(!checkObjcollisionRight(gameObject))
                    move_up_right(gameObject);
            if(System.currentTimeMillis() - my_time > 200)
            {
                Game.moveUp = false;
                Game.moveRight = true;
            }
           */
        }

        else if(Game.moveLeft == true && Game.moveUp == true)
        {
            if(this.drawable_image != jumpanim)
            {
                _spriteSheet.setCurrentframe(11);
                _spriteSheet.set_frameLengthinMillisecond(55);
                this.drawable_image = R.drawable.jumpanim;

                make_decode(res,drawable_image);
            }

            move_up_left(gameObject);
        }

        else if(Game.moveUp == true && Game.moveRight == false && Game.moveLeft == false)
        {
            //jump();

            this.pos_Y = this.pos_Y - 1200 / _spriteSheet.fps;
            _spriteSheet.set_Y_pos(pos_Y);
        }
        else if(Game.moveRight == true && Game.moveUp == false) {
            if(this.drawable_image != left_to_right)
            {
                _spriteSheet.setCurrentframe(13);
                _spriteSheet.set_frameLengthinMillisecond(40);
                this.drawable_image = left_to_right;
                make_decode(res,drawable_image);
            }
            move_right(gameObject,100);
             if(System.currentTimeMillis() - my_time > 500)
             {
                 move_right(gameObject,300);
             }
        }

        else if(Game.moveLeft == true && Game.moveUp == false) {

            if(this.drawable_image != left_to_right)
            {
                _spriteSheet.setCurrentframe(11);
                _spriteSheet.set_frameLengthinMillisecond(40);
                this.drawable_image = left_to_right;
                make_decode(res,drawable_image);
            }

            move_left(gameObject,100);
        }
        else if(Game.moveRight == false && Game.moveLeft == false && Game.moveUp == false)
        {
            if(this.drawable_image != left_to_right)
            {
                _spriteSheet.setCurrentframe(11);
                _spriteSheet.set_frameLengthinMillisecond(40);
                this.drawable_image = left_to_right;
                make_decode(res,drawable_image);
            }
        }

        fall(gameObject);
        }



    /*******************************Deri Ketu Funksiononte**********************************/

    /*******************************OBJECT COLLISON CHECK ******************************************************/
    public boolean checkleft( GameObject a )
    {
        if(((this.pos_X <= (a.Object_X() + a.getWidth() + tolerance_range)) || ((this.pos_X + tolerance_range) > a.Object_X())) && (((this.pos_Y + this.height) > a.Object_Y()) || (this.pos_Y < (a.Object_Y() + a.getHeight()))))
            return true;
        return false;
    }

    public boolean checkright(GameObject a)
    {
        return (this.pos_X + this.width + tolerance_range) > a.Object_X() && ((this.pos_X) <= a.Object_X() + tolerance_range) && (this.pos_Y + this.height) > a.Object_Y() && this.pos_Y < (a.Object_Y() + a.getHeight());
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
            if(checkleft(a[i])){
                return true;}}

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
