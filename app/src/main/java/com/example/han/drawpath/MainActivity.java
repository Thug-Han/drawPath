package com.example.han.drawpath;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mFather;
    private PathView mPath;
    private List<pointBean> pointBeans = new ArrayList<>();
    private RelativeLayout mRoot;
    /**
     * 是否画过线了.
     */
    private boolean isDraw = false;

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
        if (!isDraw){
            pointBeans.add(new pointBean(mFather.getRight() - (mFather.getWidth() / 2), mFather.getBottom()));
            pointBeans.add(new pointBean(mFather.getRight() - (mFather.getWidth() / 2), mFather.getBottom() + 200));
            pointBeans.add(new pointBean(mFather.getRight() - (mFather.getWidth() / 2) - 200, mFather.getBottom() + 200));
            pointBeans.add(new pointBean(mFather.getRight() - (mFather.getWidth() / 2) - 200, mFather.getBottom() + 200 + 200));
            mPath.setPoints(pointBeans);
            mPath.invalidate();

            ImageView imageView = new ImageView(this);
            //设置大小
            imageView.setLayoutParams(new ViewGroup.LayoutParams(mFather.getWidth(), mFather.getHeight()));
            imageView.setBackgroundColor(Color.BLUE);
            //设置xy,新图的位置
            imageView.setX(mFather.getRight() - (mFather.getWidth()) - 200);
            imageView.setY(mFather.getBottom() + 200 + 200);
            mRoot.addView(imageView);
            isDraw = true;
        }

    }


}
