package com.example.myapplication.Forms;

/**
 * A class to represent Axis-Aligned-Boundary-Boxes. The Box
 * will contain component x and y which will represent the position
 * in coordiante system respectively, and the width and height will
 * be the width and the height of the box.This class is supposed to act
 * as a container to all object that will be interacted with.
 * @version 1.0
 */

public class AABB {

    /**
     * x component of the AABB
     */
    protected float x;

    /**
     * y component of the AABB
     */
    protected float y;

    /**
     * width and height of AABB
     */
    protected float width,height;

    /**
     * Constructs an AABB object with a x and y component from float values
     *
     * @param x
     *          the x component
     * @param y
     *           the y component
     * @param height
     *           the height of the box
     * @param width
     *           the width of the box
     *
     */
    public AABB(float x,float y,float width,float height)
    {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }
    public AABB(float x,float y,int width,int height)
    {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }


    /**
     * Constructs a AABB using 2 Points that will represent the starting and
     * ending point respectively.
     * @param min
     * @param max
     */

    public AABB(Point min, Point max)
    {
        x=min.getX();
        y=min.getY();
        width=max.getX()-min.getX();
        height=max.getY()-min.getY();
    }

    /**
     * Checks if a point is inside the box.
     * @param x
     *          checks for the x coordinate of the Point.
     * @param y
     *          checks for the y coordinate of the Point.
     * @return
     *          returns true if the point is in the box and false otherwise.
     */

    public boolean contains(float x,float y)
    {
        return(x>=this.x && x<=this.x+this.width && y>=this.y && y<=this.y+this.height);


    }

    /**
     *
     * @param point
     *              represent the point that will be checked.
     * @return
     *          returns if the box contains the point or not.
     */
    public boolean contains(Point point)
    {
        return contains(point.getX(),point.getY());
    }

    /**
     * This function will check if another AABB overlaps at least partly with the other
     * AABB.
     * @param a
     *          a represents the AABB that will be checked
     * @return
     *          It will return either AABB overlaps or not.
     */

    public boolean contains(AABB a)
    {
        //if the AABB contains at least one point of the other AABB than it will partly
        //contain the AABB
        return(contains(a.getX(),a.getY())||contains(a.getxMax(),a.getyMax()));
    }

    /**
     * This function will check if another AABB is inside this AABB
     *
     * @param a
     *          a represents the AABB that will be checked
     * @return
     *          It will return either AABB is contained or not.
     */
    public boolean fullycontain(AABB a)
    {
        return(contains(a.getX(),a.getY())&&contains(a.getxMax(),a.getyMax()));
    }


    /**
     * Checks if two AABB intersects or not
     * @param a
     *          represent the AABB that will be checked with
     */
    public boolean intersect(AABB a)
    {
        return !(a.getX() > x+width || a.getxMax()<x || a.getY()>y+height || a.getyMax()<y);


    }


    /**
     *
     * @return
     *      It will return position x (the minimum value for x coordinate)
     */
    public float getX() {
        return x;
    }

    /**
     *
     * @return
     *         it returns position y (the minimum value for y coordinate)
     */
    public float getY() {
        return y;
    }
    /**
     *
     * @return
     *      It will return the max value for x coordinate
     */
    public float getxMax() {
        return x+width;
    }

    /**
     *
     * @return
     *      It will return the max value for y coordinate
     */
    public float getyMax() {
        return y+height;
    }

    /**
     *
     * @return
     *         it returns the width of the box
     */
    public float getAABBWidth() {
        return width;
    }

    /**
     *
     * @return
     *         it returns the float of the box
     */
    public float getAABBHeight() {
        return height;
    }
}
