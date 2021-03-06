package com.leocai.beaconlocalization.train;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import java.util.List;

/**
 * Created by leocai on 15-4-5.
 */
public class TrainBtnListener implements View.OnClickListener {
    private final int index;
    private final CuIndexSetter trainActivity;
    private final List<Button> btns;

    public TrainBtnListener(int index, CuIndexSetter trainActivity, List<Button> btns) {
        this.index = index;
        this.trainActivity = trainActivity;
        this.btns = btns;
    }

    @Override
    public void onClick(View v) {
        trainActivity.setCuTrainIndex(index);
        for(Button btn:btns){
            btn.setBackgroundColor(Color.GRAY);
        }
        v.setBackgroundColor(Color.GREEN);
    }
}
