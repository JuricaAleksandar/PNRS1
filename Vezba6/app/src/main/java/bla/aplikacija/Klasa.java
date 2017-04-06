package bla.aplikacija;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by student on 6.4.2017.
 */

public class Klasa extends View {
    final Paint p = new Paint();
    Toast toast = Toast.makeText(getContext(), "Opa opa!" ,Toast.LENGTH_SHORT);

    public Klasa(Context context) {
        super(context);
        p.setColor(Color.YELLOW);
    }

    public Klasa(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Klasa(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Klasa(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getWidth()/2,getHeight()/2,350,p);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (x>=getWidth()/2-350 && x<=getWidth()/2+350 && y>=getHeight()/2-350 && y<=getHeight()/2+350){
            toast.show();
        }
        return super.onTouchEvent(event);
    }
}
