import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x, y;
    
    public Point(int x, int y)  {
        this.x = x;
        this.y = y;
    }   // constructs the point (x, y)                    

    public void draw() { 
        StdDraw.point(x, y); 
    }   // draws this point
    
    public void drawTo(Point that) { 
        StdDraw.line(this.x, this.y, that.x, that.y);
    }   // draws the line segment from this point to that point
    
    public String toString() {
        return "(" + x + ", " + y + ")";
    }   // string representation               

    public int compareTo(Point that) {
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
    }   // compare two points by y-coordinates, breaking ties by x-coordinates
    
    public double slopeTo(Point that) {
        if (this.x == that.x && this.y == that.y) return Double.NEGATIVE_INFINITY;
        if (this.x == that.x) return Double.POSITIVE_INFINITY;
        if (this.y == that.y) return 0.0;
        return (this.y - that.y) / (double) (this.x - that.x);
    }   // the slope between this point and that point
    
    public Comparator<Point> slopeOrder() {
        return new PolarOrder();       
    }   // compare two points by slopes they make with this point
    
    // compare other points relative to polar angle (between 0 and 2pi) they make with this Point
    private class PolarOrder implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            double s1 = slopeTo(p1);
            double s2 = slopeTo(p2);
            
            if (s1 < s2) return -1;
            if (s1 > s2) return +1;
            return 0;
        }
    }
    
    public static void main(String[] args) {
        Point p0 = new Point(0, 0);
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 3);
        
        p0.draw(); 
        p1.draw(); 
        p2.draw();
        p0.drawTo(p1); 
        p0.drawTo(p2);
        
        assert p0.compareTo(p1) == 1 && p1.compareTo(p2) == -1;
        Comparator<Point> so = p0.slopeOrder();
        assert so.compare(p1, p2) == -1;
    }
    
    
}