package com.example.myapplication;

public class Camera {
    static float offset_X=0;
    static float offset_Y=0;

    Camera()
    {}
    static public void change_right_offset(float a)
    {
        offset_X+=a;
    }
    static public void change_left_offset(float a)
    {
        offset_X-=a;
    }

}
