package com.example.myapplication;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Paint;

import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;


public class Game extends SurfaceView  implements Runnable{

    private Thread game_thread;
    private boolean isPlaying;
    static float screenX;
    public int screenY;
    private Paint paint;

    //Per te bere te pershtatshem per size te ndryshme
    private float ratio_X;
    private float ratio_Y;

    static boolean moveRight;
    static boolean moveLeft;
    static boolean moveUp;
    //Per te bere background to move.
    private Background background1,background2,background3;

    private Player my_player;

    //Create object
    static GameObject divan1,chair1,chair2;
    //Create an array to hold all object.
    GameObject[] object_array;


    //Krijo spriteSHeet
    private  spriteSheet player_animation;


    //Krijo buttonat
    private Button left_button,right_button,up_button,down_button;
    Button[] button_array;
    public  Game(Context context,int screenX,int screenY)
    {
        super(context);

        this.screenX = screenX;
        this.screenY = screenY;

        ratio_X = 1280f / screenX;
        ratio_Y = 720f / screenY;

        background1 = new Background(1280,screenY,getResources(),R.drawable.game_background);
        background2 = new Background(1280,screenY,getResources(),R.drawable.idle);
        background3 = new Background(1280,screenY,getResources(),R.drawable.game_background);


        background2.x = screenX;
        background3.x = 2*screenX;




    //Enter divan
        divan1 = new GameObject(50,500,400,200,getResources(),R.drawable.divan);
        chair1 = new GameObject(400,400,120,100,getResources(),R.drawable.chair3_front);
        chair2 = new GameObject(40,400,120,100,getResources(),R.drawable.chair3_front);

        //Shto buttonat
        left_button = new Button(150,500,150,150,getResources(),R.drawable.leftarray);
        right_button = new Button(380,500,150,150,getResources(),R.drawable.rightarray);
        up_button = new Button(1000,500,150,150,getResources(),R.drawable.uparray);
        down_button = new Button(1900,920,150,150,getResources(),R.drawable.downarray);
        button_array = new Button[]{left_button,right_button,up_button,down_button};


        //set size and location of player
        player_animation =  new spriteSheet(2981, 151, 24,1,R.drawable.left_to_right,getResources());//124



       my_player = new Player(140,300, 124,151,getResources());
       // my_player= new Player(100,300,player_animation);

        object_array = new GameObject[] {divan1,chair1,chair2};
        paint = new Paint();
    }

    public  void resume()
    {
        isPlaying = true;
        game_thread = new Thread(this);
        game_thread.start();
    }

    @Override
    public void run() {
        long lastTime=System.nanoTime();
        double nanoSecondConversion=1000000000.0/60;//60 frames per second
        double changeInSeconds=0;
        while(isPlaying)
        {

            long now=System.nanoTime();
            changeInSeconds +=(now-lastTime)/nanoSecondConversion;

            while(changeInSeconds>=1)
            {
                update();
                changeInSeconds=0;
            }


            draw();
            lastTime=now;

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



    private  void update() {

        my_player.playerUpdate(object_array);

        if(moveLeft)
        {
          Camera.checkLeft(my_player);

        }

        else if(moveRight)
        {

           Camera.checkRight(my_player);


        }

    }

    private  void draw()
    {
        if(getHolder().getSurface().isValid()) //if surface view is initiated
        {
            Canvas canvas = getHolder().lockCanvas();//return the current canvas displayed on the screen

            background1.draw_background(canvas);
            background2.draw_background(canvas);
            background3.draw_background(canvas);




            my_player.draw(canvas);



            for(int i = 0;i<object_array.length;i++)

                canvas.drawBitmap(object_array[i].object,object_array[i].Object_X() - Camera.offset_X,object_array[i].Object_Y() - Camera.offset_Y,paint);



            //draw buttons

            for(int i = 0;i<button_array.length;i++)
                canvas.drawBitmap(button_array[i].button,button_array[i].button_X(),button_array[i].button_Y(),null);

            //canvas ready to show on scrreen
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    //Calls eventListener on Touch for Testing

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        int maskedAction = event.getActionMasked();

        int pointerCount = event.getPointerCount();
        for(int i=0;i<pointerCount;i++)
        {
            maskedAction = event.getActionMasked();
            int pointerId = event.getPointerId(i);
            if(pointerId == 0)
            {
                if(maskedAction != MotionEvent.ACTION_UP)
                {
                    if (left_button.touched((int) event.getX(0), (int) event.getY(0)))
                    {

                        moveLeft = true;
                        moveRight = false;

    }
                    else if (right_button.touched((int) event.getX(0), (int) event.getY(0))) {



                        moveRight = true;
                        moveLeft = false;

                    }
                    else if (up_button.touched((int) event.getX(0), (int) event.getY(0))) {

                        moveRight = false;
                        moveUp = true;
                        moveLeft = false;

                    }

                }
                else
                {
                    moveUp = false;
                    moveLeft = false;
                    moveRight = false;

                }
            }
            else if(pointerId == 1)
            {
                if(maskedAction == MotionEvent.ACTION_POINTER_UP)
                {
                    if(up_button.touched((int)event.getX(i),(int)event.getY(i)))
                    {
                        moveUp = true;


                    }
                }
            }
        }
        return  true;

    }

}
