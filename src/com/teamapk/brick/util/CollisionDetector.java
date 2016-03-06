package com.teamapk.brick.util;


import com.teamapk.brick.Vector;

public class CollisionDetector {

    /**
     * Checks if two cuboids collide.
     * @param pos1 Position vector of a vertex of the first cuboid not necessarily the nearest to the origo.
     * @param metr1 Vector pointing to the opposite vertex from the previous vertex
     * @param pos2 Position vector of a vertex of the second cuboid not necessarily the nearest to the origo.
     * @param metr2 Vector pointing to the opposite vertex from the previous vertex
     * @return
     */
    public static boolean collides(Vector pos1, Vector metr1, Vector pos2, Vector metr2) {
        return intersects(pos1.getX(), pos1.getX() + metr1.getX(), pos2.getX(), pos2.getX() + metr2.getX()) &&
                intersects(pos1.getY(), pos1.getY() + metr1.getY(), pos2.getY(), pos2.getY() + metr2.getY()) &&
                intersects(pos1.getZ(), pos1.getZ() + metr1.getZ(), pos2.getZ(), pos2.getZ() + metr2.getZ());
    }

    /**
     * @return true, if ]a,b[ intersects ]c,d[, where a may be greater than b, c may be greater than d
     */
    public static boolean intersects(int a, int b, int c, int d) {
        int tmp;

        if (a > b) {
            tmp = a;
            a = b;
            b = tmp;
        }
        if (c > d) {
            tmp = c;
            c = d;
            d = tmp;
        }

        //Now a<=b, c<=d
        return (a != b && c != d) &&
                ((a <= c && c < b) || // a <= c < b
                        (a <= d && d < b) || // a <= d < b
                        (c < a && a < d)); // c < a < b < d
    }
}
