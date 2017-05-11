package com.example.xie.imagebarnner01.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by xie on 2017/5/11.
 */

public class imagebarnner extends ViewGroup {
    private int children;
    private int childwidth,childheight;

    private int x;
    private int index;

    private Scroller scroller;

    public imagebarnner(Context context) {
        super(context);
        initObj();
    }


    public imagebarnner(Context context, AttributeSet attrs) {
        super(context, attrs);
        initObj();
    }

    public imagebarnner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initObj();
    }

    private void initObj() {
        scroller=new Scroller(getContext());
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),0);
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        children=getChildCount();
        if(children==0){
            setMeasuredDimension(0,0);
        }else{
            measureChildren(widthMeasureSpec,heightMeasureSpec);

            View view=getChildAt(0);
            childwidth=view.getMeasuredWidth();
            childheight=view.getMeasuredHeight();
            int width=view.getMeasuredWidth()*children;
            setMeasuredDimension(width,childheight);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x= (int) event.getX();
                Log.d("tag","down");
                break;
            case MotionEvent.ACTION_MOVE:



                int moveX= (int) event.getX();
                int distance=moveX-x;
                scrollBy(-distance,0);

                if (Math.abs(moveX - x)>20) {
                    isClick=false;
                }else{
                    x=moveX;
                    Log.d("tag1","move");
                    break;
                }



            case MotionEvent.ACTION_UP:
                int scrollX=getScrollX();
                index=(scrollX+children/2)/childwidth;
                if(index<0){
                    index=0;
                }else if (index>children-1){
                    index=childheight-1;
                }
                int dx=index*childwidth-scrollX;
                scroller.startScroll(scrollX,0,dx,0);
                postInvalidate();
                Log.d("tag2","up");
                break;
            default:

                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            int leftmargin=0;

            for (int i = 0; i <children ; i++) {
                View view=getChildAt(i);
                view.layout(leftmargin, 0, leftmargin+childwidth, childheight);
                leftmargin+=childwidth;

            }


        }

    }
}
