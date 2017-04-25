package ra47_2014.pnrs1.rtrk.taskmanager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by student on 24.4.2017.
 */

public class StatisticsView extends View {

    private Paint mPaint;
    private Canvas mCanvas;

    public StatisticsView(Context context) {
        super(context);
    }

    public StatisticsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StatisticsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.rgb(30,154,30));
        mCanvas.drawArc(new RectF(getLeft(),getTop(),getWidth()/3,getHeight()/4),90,250,true,mPaint);
    }
}
