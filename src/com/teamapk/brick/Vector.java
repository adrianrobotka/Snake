package com.teamapk.brick;

/**
 * Represents an Euclidean vector
 */
public class Vector {
    /**
     * Graphical offset of x
     */
    protected static Vector graphicalPole = new Vector(0, 0);
    /**
     * The width
     */
    protected int x;
    /**
     * The height
     */
    protected int y;
    /**
     * The depth
     */
    protected int z;

    /**
     * Tho dimensional vector
     *
     * @param x The width
     * @param y The height
     */
    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
        z = 0;
    }

    /**
     * Three dimensional vector
     *
     * @param x The width
     * @param y The height
     * @param z The depth
     */
    public Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Get graphical pole
     *
     * @return Graphical pole
     */
    public static Vector getGraphicalPole() {
        return graphicalPole;
    }

    /**
     * Set graphical pole
     *
     * @param graphicalPole Graphical pole
     */
    public static void setGraphicalPole(Vector graphicalPole) {
        Vector.graphicalPole = graphicalPole;
    }

    /**
     * Add a vector to itself
     *
     * @param vector The vector
     */
    public Vector add(Vector vector) {
        return new Vector(vector.getX() + x,
                vector.getY() + y,
                vector.getZ() + z);
    }

    /**
     * Subtract a vector from itself
     *
     * @param vector The substrahend vector
     */
    public Vector minus(Vector vector) {
        return new Vector(x - vector.getX(),
                y - vector.getY(),
                z - vector.getZ());
    }

    /**
     * Change vector to it's negate
     */
    public Vector negate() {
        return new Vector(-x, -y, -z);
    }

    /**
     * Multiple the vector with a scalar
     *
     * @param n The scalar
     */
    public Vector multiple(int n) {
        return new Vector(x * n, y * n, z * n);
    }

    /**
     * Check equality with the vector
     *
     * @param o An object to compare
     * @return Is the object equals to the vector
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vector))
            return false;

        Vector v = (Vector) o;
        return v.x == x && v.y == y && v.z == z;
    }

    /**
     * Create a readable text from the vector
     *
     * @return Vector's string representant
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    /**
     * Get the vector's width
     *
     * @return The vector's width
     */
    public int getX() {
        return x;
    }

    /**
     * Set the vector's width
     *
     * @param x Vector's width
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get the vector's height
     *
     * @return The vector's height
     */
    public int getY() {
        return y;
    }

    /**
     * Set the vector's height
     *
     * @param y Vector's height
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Get the vector's depth
     *
     * @return The vector's depth
     */
    public int getZ() {
        return z;
    }

    /**
     * Set the vector's depth
     *
     * @param z Vector's depth
     */
    public void setZ(int z) {
        this.z = z;
    }

    /**
     * Get the vector's graphical width
     *
     * @return The vector's graphical width
     */
    public int getGraphicalX() {
        return Math.abs(x - graphicalPole.x);
    }

    /**
     * Get the vector's graphical height
     *
     * @return The vector's graphical height
     */
    public int getGraphicalY() {
        return Math.abs(y - graphicalPole.y);
    }

    /**
     * Get the vector's graphical depth
     *
     * @return The vector's graphical depth
     */
    public int getGraphicalZ() {
        return Math.abs(z - graphicalPole.z);
    }
}
