package com.pnrs.graphicsexamples;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

public class HouseView extends View {

    private static final float GOLDEN_RATIO = 1.618f;

    private Paint mPaint;
    private Bitmap mCloud;
    private Bitmap mGrass;
    private RectF mRect;
    private Path mPath;

    public HouseView(Context context) {
        super(context);

        mPaint = new Paint();
        mGrass = BitmapFactory.decodeResource(getResources(), R.drawable.grass);
        mCloud = BitmapFactory.decodeResource(getResources(), R.drawable.cloud);
        mRect = new RectF();
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // set paint style
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        // draw sky
        mPaint.setColor(Color.rgb(126, 192, 238));
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);

        // draw grass
        mRect.left = 0; mRect.top = 3*canvas.getHeight()/4;
        mRect.right = canvas.getWidth(); mRect.bottom = canvas.getHeight();
        canvas.drawBitmap(mGrass, null, mRect, null);

        // draw sun
        float sunX = canvas.getWidth()/4;
        float sunY = canvas.getHeight()/4;
        float sunRadius = sunX < sunY ? sunX/2 : sunY/2;
        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(sunX, sunY, sunRadius, mPaint);

        // draw cloud
        float cloudWidth = 6*sunRadius;
        float scaleFactor = cloudWidth/mCloud.getWidth();
        float cloudLeft = sunX - sunRadius;
        float cloudTop = sunY - sunRadius;
        mRect.left = cloudLeft; mRect.top = cloudTop;
        mRect.right = cloudLeft + scaleFactor*mCloud.getWidth();
        mRect.bottom = cloudTop + scaleFactor*mCloud.getHeight();
        canvas.drawBitmap(mCloud, null, mRect, null);

        // draw house
        drawHouse(canvas, 3*canvas.getWidth()/4, 3*canvas.getHeight()/4, canvas.getWidth()/4);
    }

    private void drawHouse(Canvas canvas, float x, float y, float width) {

        // draw house base
        float houseHeight = width/GOLDEN_RATIO; // Golden rectangle
        float houseLeft = x - width/2;
        float houseTop = y - goldenLonger(houseHeight);
        mPaint.setColor(Color.WHITE);
        canvas.drawRect(houseLeft, houseTop, houseLeft + width,
                houseTop + houseHeight, mPaint);

        // draw door
        float doorHeight = goldenLonger(houseHeight);
        float doorWidth = doorHeight/GOLDEN_RATIO; // Golden rectangle
        float doorLeft = houseLeft + 3*width/4 - doorWidth/2;
        float doorBottom = houseTop + houseHeight;
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(doorLeft, doorBottom - doorHeight, doorLeft + doorWidth,
                doorBottom, mPaint);

        // draw window
        float windowWidth = (doorLeft - houseLeft)/2;
        float windowHeight = windowWidth/GOLDEN_RATIO;
        float windowLeft = houseLeft + (doorLeft - houseLeft - windowWidth)/2;
        float windowTop = doorBottom - doorHeight;
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(windowLeft, windowTop, windowLeft + windowWidth,
                windowTop + windowHeight, mPaint);

        //draw roof
        mPath.reset();
        mPath.moveTo(houseLeft, houseTop);
        mPath.lineTo(houseLeft + width, houseTop);
        mPath.lineTo(houseLeft + width/2, houseTop - houseHeight);
        mPath.close();
        mPaint.setColor(Color.RED);
        canvas.drawPath(mPath, mPaint);
    }

    private float goldenLonger(float sum) {
        return GOLDEN_RATIO*sum/(1+GOLDEN_RATIO);
    }
}
