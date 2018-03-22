package com.example.nguyenthanhxuan.gametrucxanh;

/**
 * Created by Nguyen Thanh Xuan on 3/21/2018.
 */

public class Line {
    private Point startPoint;
    private Point endPoint;

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public Line(Point startPoint, Point endPoint) {

        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }
}
