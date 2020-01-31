package com.example.myapplication.Utility;

public class Vector {
    public float x;
    public float y;


    public Vector(float x,float y)
    {
        this.x=x;
        this.y=y;
    }

    public Vector add(Vector vector)
    {
        x+=vector.x;
        y+=vector.y;
        return this;
    }

    public static Vector add(Vector a,Vector b)
    {
        return new Vector(a.x+b.x,a.y+b.y);
    }

    public Vector sub(Vector vector)
    {
        x-=vector.x;
        y-=vector.y;
        return this;
    }

    public static Vector sub(Vector a,Vector b)
    {
        return new Vector(a.x-b.x,a.y-b.y);
    }

    public Vector mult(float s)
    {
        x*=s;
        y*=s;
        return this;
    }

    public static Vector mult(Vector a,float s)
    {
       return new Vector(a.x*s,a.y*s);
    }

    public Vector div(float s)
    {
        x/=s;
        y/=s;
        return this;
    }

    public static Vector div(Vector a,float s)
    {
        return new Vector(a.x/s,a.y/s);
    }

    //Llogarit vektorin normal ne varsi (i majte ose i djathe) e rendesishme nese eshte true merr normalin e djathte
    public Vector norm(boolean right)
    {
        int n=right==true?-1:1;
        return new Vector(n*-y,n*x);

    }

    public float mag()
    {
        return (float) Math.sqrt((x*x)+(y*y));
    }

    public  float magSq()
    {
        return (x*x)+(y*y);
    }


    //E rendesimshme llogarit projeksionin ne nje aks "Dot Product"
    public float dot(Vector vector)
    {
        return (x*vector.x)+(y*vector.y);
    }

    public static float dot(Vector vector,Vector b)
    {
        return (b.x*vector.x)+(b.y*vector.y);
    }

    //cross product nuk te hyne ne pune
    public float cross(Vector vec) {
        return (this.x * vec.y) - (this.y * vec.x);
    }

    public static float cross(Vector a, Vector b) {
        return (a.x * b.y) - (a.y * b.x);
    }
    //kjo po mund te duhet
    public static double cross3(Vector a, Vector b, Vector c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    }

    /**
     * Calculates the angle of rotation for this vector.
     *
     * @return the angle of rotation
     */
    public float heading() {
        return (float)Math.atan2(this.y, this.x);
    }


    public Vector negate() {
        this.x *= -1;
        this.y *= -1;
        return this;
    }

    public static Vector negate(Vector vec) {
        vec.x *= -1;
        vec.y *= -1;
        return vec;
    }

    /**
     * Calculates and returns the angle (in radians) between two vectors.
     *
     * @param vec
     *            the second Vector2d object.
     * @return the angle between the two vectors (in radians)
     */

    //mos e perdor
    public float angleBetween(Vector vec) {
        return (float)Math.acos(this.dot(vec) / (this.mag() * vec.mag()));
    }

    /**
     * Calculates the distance between this vector and another vector.
     *
     * @param vec
     *            the vector to compare this vector to.
     * @return the distance between the two vectors
     */
    public float dist(Vector vec) {
        return (float)Math.hypot(this.x - vec.x, this.y - vec.y);
    }

    /**
     * Calculates the distance between two vectors.
     *
     * @param vec1
     *            the first vector
     * @param vec2
     *            the second vector
     * @return the distance between the two vectors.
     */
    //Keto funk duhet me i gjeneruar vete
    public static float dist(Vector vec1, Vector vec2) {
        return (float)Math.hypot(vec1.x - vec2.x, vec1.y - vec2.y);
    }

    //E rendesishme e kthen ne vektor njesi nje vektor te caktua
    public Vector normalize()
    {
        if(mag()>0)
        { x/=mag();
        y/=mag();}
        return this;
    }


    public float[] toArray() {
        return toArray(this);
    }

    /**
     * Creates an array representation of this vector.
     *
     * @param vec
     *            the Vector2d object to convert
     * @return array representing this vector
     */
    public static float[] toArray(Vector vec) {
        float[] arr = { vec.x, vec.y };
        return arr;
    }

    public static Vector clone(Vector vec) {
        return new Vector(vec.getX(), vec.getY());
    }

    public void set(float nX, float nY) {
        this.x = nX;
        this.y = nY;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }


    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (obj instanceof Vector) {
            Vector vec = (Vector) obj;

            if (this.x == vec.x && this.y == vec.y) {
                return true;
            }
        }

        return false;
    }
}
