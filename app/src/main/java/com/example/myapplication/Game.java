package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class Game extends SurfaceView  implements Runnable{
      /*******************************************************************/
      private int control=5;
/*************************************************************************/
    //Sprite sheet
    private boolean isMoving=true;
    private float runSpeedPerSecond = 200;
    private float playerXpos;//poszicioni i x ku nis levizja
    private float playerYpos;//poszicioni i y ku nis levizja
    public static int frameWidth = 180,frameHeight = 260;
    private int frameCount = 24; //Ndahet  Spritesheet ne 12 pjese te barabarta
    private int currentFrame = 12;
    private long fps;
    private long timeThisFrame;
    private long lastFrameChangeTime = 0;
    private int frameLengthinMillisecond = 20; //Shpejtesia e levizjes se objektit . P.sh Levizin me shpejt kembet
    //Percakton size e frames qe do te merret

    private Rect frametoDraw = new Rect(0,0,frameWidth,frameHeight);//Ku fillon ndarja e framave. Eshte frame qe shfaqet qe shohim ne.nese e vendos psh top : 200 ath zdhuket

    //Poicioni se ku do shfaqet frama
    private RectF wheretoDraw = new RectF(playerXpos,playerYpos,playerXpos+frameWidth,frameHeight);
    private Thread game_thread;
    private boolean isPlaying;
    private int screenX;
    private int screenY;
    private Paint paint;

    //Per te bere te pershtatshem per size te ndryshme
    private float ratio_X;
    private float ratio_Y;

    private boolean moveRight;
    private boolean moveLeft;
    //Per te bere background to move.
    private Background background1,background2;

    private Player my_player;
    //Create object
    private GameObject my_wall,my_wall1;
    //Create an array to hold all object.
    GameObject[] object_array;
    public  Game(Context context,int screenX,int screenY)
    {
        super(context);

        this.screenX = screenX;
        this.screenY = screenY;

        ratio_X = 720f / screenX;
        ratio_Y = 1280f / screenY;

        background1 = new Background(screenX,2*screenY,getResources(),R.drawable.idle);
        background2 = new Background(screenX,2*screenY,getResources(),R.drawable.idle_reverse);

        //Background 2 nuk do te shfaqet ne ekran por ai do te jete pas background 1

        background2.x = screenX;


        my_wall = new GameObject(500,1080-100,900,100,getResources());
        my_wall1 = new GameObject(800,600,900,100,getResources());


        my_player = new Player(100,100,frameWidth*frameCount,frameHeight,getResources());//Frame count duhet .

        playerXpos = my_player.player_X();
        playerYpos = my_player.player_Y();
        object_array = new GameObject[] {my_wall,my_wall1};
        paint = new Paint();
    }


    //Return background x , y;
    public int background_X()
    {
        return background1.x;
    }

    public int background_Y()
    {
        return background1.y;
    }
    public  void resume()
    {
        isPlaying = true;
        game_thread = new Thread(this);
        game_thread.start();
    }

    @Override
    public void run() {

        while(isPlaying)
        {
            long startFrameTime = System.currentTimeMillis();


            draw();
            update();
            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if(timeThisFrame >=1)
            {

               fps = 1000/timeThisFrame;



            }
            try {
                game_thread.sleep(7);
            }catch (Exception e){}



        }
    }

    public  void pause()
    {
        try {
            isPlaying = false;
            game_thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void manageCurrentFrame()
    {
        long time = System.currentTimeMillis();
        if(isMoving)
        {
            if(time > lastFrameChangeTime + frameLengthinMillisecond)
            {
                lastFrameChangeTime = time;
                if(moveRight == true)
                {
                    currentFrame++;
                    if(currentFrame >= frameCount)
                    {
                        currentFrame = 13;

                    }
                }

                if(moveLeft == true)
                {
                    currentFrame--;
                    if(currentFrame < 1)
                    {
                        currentFrame = 11;

                    }
                }
            }
        }


        frametoDraw.left = currentFrame*frameWidth;

        frametoDraw.right = frametoDraw.left + frameWidth;
    }

    private  void update()
    {

        my_player.playerUpdate(object_array,5);
        if(moveRight)
        {
            my_player.playerUpdate(object_array,1);

            if(my_player.didMoveRight()) {
                for (int i = 0; i < object_array.length; i++) {
                    object_array[i].move_left();
                }
                background1.updateRight(my_player.player_X());
                background2.updateRight(my_player.player_X());
                /*if (my_player.player_X() < background1.my_background.getHeight() / 2) {
                    background1.x -= 10;
                    background2.x -= 10;
                }

                if (my_player.player_X() > background1.my_background.getHeight() / 2) {
                    background1.x -= 12;
                    background2.x -= 12;
                }
                if (background1.x + background1.my_background.getWidth() < 0) {
                    //call background 1
                    background1.x = screenX + 5;
                }
                if (background2.x + background2.my_background.getWidth() < 0) {
                    //call background 1
                    background2.x = screenX + 5;
                }*/
            }

        }else if(moveLeft) {

            my_player.playerUpdate(object_array,2);

            for (int i = 0; i < object_array.length; i++) {
                //object_array[i].move_right();
            }

        }




        //Per te leviz poshte

        /*
         {

            playerYpos -= 10;



            if(background1.y == background1.my_background.getHeight())
            {

                background1.y -= 0;
                background2.y -= 0;
            }
            //background1.x -= 20;
            //background2.x -= 20;

            if(background1.y > -background1.my_background.getHeight()/2) {
                background1.y -= 10*ratio_X;
                background2.y -= 10*ratio_X;
                for(int i = 0;i<object_array.length;i++)
                {
                    //object_array[i].move_left();
                    object_array[i].move_up();
                }

            }


        }

         */

    }

    private  void draw()
    {
        if(getHolder().getSurface().isValid()) //if surface view is initiated
        {
            Canvas canvas = getHolder().lockCanvas();//return the current canvas displayed on the screen

            synchronized (canvas) {

                canvas.drawBitmap(background1.my_background, background1.x, background1.y, paint);
                canvas.drawBitmap(background2.my_background, background2.x, background2.y, paint);

            /*
        //Draw anim
            //wheretoDraw.set((int)playerXpos,(int)playerYpos,(int)playerXpos  + frameWidth,(int)playerYpos + frameHeight);

            //draw every frame on screen
            //manageCurrentFrame();
            //canvas.drawBitmap(my_player.sprite,frametoDraw,wheretoDraw,null);*/
                my_player.draw(canvas);


                //draw wall
                canvas.drawBitmap(my_wall.object, my_wall.Object_X(), my_wall.Object_Y(), paint);
                canvas.drawBitmap(my_wall1.object, my_wall1.Object_X(), my_wall1.Object_Y(), paint);
                //canvas ready to show on scrreen
            }
            getHolder().unlockCanvasAndPost(canvas);
        }
    }




    public  boolean isPlaying()
    {
        return isPlaying;
    }

    //Calls eventListener on Touch for Testing

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {


        //If the user Touches the screen
        if(event.getAction()!=MotionEvent.ACTION_UP)
        {

            if((int)event.getX()>this.getWidth()/2)
            {moveRight=true;//my_player.moveRight=true;

                isMoving = true;//my_player.isMoving=true;
                moveLeft = false;//my_player.moveLeft=false;
                control=1;
                }

            if((int)event.getX()<=this.getWidth()/2)
            {
                moveLeft=true;//my_player.moveLeft=true;
                isMoving=true;//my_player.isMoving=true;
                moveRight=false;//my_player.moveRight=false;
                control=2;
            }
            return true;
        }
        else {moveRight=false;moveLeft=false;control=5; //my_player.moveRight=false;my_player.moveLeft=false;
            // currentFrame  = 13;
            return false;}

    }

}
