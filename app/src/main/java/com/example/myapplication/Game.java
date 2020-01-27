package com.example.myapplication;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Paint;

import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.example.myapplication.Forms.AABB;


public class Game extends SurfaceView  implements Runnable{

    private Thread game_thread;
    private boolean isPlaying;
    static float screenX;
    static int screenY;
    static int scene_height;
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
    static GameObject darkred_shelf,halfyellow_shelf,horizontal_shelf,horizontal_shelf1,grandfather_clock,second_table,under_clock,before_clock,blockelement,blockelement1,lamp,dinning_table,rightchair,overtable,overtable2,overtable3,floor,floor2,floor3;
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

        background1 = new Background(screenX,2*screenY,getResources(),R.drawable.game_background);
        background2 = new Background(screenX,2*screenY,getResources(),R.drawable.idle);
        background3 = new Background(screenX,2*screenY,getResources(),R.drawable.game_background);

        scene_height = 2*screenY;
        background2.x = screenX;
        background3.x = 2*screenX;




        floor = new GameObject(0,scene_height - 80/ratio_Y, screenX,50/ratio_Y,getResources(),R.drawable.wood1);
        floor2 = new GameObject(screenX,floor.Object_Y(), screenX,50/ratio_Y,getResources(),R.drawable.wood1);
        floor3 = new GameObject(2*screenX,floor.Object_Y(), screenX,50/ratio_Y,getResources(),R.drawable.wood1);
        //All objects
        darkred_shelf = new GameObject(0,scene_height - 700/ratio_Y, 40/ratio_X,350/ratio_Y,getResources(),R.drawable.shelf_darkred);
        halfyellow_shelf = new GameObject(0,(darkred_shelf.Object_Y() - 580/ratio_Y)/ratio_Y, 100/ratio_X,400/ratio_Y,getResources(),R.drawable.shelf_halfyellow);
        horizontal_shelf = new GameObject(100/ratio_X,(halfyellow_shelf.Object_Y())/ratio_Y, 150/ratio_X,30/ratio_Y,getResources(),R.drawable.shelf_halfyellow);
        horizontal_shelf1 = new GameObject(100/ratio_X,(horizontal_shelf.Object_Y() + 170/ratio_Y)/ratio_Y, 150/ratio_X,30/ratio_Y,getResources(),R.drawable.shelf_halfyellow);
        second_table = new GameObject(darkred_shelf.Object_X() + 160/ratio_X,floor.Object_Y() - 220/ratio_Y, 300/ratio_X,220/ratio_Y,getResources(),R.drawable.second_image);
        lamp = new GameObject(second_table.Object_X() + second_table.getWidth() - 60/ratio_X,second_table.Object_Y() - 90/ratio_Y, 60/ratio_X,90/ratio_Y,getResources(),R.drawable.shelf_darkred);
        under_clock = new GameObject(920/ratio_X,floor.Object_Y() - 80/ratio_Y, 320/ratio_X,80/ratio_Y,getResources(),R.drawable.grandfather_clock);
        grandfather_clock = new GameObject(under_clock.Object_X() + 110/ratio_X,under_clock.Object_Y() - 500/ratio_Y, 90/ratio_X,500/ratio_Y,getResources(),R.drawable.grandfather_clock);
        before_clock = new GameObject(720/ratio_X,floor.Object_Y() - 220/ratio_Y, 100/ratio_X,80/ratio_Y,getResources(),R.drawable.before_clock);
        blockelement = new GameObject(grandfather_clock.Object_X() - 120/ratio_X,before_clock.Object_Y() - 140/ratio_Y, 120/ratio_X,40/ratio_Y,getResources(),R.drawable.before_clock);
        blockelement1 = new GameObject(blockelement.Object_X() - 200/ratio_X,blockelement.Object_Y() - 140/ratio_Y, 120/ratio_X,40/ratio_Y,getResources(),R.drawable.before_clock);

        dinning_table = new GameObject(1400/ratio_X,floor.Object_Y() - 380/ratio_Y, 800/ratio_X,380/ratio_Y,getResources(),R.drawable.dinning_table_final);
        rightchair = new GameObject(dinning_table.Object_X() - 35/ratio_X,floor.Object_Y() - 375/ratio_Y, 160/ratio_X,375/ratio_Y,getResources(),R.drawable.rightchair);

        //Over table
        overtable = new GameObject(dinning_table.Object_X() + 250/ratio_X,dinning_table.Object_Y() - 80/ratio_Y , 300/ratio_X,80/ratio_Y,getResources(),R.drawable.wood1);
        overtable2 = new GameObject(overtable.Object_X() + 100/ratio_X,overtable.Object_Y() - 80/ratio_Y , 200/ratio_X,80/ratio_Y,getResources(),R.drawable.wood1);
        overtable3 = new GameObject(overtable2.Object_X() + 100/ratio_X,overtable2.Object_Y() - 80/ratio_Y , 100/ratio_X,80/ratio_Y,getResources(),R.drawable.wood1);
        //Shto buttonat
        left_button = new Button((int)(100/ratio_X),(int)(screenY - 100/ratio_Y), (int) (100/ratio_X),(int)(100/ratio_Y),getResources(),R.drawable.leftarray);
        right_button = new Button((int)(300/ratio_X),(int)(screenY - 100/ratio_Y),(int)(100/ratio_X), (int) (100/ratio_Y),getResources(),R.drawable.rightarray);
        up_button = new Button((int)(screenX-400/ratio_X),(int)(screenY - 100/ratio_Y),(int)(100/ratio_X),(int)(100/ratio_Y),getResources(),R.drawable.uparray);
        down_button = new Button((int)(screenX - 200/ratio_X),(int)(screenY - 100/ratio_Y),(int)(100/ratio_X),(int)(100/ratio_Y),getResources(),R.drawable.downarray);
        button_array = new Button[]{left_button,right_button,up_button,down_button};


        //set size and location of player
        //player_animation =  new spriteSheet(2981, 151, 10,4,R.drawable.player_sprite,getResources());//124



        my_player = new Player(500,100, (int)(124/ratio_X),(int)(151/ratio_Y),getResources());
        // my_player= new Player(100,300,player_animation);

        object_array = new GameObject[] {floor,floor2,floor3,darkred_shelf,halfyellow_shelf,horizontal_shelf,horizontal_shelf1,grandfather_clock,second_table,under_clock,before_clock,blockelement,blockelement1,lamp,dinning_table,rightchair,overtable,overtable2,overtable3};
        //darkred_shelf,halfyellow_shelf,horizontal_shelf,horizontal_shelf1,grandfather_clock,second_table,under_clock,before_clock,blockelement,blockelement1,lamp,dinning_table,rightchair,overtable,overtable2,overtable3,
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
            Log.d("tag3", "run: "+String.valueOf(my_player.contains(new AABB(0,1360,1384,80))));

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
            if(didMove())
          Camera.checkLeft(my_player);

        }

        else if(moveRight)
        {
            if (didMove())
           Camera.checkRight(my_player);


        }

        else if(moveUp)
        {
            if (didMove())
            Camera.checkUp(my_player);
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

    public boolean didMove()
    {
        return my_player.movedDown||my_player.movedRight||my_player.movedUp||my_player.movedLeft;
    }


}
