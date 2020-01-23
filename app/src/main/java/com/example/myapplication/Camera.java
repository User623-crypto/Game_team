package com.example.myapplication;

public class Camera {
    static float offset_X=0;
    static float offset_Y=0;

    Camera()
    {}
    static public void change_right_offset(float a)
    {
        if(offset_X+Game.screenX<10000)//madhesia e hartes
        offset_X+=a;
    }
    static public void change_left_offset(float a)
    {
        if(offset_X>0)
        offset_X-=a;
    }

    static public void checkLeft(Player my_player)
    {
        if((my_player.player_x() < (Camera.offset_X) + Game.screenX/5))
        {


            Camera.change_left_offset(20);}
        else
        {

            Camera.change_left_offset(15);

        }
    }

    static public void checkRight(Player my_player)
    {
        if((my_player.player_x() > (Camera.offset_X) + 4*Game.screenX/5))
        {


                Camera.change_right_offset(20);}

        else if(my_player.player_x() - Camera.offset_X > 0)
        {

                Camera.change_right_offset(15);

        }
    }

}
