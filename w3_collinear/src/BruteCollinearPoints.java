import java.util.Comparator;

import edu.princeton.cs.algs4.Stack;

public class BruteCollinearPoints {
    private Point[] points;
    private int n;
    private Stack<LineSegment> stack= new Stack<LineSegment>();
    private LineSegment[] segments;
    
    
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw (new NullPointerException());
        n = points.length;
        this.points = new Point[n];
        for (int i = 0; i < n; i++) {
            if (points[i] == null) throw (new NullPointerException());
            this.points[i] = points[i];
        }
          
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                if (points[i].compareTo(points[j]) == 0) throw (
                        new IllegalArgumentException());
            }
        
        findCollinear();
    }   // finds all line segments containing 4 points
    
    public int numberOfSegments() {
        return segments.length;
    }   // the number of line segments
    
    public LineSegment[] segments() {
        return segments;
    }   // the line segments
    
    private Point min(Point a, Point b) {
        return a.compareTo(b) < 0 ? a : b;
    }
    
    private Point max(Point a, Point b) {
        return a.compareTo(b) > 0 ? a : b;
    }
    
    private void findCollinear() {        
        for (int i = 0; i < n; i++) {
            Comparator<Point> so = points[i].slopeOrder();
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (so.compare(points[j], points[k]) == 0) {
                        for (int l = k + 1; l < n; l++) {
                            if (so.compare(points[k], points[l]) == 0) {
                                //segments.
                                Point min = min(min(points[i], points[j]), 
                                        min(points[k], points[l]));
                                Point max = max(max(points[i], points[j]), 
                                        max(points[k], points[l]));
                                LineSegment seg = new LineSegment(min, max);
                                stack.push(seg);
                            }
                        }
                    }
                }
            }
        }
        segments = new LineSegment[stack.size()];
        for (int m = 0; m < segments.length; m++) {
            segments[m] = stack.pop();
        }
    }
}
