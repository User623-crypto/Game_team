package com.example.myapplication;

public  class Camera

{


    public float X;
    public float Y;
    static float offset_X = 0;
    static float offset_Y = 0;

    public Camera(int _x,int _y){}


    static void change_left_offset(float X)
    {

        offset_X = offset_X + X;

    }
    static void change_right_offset(float X)
    {
        offset_X = offset_X - X;
    }
    static void change_up_offset(float Y)
    {

        offset_Y = offset_Y + Y;
    }

    static void change_down_offser(float Y)
    {

        offset_Y = offset_Y - Y;
    }









}
