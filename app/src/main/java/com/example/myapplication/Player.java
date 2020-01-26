package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;


import static com.example.myapplication.R.drawable.*;

public class Player {

    //
    int leftPadding = 50;
    int rightPadding = 40;
    int upPadding = 0;
    int downPadding = 10;
    /****************************Animim*//////////////////////////////
    int testCount = 0;

    float runSpeed = 60 / 40;//shpejtesia
    /*************************************Pjesa e Perplasjes***********************************/
    //????
    private int nr_i_vektorit = 0;
    private final int tolerance_range = 10;//Marzh gabimi
    private final int tolerance_rangeY = 10;
    /*********************Pas Kesaj Punon*******************************************************/


    private boolean didMoveRight = false;
    private boolean didMoveLeft = false;

    private float pos_X;
    private float pos_Y;
    private int width;
    private int height;
    private int vSpeed = 0; /*Vertical speed needed  when jump*/
    private int acceleration = 1;
    private int x_acceleration = 2;
    private int hSpeed = 0; //horizontal speed
    private int maxSpeed = 20;


    static boolean isMovingDown;//Nese eshte duke rene

    private spriteSheet _spriteSheet;

    private int row = 0;
    private int col = 0;


    public Player(float _X, float _Y, spriteSheet my_spriteSheet) {
        this.pos_X = _X;
        this.pos_Y = _Y;

        this.width = my_spriteSheet.width();
        this.height = my_spriteSheet.height();

        this._spriteSheet = my_spriteSheet;


    }

    public Player(float _X, float _Y, int width, int height, Resources res) {
        this.pos_X = _X;
        this.pos_Y = _Y;
        this.width = width;
        this.height = height;
        this._spriteSheet = new spriteSheet(width, height, 10, 4, player_sprite, res, true);//124


    }

    private float imgBoundR() {
        return pos_X + width - rightPadding;
    }

    private float imgBoundL() {
        return pos_X + leftPadding;
    }

    private float imgBoundU() {
        return pos_Y + upPadding;
    }

    private float imgBoundD() {
        return pos_Y + height - downPadding;
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
        if (this.pos_Y + height < (2 * Game.screenY) && !checkObjcollisionDown(gameObject)) {

            pos_Y = pos_Y + vSpeed;
            vSpeed = vSpeed + acceleration;

            if (!checkObjcollisionDown(gameObject))
                Camera.checkDown(this);

        }
    }


        public void move_right (GameObject gameObject[] ,float angle)
        {

            if (!checkObjcollisionRight(gameObject)) {
                pos_X = pos_X + hSpeed;
                if(hSpeed < maxSpeed)
                    hSpeed = hSpeed + x_acceleration;

            }
            else{
                hSpeed = 0;
            }

        }
        public void move_left ( final GameObject gameObject[])
        {

            if (!checkObjcollisionLeft(gameObject)) {
                pos_X = pos_X - hSpeed;
                if(hSpeed < maxSpeed)
                    hSpeed = hSpeed + x_acceleration;

            }
            else
            {
                hSpeed = 0;
            }

        }
        public void my_jump (GameObject gameObject[],int jumpHeight)
        {


            if (!checkObjcollisionUp(gameObject) && checkObjcollisionDown(gameObject) == true) {

                vSpeed = -jumpHeight;
                pos_Y = pos_Y + vSpeed;
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

                animatePlayerUpRight();
                move_up_right(gameObject,20,20);

            } else if (Game.moveLeft == true && Game.moveUp == true) {
                animatePlayerUpLeft();
                move_up_left(gameObject,20,20);
            } else if (Game.moveUp == true && Game.moveRight == false && Game.moveLeft == false) {
                //jump();
                my_jump(gameObject, 15);
            } else if (Game.moveRight == true && Game.moveUp == false) {

                if (testCount > runSpeed) {
                    animatePlayerRight();
                    testCount = 0;

                }

                move_right(gameObject, 30);


            } else if (Game.moveLeft == true && Game.moveUp == false) {

                if (testCount > runSpeed) {
                    animatePlayerLeft();
                    testCount = 0;

                }

                move_left(gameObject);

            } else if (!Game.moveRight && !Game.moveLeft && !Game.moveUp) {
                row = 0;
                col = 0;

            }

            fall(gameObject);
        }


        /********************Animimi****************************************/
        private void animatePlayerRight ()
        {
            runSpeed = 60 / 40;
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
        private void animatePlayerLeft ()
        {
            runSpeed = 60 / 40;
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

        private void animatePlayerUpRight ()
        {
            runSpeed = 60 / 60;
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

        private void animatePlayerUpLeft ()
        {
            runSpeed = 60 / 30;
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
