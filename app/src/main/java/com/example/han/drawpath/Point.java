package com.example.han.drawpath;

import java.util.List;

/**
 * @author:jalong han
 * @date:2017/11/20
 * @e-mail:hjl999@126.com
 */

public class Point {

    private boolean isDraw ;
    private List<pointBean> points;


    public Point(boolean isDraw, List<pointBean> points) {
        this.isDraw = isDraw;
        this.points = points;
    }


    public boolean isDraw() {
        return isDraw;
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
    }

    public List<pointBean> getPoints() {
        return points;
    }

    public void setPoints(List<pointBean> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Point{" +
                "isDraw=" + isDraw +
                ", points=" + points +
                '}';
    }
}
