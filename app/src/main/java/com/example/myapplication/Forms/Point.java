package com.example.myapplication.Forms;

/**
 * This class with represent mainly  vertexes of the polygons
 * @version 1.0
 */


public class Point {
    float x;
    float y;

    /**
     *
     * @param x
     *          x component of the Point
     * @param y
     *          y component of the Point
     */
    Point(float x,float y)
    {
        this.x=x;
        this.y=y ;

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
