package com.gowreeshmago.mynotes.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.View;

import com.gowreeshmago.mynotes.R;

public class Lines_in_Notes_Object extends AppCompatEditText {

    Rect newRect;
    Paint newPaint;




    @SuppressLint("ResourceAsColor")
    public Lines_in_Notes_Object(Context context, AttributeSet attrs) {
        super(context, attrs);

        newRect = new Rect();
        newPaint = new Paint();
        newPaint.setStyle(Paint.Style.STROKE);
        newPaint.setStrokeWidth(2);
        newPaint.setColor(R.color.colorPrimaryDark);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        int heightOfParent = ((View)this.getParent()).getHeight();
        int distanceBetweenLines = getLineHeight();
        int numberOfLines = heightOfParent/distanceBetweenLines;



        Rect rect = newRect;
        Paint paint = newPaint;

        int baseLine = getLineBounds(0,rect);


        for (int i=0;i<numberOfLines;i++) {
            canvas.drawLine(rect.left, baseLine + 1, rect.right,baseLine+1, paint);
            baseLine+=distanceBetweenLines;
        }



        super.onDraw(canvas);
    }
}
