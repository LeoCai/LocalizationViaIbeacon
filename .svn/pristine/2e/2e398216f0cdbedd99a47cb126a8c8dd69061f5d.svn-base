package com.leocai.beaconlocalization.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.leocai.beaconlocalization.localization.BaseBeaconInfo;
import com.leocai.beaconlocalization.localization.BeaconLocalization;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by leocai on 15-3-25.
 */
public class LocalizationMapView extends View implements Observer {

    private static final String TAG = "cupos";
    private BeaconLocalization beaconLocalization;
    private float baseBeaconCircleSize = 10;
    private Paint baseBeaconPaint = new Paint();
    private float currentPosCircleSize = 20;
    private Paint currentPosCirclePaint = new Paint();
    private int scale = 80;
    private Paint neighboutCirclePaint = new Paint();
    private float nearestBeaconSize = 20;
    private Paint textPaint = new Paint();
    private Paint linePaint = new Paint();

    private Paint helpPointPaint = new Paint();
    private float helpPointSize = 15;


    public LocalizationMapView(Context context) {
        super(context);
        initPaint();
    }

    public LocalizationMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public LocalizationMapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint();
    }

    private void initPaint() {
        currentPosCirclePaint.setColor(Color.RED);
        neighboutCirclePaint.setColor(Color.BLUE);
        textPaint.setTextSize(50);

    }

    int count = 1;

    @Override
    protected void onDraw(Canvas canvas) {
        if (beaconLocalization == null) return;

        canvas.drawLine(0 * scale, 6 * scale, 12 * scale, 6 * scale, linePaint);
        canvas.drawLine(6 * scale, 0 * scale, 6 * scale, 12 * scale, linePaint);

        canvas.drawText("" + count++, 6 * scale, 6 * scale, textPaint);

        List<BaseBeaconInfo> baseBeaconInfos = beaconLocalization.getAllBaseBeacons();
        for (BaseBeaconInfo baseBeaconInfo : baseBeaconInfos) {
            canvas.drawCircle(baseBeaconInfo.getX() * scale, baseBeaconInfo.getY() * scale, baseBeaconCircleSize, baseBeaconPaint);
        }
        double[] currentPos = beaconLocalization.getPosition();

        List<double[]> neibours = beaconLocalization.getNeighbours();
        for (int i = 0; i < neibours.size(); i++) {
            double[] neighbour = neibours.get(i);
            if (i == 0) {
                neighboutCirclePaint.setColor(Color.BLUE);
            } else {
                neighboutCirclePaint.setColor(Color.GREEN);
            }
            canvas.drawCircle((float) neighbour[0] * scale, (float) neighbour[1] * scale, nearestBeaconSize, neighboutCirclePaint);
        }

        List<double[]> helpPoints = beaconLocalization.getHelpPoints();
        for (int i = 0; i < helpPoints.size(); i++) {
            double[] helpPoint = helpPoints.get(i);
            helpPointPaint.setColor(Color.argb(255, i * 100 % 255, 200, 200));
            canvas.drawCircle((float) helpPoint[0] * scale, (float) helpPoint[1] * scale, helpPointSize, helpPointPaint);
        }

        canvas.drawText(String.format("%.2f", beaconLocalization.getHelpInfo().getVariace()), 6 * scale, 6 * scale + 50, textPaint);


        if (currentPos != null) {
            Log.d(TAG, Arrays.toString(currentPos));
            canvas.drawCircle((float) currentPos[0] * scale, (float) currentPos[1] * scale, currentPosCircleSize, currentPosCirclePaint);
        }
    }


    public BeaconLocalization getBeaconLocalization() {
        return beaconLocalization;
    }

    public void setBeaconLocalization(BeaconLocalization beaconLocalization) {
        this.beaconLocalization = beaconLocalization;
        beaconLocalization.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object data) {
        postInvalidate();
    }

}
