package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;


import com.example.myapplication.Forms.AABB;


import static com.example.myapplication.R.drawable.*;

public class Player extends AABB {

    //
    int leftPadding = 20;
    int rightPadding = 40;
    int upPadding = 0;
    int downPadding = 10;
    /****************************Animim*//////////////////////////////
    int testCount = 0;
    float runtime=60/30f;float frameSpeed=60/30f; int frameCount=0; int normalCount=0; float frameSpeedR,frameSpeedL,frameSpeedUR,frameSpeedUL;
    float runspeedR=frameSpeedR=60/30f; float runspeedL=frameSpeedL=60/40f; float runspeedUR=frameSpeedUR=60/20f; float runspeedUL=frameSpeedUL=60/20f;
    float runSpeed = 60 / 40;//shpejtesia
    /*************************************Pjesa e Perplasjes***********************************/
    //????
    private int nr_i_vektorit = 0;
    private final int tolerance_range = 15;//Marzh gabimi
    private final int tolerance_rangeY = 8;
    /*********************Pas Kesaj Punon*******************************************************/
    public boolean movedRight=false;
    public boolean movedLeft=false;public boolean movedUp=false;public  boolean movedDown=false;


    /*****************************************************************************************/

    private boolean didMoveRight = false;
    private boolean didMoveLeft = false;

    private float pos_X,nextPosX;
    private float pos_Y,nextPosY;
    private int width;
    private int height;
    private int vSpeed = 0; /*Vertical speed needed  when jump*/
    private int acceleration = 1;
    private int x_acceleration = 2;
    private int hSpeed = 0; //horizontal speed
    private int maxSpeed = 20;
    private float tempX;
    private float tempY;


    static boolean isMovingDown;//Nese eshte duke rene

    private spriteSheet _spriteSheet;

    private int row = 0;
    private int col = 0;


    public Player(float _X, float _Y, spriteSheet my_spriteSheet) {
        super(_X,_Y,my_spriteSheet.width(),my_spriteSheet.height());
        this.pos_X = nextPosX=_X;
        this.pos_Y =nextPosY= _Y;

        this.width = my_spriteSheet.width();
        this.height = my_spriteSheet.height();

        this._spriteSheet = my_spriteSheet;


    }

    public Player(float _X, float _Y, int width, int height, Resources res) {
        super(_X,_Y,width,height);
        this.pos_X = nextPosX=_X;
        this.pos_Y =nextPosY= _Y;
        this.width = width;
        this.height = height;
        this._spriteSheet = new spriteSheet(width, height, 10, 4, player_sprite, res, true);//124


    }

    private float imgBoundR() {
        return nextPosX + width - rightPadding;
    }

    private float imgBoundL() {
        return nextPosX + leftPadding;
    }

    private float imgBoundU() {
        return nextPosY + upPadding;
    }

    private float imgBoundD() {
        return nextPosY + height - downPadding;
    }
/*


    public boolean didMoveRight() {
        return didMoveRight;
    }

    public boolean didMoveLeft() {
        return didMoveLeft;
    }

 */
    private void fall(GameObject gameObject[]) {
        tempY=pos_Y;
        nextPosY = pos_Y + vSpeed;
        vSpeed = vSpeed + acceleration;
        if (this.pos_Y + height < (2 * Game.screenY) && !checkObjcollisionDown(gameObject)) {
                movedDown=true;
            //pos_Y = pos_Y + vSpeed;
           // vSpeed = vSpeed + acceleration;
                pos_Y=nextPosY;
            if (!checkObjcollisionDown(gameObject))
                Camera.checkDown(this);

        }
        else{
            //tempY=gameObject[nr_i_vektorit].Object_Y()-height;
            movedDown=false;
            pos_Y=tempY;


        }
    }


        public void move_right (GameObject gameObject[] ,float angle)
        {
                tempX=pos_X;

            nextPosX = pos_X + hSpeed;
            //nextPosX=pos_X;
            if(hSpeed < maxSpeed)
                hSpeed = hSpeed + x_acceleration;
            if (!checkObjcollisionRight(gameObject)) {
                movedRight=true;
                /*pos_X = pos_X + hSpeed;
                if(hSpeed < maxSpeed)
                    hSpeed = hSpeed + x_acceleration;*/
              //  Log.d("tag", "Duhet te japi jo"+String.valueOf(!checkObjcollisionRight(gameObject)));
                pos_X=nextPosX;
            }
            else{
                Log.d("tag","duhet te japi po"+ String.valueOf(checkObjcollisionRight(gameObject)));
                hSpeed = 0;
                movedRight=false;
                pos_X=tempX;
                nextPosX=pos_X;


            }

        }
        public void move_left ( final GameObject gameObject[])
        {
                tempX=pos_X;
                nextPosX=pos_X;
            //movedLeft=true;
            nextPosX = pos_X - hSpeed;
            //nextPosX=pos_X;
            if(hSpeed < maxSpeed)
                hSpeed = hSpeed + x_acceleration;
            if (!checkObjcollisionLeft(gameObject)) {
                movedLeft=true;
               /* pos_X = pos_X - hSpeed;
                if(hSpeed < maxSpeed)
                    hSpeed = hSpeed + x_acceleration;*/
               pos_X=nextPosX;

            }
            else
            {
                pos_X=tempX;
                nextPosX=pos_X;
                hSpeed = 0;
            }

        }
        public void my_jump (GameObject gameObject[],int jumpHeight)
        {

                tempY=pos_Y;
            vSpeed = -jumpHeight;
            nextPosY = pos_Y + vSpeed;

            if (!checkObjcollisionUp(gameObject) && checkObjcollisionDown(gameObject) == true) {
                movedUp=true;

               /* vSpeed = -jumpHeight;
                pos_Y = pos_Y + vSpeed;*/
               pos_Y=nextPosY;
            }
            else {
                pos_Y=tempY;
                nextPosY=pos_Y;

            }

        }
        public void move_up_right (GameObject gameObject[],int jumpHeight,int x_velocity)
        {

            my_jump(gameObject, jumpHeight);

            move_right(gameObject, 0);


        }
        public void move_up_left (GameObject gameObject[],int jumpHeight,int x_velocity)
        {
            my_jump(gameObject, jumpHeight);
            move_left(gameObject);
        }

/*

        public int player_width ()
        {
            return width;
        }
        public int player_height ()
        {
            return height;
        }

 */

        public float player_x ()
        {
            return pos_X;
        }
        public float player_y ()
        {
            return pos_Y;
        }


        public void draw (Canvas canvas)
        {


            //Draw anim
            //_spriteSheet.wheretoDraw().set((int)this.pos_X,(int)this.pos_Y,(int)this.pos_X  + _spriteSheet.width(),(int)this.pos_Y + _spriteSheet.height());

            //draw every frame on screen
            //_spriteSheet.manageCurrentFrame();

            //nuk e vizaton gjitheimazhin per nje pjese te saj
            //canvas.drawBitmap(this.sprite,_spriteSheet.frametoDraw(),_spriteSheet.wheretoDraw(),null);
            canvas.drawBitmap(_spriteSheet.returnSprite(row, col), this.pos_X - Camera.offset_X, this.pos_Y - Camera.offset_Y, null);


        }

        public void playerUpdate (GameObject gameObject[])
        {


            if (checkObjcollisionDown(gameObject) == true) //Nese bie ne toke e ben shpejtesine vertikale 0 ;
                vSpeed = 0;
           /* if (testCount > 60){
                testCount = 0;}*/
            testCount++;
            if (Game.moveRight == true && Game.moveUp == true) {

                animatePlayerUpRight1();
                move_up_right(gameObject,20,20);

            } else if (Game.moveLeft == true && Game.moveUp == true) {
                animatePlayerUpLeft1();
                move_up_left(gameObject,20,20);
            } else if (Game.moveUp == true && Game.moveRight == false && Game.moveLeft == false) {
                //jump();
                my_jump(gameObject, 15);
            } else if (Game.moveRight == true && Game.moveUp == false) {

                animatePlayerRight1();

                move_right(gameObject, 30);


            } else if (Game.moveLeft == true && Game.moveUp == false) {

                animatePlayerLeft1();

                move_left(gameObject);

            } else if (!Game.moveRight && !Game.moveLeft && !Game.moveUp) {
                row = 0;
                col = 0;
                frameCount=0;
                normalCount=0;
            }

             fall(gameObject);
        }


        /********************Animimi****************************************/
        private void animatePlayerRight ()
        {
            col = 0;
            if (row < 9) {
                row++;
            }
        /*
        if (row < 23)
            row++;
         */
            else {
                row = 0;
            }
        }

        /*E rendesishme ka nevoje per limit */
    private void animatePlayerRight1()
    {
        if(testCount>=runspeedR) {
            frameCount++;
            normalCount++;

            if(frameCount*frameSpeedR<=normalCount)
            {runspeedR=(int)frameSpeedR;}
            else
                runspeedR=frameSpeedR;

            animatePlayerRight();
            testCount=0;

        }else
        {
            normalCount++;
        }
    }


        private void animatePlayerLeft ()
        {

            col = 1;
            if (row < 9) {
                row++;
            }
        /*
        if (row < 23)
            row++;
         */
            else {
                row = 0;
            }

        }
    private void animatePlayerLeft1()
    {
        if(testCount>=runspeedL) {
            frameCount++;
            normalCount++;

            if(frameCount*frameSpeedL<=normalCount)
            {runspeedL=(int)frameSpeedL;}
            else
                runspeedL=frameSpeedL;

            animatePlayerLeft();
            testCount=0;

        }else
        {
            normalCount++;
        }
    }


    private void animatePlayerUpRight ()
        {

            col = 2;
            if (row < 9) {
                row++;

            }
        /*
        if (row < 23)
            row++;
         */
            else {

                Game.moveUp = false;
                row = 0;
            }
        }

    private void animatePlayerUpRight1()
    {
        if(testCount>=runspeedUR) {
            frameCount++;
            normalCount++;

            if(frameCount*frameSpeedUR<=normalCount)
            {runspeedUR=(int)frameSpeedUR;}
            else
                runspeedUR=frameSpeedUR;

            animatePlayerUpRight();
            testCount=0;

        }else
        {
            normalCount++;
        }
    }

        private void animatePlayerUpLeft ()
        {

            col = 3;
            if (row < 9) {
                row++;
            }
        /*
        if (row < 23)
            row++;
         */
            else {
                Game.moveUp = false;
                row = 0;
            }
        }
    private void animatePlayerUpLeft1()
    {
        if(testCount>=runspeedUL) {
            frameCount++;
            normalCount++;

            if(frameCount*frameSpeedUL<=normalCount)
            {runspeedUL=(int)frameSpeedUL;}
            else
                runspeedUL=frameSpeedUL;

            animatePlayerUpLeft();
            testCount=0;

        }else
        {
            normalCount++;
        }
    }



    /*******************************Deri Ketu Funksiononte**********************************/

    /*******************************OBJECT COLLISON CHECK ******************************************************/
    public boolean checkleft( GameObject a )
    {
        return imgBoundL() <= (a.Object_X() + a.getWidth()) && (imgBoundR() > a.Object_X()) && (imgBoundD()) > a.Object_Y() && imgBoundU() < (a.Object_Y() + a.getHeight());
    }

    public boolean checkright(GameObject a)
    {
        return (imgBoundR()) >= a.Object_X() && (imgBoundL() < a.Object_X()+a.getWidth()) && (imgBoundD()) > a.Object_Y() && imgBoundU() < (a.Object_Y() + a.getHeight());
    }

    public boolean checkup(GameObject a)
    {
        return imgBoundU() <= (a.Object_Y() + a.getHeight()) && ((imgBoundD()) > a.Object_Y()) && (imgBoundR() > a.Object_X() && imgBoundL() < (a.Object_X() + a.getWidth()));
    }

    public boolean checkdown(GameObject a)
    {

        if((imgBoundD())>=(a.Object_Y()) && ((imgBoundU())<a.Object_Y()) && (imgBoundR()>a.Object_X() && imgBoundL()<(a.Object_X()+a.getWidth())))
            return true;

        return false;
    }
   /*public boolean checkleft(GameObject a)
   {
       return super.contains(a);
   }
    public boolean checkright(GameObject a)
    {
        if(super.contains(a))
        {
            Log.d("Objekti", "Pozicioni x,y: "+String.valueOf(a.Object_X())+","+String.valueOf(a.Object_Y())+" width+ height:"+String.valueOf(a.getWidth())+","+String.valueOf(a.getHeight()));
            Log.d("Lojtari", "x y w h: "+String.valueOf(pos_X)+" "+String.valueOf(pos_Y)+" "+String.valueOf(width)+" "+String.valueOf(height)+" ");
            return  true;
        }else{return  false;}
    }
    public boolean checkup(GameObject a)
    {
        return super.contains(a);
    }
    public boolean checkdown(GameObject a)
    {
        return super.contains(a);
    }*/



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
