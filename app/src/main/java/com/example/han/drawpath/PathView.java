package com.example.han.drawpath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:jalong han
 * @date:2017/11/13
 * @e-mail:hjl999@126.com
 */

public class PathView extends View{
    /**
     * 画笔
     */
    private final Paint paint;
    /**
     * 传过来要画的点
     */
    private List<pointBean> points = new ArrayList<>();

    public List<pointBean> getPoints() {
        return points;
    }

    public void setPoints(List<pointBean> points) {
        this.points.clear();
        this.points.addAll(points);
    }

    public PathView(Context context) {
        this(context,null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLACK);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPath(points,canvas);
    }


    /**
     * 根据传过来的点设置线,
     * @param points
     * @param canvas
     */
    public void drawPath(List<pointBean> points,Canvas canvas){
        Path path = new Path();
        for (int i = 0; i < points.size(); i++) {
            if (i==0){
                //设置起点
                path.moveTo(points.get(i).getX(),points.get(i).getY());
            }else {
                //设置其它的点
                path.lineTo(points.get(i).getX(),points.get(i).getY());
            }

        }
        //画
        canvas.drawPath(path,paint);

    }
}
