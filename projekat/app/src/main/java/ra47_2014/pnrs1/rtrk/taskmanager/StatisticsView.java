package ra47_2014.pnrs1.rtrk.taskmanager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by student on 24.4.2017.
 */

public class StatisticsView extends View{

    private Paint mPaint;
    private Paint mBorderPaint;
    private Paint mBackgroundPaint;
    private Paint mTextPaint;
    private RectF mRect;
    private float mSetPercentage;
    private float mDrawnPercentage;

    public StatisticsView(Context context) {
        super(context);
        init();
    }

    public StatisticsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StatisticsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setPercentage(float percentage){
        this.mSetPercentage = percentage;
    }

    public void redrawPercentage(){
        mDrawnPercentage++;
        invalidate();
    }

    public void setColor(int colorId){
        mPaint.setColor(getContext().getResources().getColor(colorId));
    }

    public void setBgdColor(int colorId){
        mBackgroundPaint.setColor(getContext().getResources().getColor(colorId));
    }

    public void init(){

        mSetPercentage = 0;
        mDrawnPercentage = 0;

        mPaint = new Paint();
        mPaint.setColor(getContext().getResources().getColor(R.color.greenButton));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mBorderPaint = new Paint();
        mBorderPaint.setColor(getContext().getResources().getColor(R.color.colorAccent));
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setStyle(Paint.Style.STROKE);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(getContext().getResources().getColor(R.color.yellowBgd));
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setStyle(Paint.Style.FILL);

        mTextPaint = new Paint();
        mTextPaint.setColor(getContext().getResources().getColor(R.color.colorPrimary));
        mTextPaint.setTextSize(45);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);

        mRect = new RectF();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mRect.set(0, 0, getWidth(),getHeight());
        canvas.drawArc(mRect, -90, 360, true, mBackgroundPaint);
        canvas.drawArc(mRect, -90, 3.6f*mDrawnPercentage, true, mPaint);
        canvas.drawArc(mRect, -90, 3.6f*mDrawnPercentage, true, mBorderPaint);
        canvas.drawText(String.valueOf(mDrawnPercentage)+"%",getWidth()/2-45,getHeight()/2+20,mTextPaint);
        if(mSetPercentage!=mDrawnPercentage)
            redrawPercentage();
    }
}
