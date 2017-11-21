package com.example.han.drawpath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:jalong han
 * @date:2017/11/13
 * @e-mail:hjl999@126.com
 */

public class PathView extends RelativeLayout {
    private final String TAG = "PathView";
    /**
     * 画笔
     */
    private final Paint paint;
    /**
     * 传过来要画的点,存储
     */
    private List<Point> points = new ArrayList<>();


    public void addPoints(Point point) {
        this.points.add(point);
    }

    public PathView(Context context) {
        this(context, null);
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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(((ViewGroup) getParent()).getWidth(), ((ViewGroup) getParent()).getHeight());
        Log.i(TAG, "onMeasure: ");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPath(points, canvas);
    }


    /**
     * 根据传过来的点设置线,
     *
     * @param points
     * @param canvas
     */
    public void drawPath(List<Point> points, Canvas canvas) {
        Log.i(TAG, "drawPath: "+points.toString());
        Path path = new Path();
        for (int i = 0; i < points.size(); i++) {

            for (int j = 0; j < points.get(i).getPoints().size(); j++) {

                if (j == 0) {
                    //设置起点
                    path.moveTo(points.get(i).getPoints().get(j).getX(), points.get(i).getPoints().get(j).getY());

                } else {
                    //设置其它的点
                    path.lineTo(points.get(i).getPoints().get(j).getX(), points.get(i).getPoints().get(j).getY());
                }
            }

        }
        //画
        canvas.drawPath(path, paint);
    }
}
