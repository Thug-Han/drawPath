package com.example.han.drawpath;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "MainActivity";
    private ImageView mFather;
    private PathView mPath;
    private RelativeLayout mRoot;

    /**
     * 记录哪排的imageview
     */
    private List<pointBean> yList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mFather = (ImageView) findViewById(R.id.father);
        mPath = (PathView) findViewById(R.id.path);
        mFather.setOnClickListener(this);
        mRoot = (RelativeLayout) findViewById(R.id.root);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "画", Toast.LENGTH_LONG).show();
        switch (v.getId()) {
            case R.id.father:
                draw(mFather);
                break;
            default:
                break;
        }

    }

    private void draw(ImageView fater) {

        boolean isNext = true;
        Log.i(TAG, "draw: " + fater.getTop() + ":" + fater.getBottom() + ":" + fater.getLeft() + ":" + fater.getRight() + ":" + fater.getX() + ":" + fater.getY());
        Log.i(TAG, "draw: ylist:" + yList.toString());


        for (int i = 0; i < yList.size(); i++) {

            if ((fater.getY() + fater.getHeight() + 400) == yList.get(i).getY()) {

//                if (yList.get(i).getX() != 0) {
                    Log.i(TAG, "draw: 画一排");
                    final ImageView imageView = new ImageView(this);
                    //设置大小
                    imageView.setLayoutParams(new ViewGroup.LayoutParams(fater.getWidth(), fater.getHeight()));
                    imageView.setBackgroundColor(Color.BLUE);
                    //设置xy,新图的位置
                    mRoot.addView(imageView);
                    imageView.setX(yList.get(i).getX() + fater.getWidth() + 100);
                    imageView.setY(fater.getY() + fater.getHeight() + 200 + 200);
                    //扩大选区
                    if (yList.get(i).getX() + fater.getWidth() >= mRoot.getWidth()) {

                        FrameLayout.LayoutParams Params = (FrameLayout.LayoutParams) mRoot.getLayoutParams();
                        Log.i(TAG, "draw: " + Params.height + ":" + Params.width);
                        Params.width = (int) (imageView.getX() + fater.getWidth());
//                    Params.height = (int) (fater.getY()+fater.getHeight()+200+200+fater.getHeight());
                        mRoot.setLayoutParams(Params);

                        mPath.invalidate();
//
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mPath.getLayoutParams();
                        Log.i(TAG, "draw: " + Params.height + ":" + Params.width);
                        params.width = (int) (imageView.getX() + fater.getWidth());
//                    params.height = (int) (fater.getY()+fater.getHeight()+200+200+fater.getHeight()-1);

////                params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
////                params.height = RelativeLayout.LayoutParams.MATCH_PARENT;
                        mPath.setLayoutParams(params);
//                mPath.invalidate();
                    }


                    //如果是这种情况是第二行以前本行已经添加过imageview了
                    List<pointBean> PointBeans = new ArrayList<>();
                    PointBeans.add(new pointBean(fater.getX() + (fater.getWidth() / 2), fater.getY() + fater.getHeight()));
                    PointBeans.add(new pointBean(fater.getX() + (fater.getWidth() / 2), fater.getY() + fater.getHeight() + 200));
                    PointBeans.add(new pointBean(yList.get(i).getX() + fater.getWidth() + 100 + (fater.getWidth() / 2), fater.getY() + fater.getHeight() + 200));
                    PointBeans.add(new pointBean(yList.get(i).getX() + fater.getWidth() + 100 + (fater.getWidth() / 2), fater.getY() + fater.getHeight() + 200 + 200));


                    mPath.addPoints(new Point(false, PointBeans));
                    mPath.invalidate();

                    //设置x值以便下次确认下一个imageview的位置
                    yList.get(i).setX(yList.get(i).getX() + fater.getWidth() + 100);
                Log.i(TAG, "draw: +yList.get(i).getX()"+yList.get(i).getX());
                    isNext = false;
                    //如果有这情况,直接跳出for循环
                    break;
                }else {

                }


//            }


        }

        //下一行没添加过imageview
        if (isNext) {
            Log.i(TAG, "draw: isNext");

            //添加imageview
            final ImageView imageView = new ImageView(this);
            //设置大小
            imageView.setLayoutParams(new ViewGroup.LayoutParams(fater.getWidth(), fater.getHeight()));
            imageView.setBackgroundColor(Color.BLUE);
            //设置xy,新图的位置
            mRoot.addView(imageView);
            imageView.setX(0);
            imageView.setY(fater.getY() + fater.getHeight() + 200 + 200);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    draw(imageView);

                }
            });

            //如果y轴超标,加大父布局的y轴
            Log.i(TAG, "draw: " + (fater.getY() + fater.getHeight() + 200 + 200 + fater.getHeight()) + ":" + mRoot.getHeight());
            if (fater.getY() + fater.getHeight() + 200 + 200 + fater.getHeight() >= mRoot.getHeight()) {
                FrameLayout.LayoutParams Params = (FrameLayout.LayoutParams) mRoot.getLayoutParams();
                Log.i(TAG, "draw: " + Params.height + ":" + Params.width);
                Params.width = (int) (fater.getX() + (fater.getWidth() / 2) + fater.getWidth());
                Params.height = (int) (fater.getY() + fater.getHeight() + 200 + 200 + fater.getHeight());
                mRoot.setLayoutParams(Params);
                mPath.invalidate();
//
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mPath.getLayoutParams();
                Log.i(TAG, "draw: " + Params.height + ":" + Params.width);
                params.width = (int) (fater.getX() + (fater.getWidth() / 2) + fater.getWidth());
                params.height = (int) (fater.getY() + fater.getHeight() + 200 + 200 + fater.getHeight() - 1);

////                params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
////                params.height = RelativeLayout.LayoutParams.MATCH_PARENT;
                mPath.setLayoutParams(params);
//                mPath.invalidate();
            }

            List<pointBean> PointBeans = new ArrayList<>();
            PointBeans.add(new pointBean(fater.getX() + (fater.getWidth() / 2), fater.getY() + fater.getHeight()));
            PointBeans.add(new pointBean(fater.getX() + (fater.getWidth() / 2), fater.getY() + fater.getHeight() + 200 + 200));
            mPath.addPoints(new Point(false, PointBeans));
            mPath.invalidate();

            //将xy存入
            yList.add(new pointBean(0, fater.getY() + fater.getHeight() + 200 + 200));

        }


    }


}
