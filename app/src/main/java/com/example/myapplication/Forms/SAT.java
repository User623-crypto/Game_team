package com.example.myapplication.Forms;
import com.example.myapplication.Utility.Vector;

public class SAT {


    //E pa optimizuar duhet te optimizohet
    private static boolean checkCollision(Vector[] aVertices,
                                          Vector[] bVertices) {

        int aLength = aVertices.length;
        int bLength = bVertices.length;

        // Try to find a separating axis using the first polygon's edges
        for (int i = 0, j = aLength - 1; i < aLength; j = i, i++) {
            Vector axis = Vector.sub(aVertices[i], aVertices[j]).norm(true);//Duhet   Vector axis = Vector.sub(aVertices[i], aVertices[j]).norm(true).normalize();

            if (separatingAxis(aVertices, bVertices, axis)) {
                return false;
            }
        }

        // Try to find a separating axis using the second polygon's edges
        for (int i = 0, j = bLength - 1; i < bLength; j = i, i++) {
            Vector axis = Vector.sub(bVertices[i], bVertices[j]).norm(true);//Duhet  Vector axis = Vector.sub(aVertices[i], aVertices[j]).norm(true).normalize();

            if (separatingAxis(aVertices, bVertices, axis)) {
                return false;
            }
        }

        return true;
    }

        // e pa optimizuar
    private static boolean separatingAxis(Vector[] aVertices,
                                          Vector[] bVertices, Vector axis) {

        float minA = (float)Double.POSITIVE_INFINITY;
        float maxA = (float)Double.NEGATIVE_INFINITY;
        float minB = (float)Double.POSITIVE_INFINITY;
        float maxB = (float)Double.NEGATIVE_INFINITY;

        int maxLength = Math.max(aVertices.length, bVertices.length);

        // project both polygons onto axis
        for (int i = 0; i < maxLength; i++) {
            if (i < aVertices.length) {
                float dot = axis.dot(aVertices[i]);

                if (dot < minA)
                    minA = dot;
                if (dot > maxA)
                    maxA = dot;
            }

            if (i < bVertices.length) {
                float dot = axis.dot(bVertices[i]);

                if (dot < minB)
                    minB = dot;
                if (dot > maxB)
                    maxB = dot;
            }

            // exit early if overlap found
            if (minA <= maxB && minB <= maxA) {
                return false;
            }
        }

        return true;
    }

}
