package com.example.myapplication;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Paint;

import android.view.MotionEvent;
import android.view.SurfaceView;


public class Game extends SurfaceView  implements Runnable{

    private Thread game_thread;
    private boolean isPlaying;
    private int screenX;
    private int screenY;
    private Paint paint;

    //Per te bere te pershtatshem per size te ndryshme
    private float ratio_X;
    private float ratio_Y;

    static boolean moveRight;
    static boolean moveLeft;
    static boolean moveUp;
    //Per te bere background to move.
    private Background background1,background2;

    private Player my_player;

    //Create object
    private GameObject my_wall,my_wall1,my_wall2,my_wall3,my_wall4,my_wall5,my_wall6,my_wall7,my_wall8,my_wall9;
    private GameObject divan1,chair1;
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

        background1 = new Background(5*screenX,2*screenY,getResources(),R.drawable.game_background);


        my_wall = new GameObject(50,500,900,60,getResources(),R.drawable.wall);
        /*
        my_wall1 = new GameObject(950,300,900,60,getResources());
        my_wall2 = new GameObject(1850,500,900,60,getResources());
        my_wall3 = new GameObject(2800,500,900,60,getResources());
        my_wall4 = new GameObject(3700,500,500,60,getResources());
        my_wall5 = new GameObject(4250,500,50,60,getResources());
        my_wall6 = new GameObject(4500,700,50,60,getResources());

        my_wall7 = new GameObject(4700,300,150,60,getResources());
        my_wall8 = new GameObject(5000,200,50,60,getResources());
        my_wall9 = new GameObject(5400,500,500,60,getResources());
         */

    //Enter divan
        divan1 = new GameObject(50,500,400,200,getResources(),R.drawable.divan);
        chair1 = new GameObject(500,600,120,100,getResources(),R.drawable.chair3_front);

        //Shto buttonat
        left_button = new Button(150,500,150,150,getResources(),R.drawable.leftarray);
        right_button = new Button(380,500,150,150,getResources(),R.drawable.rightarray);
        up_button = new Button(1000,500,150,150,getResources(),R.drawable.uparray);
        down_button = new Button(1900,920,150,150,getResources(),R.drawable.downarray);
        button_array = new Button[]{left_button,right_button,up_button,down_button};


        //set size and location of player
        player_animation =  new spriteSheet(100, 200, 80, 140, 24);


        my_player = new Player((int)player_animation.X_pos(),(int)player_animation.Y_pos(), 24 * player_animation.width(),player_animation.height(),getResources(),R.drawable.left_to_right,player_animation);//Frame count duhet .

        object_array = new GameObject[] {divan1,chair1};
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

        while(isPlaying)
        {
            long startFrameTime = System.currentTimeMillis();

            update();
            draw();
            //sleep();
            player_animation.on_run(startFrameTime);

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

        float before_posX = my_player.player_x();
        float before_posY = my_player.player_y();



        float after_posX = my_player.player_x();
        float after_posY = my_player.player_y();

        int X_amount = (int)(after_posX - before_posX);
        int Y_amount = (int)(after_posY - before_posY);
        //background1.update_bakground(X_amount,Y_amount,my_player);
        //background2.update_bakground(X_amount,Y_amount,my_player);
        for(int i = 0;i<object_array.length;i++)
            object_array[i].update_object(X_amount,Y_amount,my_player);


        my_player.playerUpdate(object_array,getResources());

    }

    private  void draw()
    {
        if(getHolder().getSurface().isValid()) //if surface view is initiated
        {
            Canvas canvas = getHolder().lockCanvas();//return the current canvas displayed on the screen
            canvas.drawBitmap(background1.my_background,background1.x,background1.y,paint);
            //canvas.drawBitmap(background2.my_background,background2.x,background2.y,paint);


        //Draw anim
/*


            player_animation.manageCurrentFrame();
            player_animation.wheretoDraw().set((int)player_animation.X_pos(),(int)player_animation.Y_pos(),(int)player_animation.X_pos() + my_player.player_width(),(int)player_animation.Y_pos() + my_player.player_height());
            //draw every frame on screen

            canvas.drawBitmap(my_player.sprite,player_animation.frametoDraw(),player_animation.wheretoDraw(),null);
 */

//Draw i Roelit :)
            my_player.draw(canvas);

            //draw wall
            for(int i = 0;i<object_array.length;i++)

                canvas.drawBitmap(object_array[i].object,object_array[i].Object_X(),object_array[i].Object_Y(),paint);


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
