import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import edu.princeton.cs.algs4.*;

public class FastCollinearPoints {
    private Point[] pts;
    private Point[] points;
    private int n;
    private HashMap<String, LineSegment> colin = new HashMap<String, LineSegment> ();
    private LineSegment[] segments;
    
    public FastCollinearPoints(Point[] pts) {
        if (pts == null) throw (new NullPointerException());
        this.pts = pts;
        n = pts.length;
        points = new Point[n];
        for (int i = 0; i < n; i++) {
            if (pts[i] == null) throw (new NullPointerException());
            points[i] = pts[i];
        }
          
        Arrays.sort(points);
        
        for (int i = 1; i < n; i++)
            if (points[i].compareTo(points[i - 1]) == 0) throw (
                    new IllegalArgumentException());
             
        findCollinear(); 
    }   // finds all line segments containing 4 points
    
    public int numberOfSegments() {
        return segments.length;
    }   // the number of line segments
    
    public LineSegment[] segments() {
        return segments;
    }   // the line segments

    private void findCollinear() {        
        for (Point pivot: pts) {
            Arrays.sort(points, pivot.slopeOrder());
            double lastSlope = Double.NEGATIVE_INFINITY;
            int length = 1;
            for (int j = 0; j < n; j++) {
                double slope = pivot.slopeTo(points[j]);
                if (slope == lastSlope) {
                    length++;
                    
                }
                else {
                    if (length >= 3) {
                        addLineSegment(pivot, j - length, j);
                    }
                    length = 1;
                    lastSlope = slope;
                }
            }
            if (length >= 3) {
                addLineSegment(pivot, n - length, n);
            }
        }
        segments = new LineSegment[colin.size()];
        segments = colin.values().toArray(segments);
    }
    
    private void addLineSegment(Point pivot, int imin, int imax) {
        Arrays.sort(points, imin, imax);
        // pivot itself is missing because of negative slope on definitions
        Point min = points[imin];
        Point max = points[imax - 1];
        if (pivot.compareTo(min) < 0) min = pivot;
        if (pivot.compareTo(max) > 0) max = pivot;
        LineSegment m = new LineSegment(min, max);
        
        colin.put(m.toString(), m);   
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
    
        // draw the points
        //StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
    
        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }


}