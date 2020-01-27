package com.example.myapplication.Forms;


/**
 *  A class that will be used to project Triangles.
 *  This object will be contained in an AABB for broad checking
 *  collision purpose.
 */
public class Triangle extends AABB{
    /**
     * Point a,b and c represent the 3 vertexes of the Triangle
     *
     * Point min and max are the points that with form the AABB.
     *
     * area is the area of the triangle
     */
    private Point a,b,c;
    private double area;


    /**
     *  NOTE:The Triangle is not self tested the values entered are supposed to be working values
     *  and not points that will be aligned and make it unable to represent a triangle.
     *  Also the boundary box that will contain it will be build according to the vertexes given.
     *  IMPORTANT: PARAMETERS SHOULD BE GIVEN AS IT IS FIT.
     * @param a
     *          a is the left uttermost vertex
     * @param b
     *          b is the second Vertex
     * @param c
     *          c is the third Vertex
     * @param min
     *      *          min point of the Boundary box (it MUST be < than all other points
     * @param max
     *      *          min point of the Boundary box (it MUST be > than all other points
     */
    Triangle(Point a,Point b,Point c,Point min,Point max) {
        super(min,max);

        this.a=a;
        this.b=b;
        this.c=c;
        area=area(a,b,c);

    }

    /**
     *
     * @param a
     *          Vertex a
     * @param b
     *          Vertex b
     * @param c
     *          Vertex b
     * @return
     *      returns the area of the triangle.
     */
    public double area(Point a, Point b, Point c)
    {
        return  Math.abs((a.getX()*(b.getY()-c.getY()) + b.getX()*(c.getY()-a.getY())+
                c.getX()*(a.getY()-b.getY()))/2.0);
    }


    /**
     * This function checks if a point is inside a triangle
     * @param p
     *      represents the point that will be checked
     * @return
     *      it will return true if the point is inside and false otherwise.
     */
    public boolean isInside(Point p)
    {

        /* Calculate area of triangle PAC */
        double A1 = area (p,a,c);

        /* Calculate area of triangle PBC */
        double A2 = area (p,b,c);

        /* Calculate area of triangle PAB */
        double A3 = area (p,a,b);

        /* Check if sum of A1, A2 and A3 is same as A */
        return (area == A1 + A2 + A3);
    }



}
