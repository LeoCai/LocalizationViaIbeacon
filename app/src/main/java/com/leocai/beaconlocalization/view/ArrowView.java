package com.leocai.beaconlocalization.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by leocai on 15-4-4.
 */
public class ArrowView extends View implements Observer {

    private float[] center = new float[]{200, 500};
    private float size = 50;
    private float[][] arrow = new float[][]{
            {(float) (center[0] - size * Math.sqrt(3)), center[1] - size},
            {(float) (center[0] - size * Math.sqrt(3)), center[1] + size},
            {center[0] + size * 2, center[1]}};
    private float[][] cuArrow = new float[][]{{10, 50}, {10, 80}, {100, 65}};
    private Paint paint = new Paint();
    private double initAngle = Math.PI/2;
    private double currentAngle = 0;


    public double getCurrentAngle() {
        return currentAngle;
    }

    public void setCurrentAngle(double currentAngle) {
        this.currentAngle = -currentAngle;
        postInvalidate();
    }

    public ArrowView(Context context) {
        super(context);
    }

    public ArrowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ArrowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initPoints();
        rotateArrow();
        drawArrow(canvas);
    }

    private void initPoints() {
         center = new float[]{getX()+100, getY()+100};
         float size = 50;
         arrow = new float[][]{
                {(float) (center[0] - size * Math.sqrt(3)), center[1] - size},
                {(float) (center[0] - size * Math.sqrt(3)), center[1] + size},
                {center[0] + size * 2, center[1]}};
    }

    private void rotateArrow() {
        for (int i = 0; i < 3; i++) {
            float[] point = arrow[i];
            cuArrow[i] = rotatePoint(point);
        }
    }

    private float[] rotatePoint(float[] point) {
        double cosAngle = Math.cos(currentAngle - initAngle);
        double sinAngle = Math.sin(currentAngle - initAngle);
        float[] tempP = new float[]{point[0]-center[0],point[1]-center[1]};
        float[] rp = new float[]{(float) (tempP[0] * cosAngle - tempP[1] * sinAngle), (float) (tempP[0] * sinAngle + tempP[1] * cosAngle)};
        rp[0]+=center[0];
        rp[1]+=center[1];
        return rp;
    }

    private void drawArrow(Canvas canvas) {
        for (int i = 0; i < 3; i++) {
            canvas.drawLine(cuArrow[i][0], cuArrow[i][1], cuArrow[(i + 1) % 3][0], cuArrow[(i + 1) % 3][1], paint);
        }
    }

    @Override
    public void update(Observable observable, Object data) {
        postInvalidate();
    }
}
