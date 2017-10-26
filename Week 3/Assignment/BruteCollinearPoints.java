import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mayur Kulkarni <mayurkulkarni012@gmail.com>
 */
public class BruteCollinearPoints {
    private final double EPSILON = 1e-7;
    private int numberOfSegments;
    private Point[] points;
    private List<LineSegment> segments = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        this.points = points;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k])
                                && points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])) {
                            numberOfSegments++;
                            System.out.println(points[i] + " " + points[j] + " " +
                                    points[k] + " " + points[l]);
                            Point[] coll = new Point[4];
                            coll[0] = points[i];
                            coll[1] = points[j];
                            coll[2] = points[k];
                            coll[3] = points[l];
                            Arrays.sort(coll);
                            segments.add(new LineSegment(coll[0], coll[3]));
                        }
                    }
                }
            }
        }
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
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
//        FastCollinearPoints collinear = new FastCollinearPoints(points);
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
//        Point[] points = {new Point(1, 13), new Point(2, 2), new Point(5, 5),
//                            new Point(3, 3),new Point(4, 4)};
//        BruteCollinearPoints collinearPoints = new BruteCollinearPoints(points);
//        System.out.println(Arrays.toString(collinearPoints.segments()));
    }

    public int numberOfSegments() {
        return this.numberOfSegments;
    }

    public LineSegment[] segments() {
        LineSegment[] segs = new LineSegment[numberOfSegments];
        for (int i = 0; i < segments.size(); i++) {
            segs[i] = segments.get(i);
        }
        return segs;
    }
}