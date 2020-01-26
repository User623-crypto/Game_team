package com.example.myapplication;

public class Camera {
    static float offset_X=0;
    static float offset_Y=0;

    Camera()
    {}
    static public void change_right_offset(float a)
    {
        if(offset_X < 2*Game.scene_height)
             offset_X+=a;
    }
    static public void change_left_offset(float a)
    {
        if(offset_X > a)
            offset_X-=a;
    }
    static public void change_up_offset(float a)
    {
        if(offset_Y>0)
            offset_Y-=a;
    }

    static public void change_down_offset(float a)
    {
        if(offset_Y < 2*Game.screenY - Game.screenY) //E con offset_Y deri ne screen x e fundit
            offset_Y+=a;
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







    static public  void checkUp(Player my_player)
    {
        if((my_player.player_y() < (Camera.offset_Y) + Game.screenY/5))
        {


            Camera.change_up_offset(15);}
        else
        {

            Camera.change_up_offset(8);

        }
    }

    static public  void checkDown(Player my_player)
    {
        if((my_player.player_y() > (Camera.offset_Y) + 4*Game.screenY/5) && Game.moveUp == false)
        {


            Camera.change_down_offset(15);}

        else
        {

            Camera.change_down_offset(8);

        }

    }


}