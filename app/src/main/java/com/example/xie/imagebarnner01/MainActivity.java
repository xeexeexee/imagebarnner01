package com.example.xie.imagebarnner01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.xie.imagebarnner01.view.imagebarnner;

public class MainActivity extends AppCompatActivity {
    private imagebarnner mGroup;

    private int[] ids=new int[]{
            R.drawable.gesture_bkbottom_normal,
            R.drawable.gesture_bktop,
            R.drawable.head
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        mGroup= (imagebarnner) findViewById(R.id.image_group);
        for (int i = 0; i <ids.length ; i++) {
            ImageView iv=new ImageView(this);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setLayoutParams(new ViewGroup.LayoutParams(width,ViewGroup.LayoutParams.WRAP_CONTENT));
            iv.setImageResource(ids[i]);
            mGroup.addView(iv);
        }
    }
}
