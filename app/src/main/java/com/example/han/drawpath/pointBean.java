package com.example.han.drawpath;

/**
 * @author:jalong han
 * @date:2017/11/13
 * @e-mail:hjl999@126.com
 */

public class pointBean {

    private float x;
    private float y;

    public pointBean(float x, float y) {
        this.x = x;
        this.y = y;
    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }


    @Override
    public String toString() {
        return "PointBean{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
