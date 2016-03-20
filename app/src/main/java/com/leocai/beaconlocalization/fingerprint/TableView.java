package com.leocai.beaconlocalization.fingerprint;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leocai.beaconlocalization.uitls.MathUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leocai on 15-4-5.
 */
public class TableView extends LinearLayout {


    private TextView tvTop;
    private TextView tvBottom;
    private Button btnSmall;
    private Button btnLarger;
    private double threhold;
    private List<Double> values = new ArrayList<Double>();

    public TableView(Context context) {
        super(context);
        initView();
    }

    public TableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TableView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView(){
        tvTop = new TextView(getContext());
        tvTop.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        tvTop.setText("average:\nsqrt:\n");
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(HORIZONTAL);
//        linearLayout.setGravity(Gravity.CENTER);
        btnSmall = new Button(getContext());
        btnSmall.setText("-");
        btnSmall.setMinWidth(120);
        tvBottom = new TextView(getContext());
        tvBottom.setText(""+threhold);
        btnLarger = new Button(getContext());
        btnLarger.setText("+");
        btnLarger.setMinWidth(120);

        linearLayout.addView(btnSmall);
        linearLayout.addView(tvBottom);
        linearLayout.addView(btnLarger);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        addView(tvTop);
        addView(linearLayout);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        btnSmall.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                threhold -= 0.5;
                tvBottom.setText(""+threhold);
            }
        });
        btnLarger.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                threhold += 0.5;
                tvBottom.setText(""+threhold);
            }
        });
    }

    public void setText(String text){
        tvTop.setText(text);
    }

    public double getThrehold() {
        return threhold;
    }

    public void setThrehold(double threhold) {
        this.threhold = threhold;
        tvBottom.setText(""+threhold);
    }

    public void addValue(double distance) {
        values.add(distance);
    }

    public void refresh() {
        if(values.size()==0) return;
        double average = MathUtils.average(values);
        double varience = MathUtils.sqrtVarience(values);
        values.clear();

        setText("average:"+String.format("%.2f",average)+"\n"+"varience:"+String.format("%.2f",varience));
        if(average>threhold){
            setBackgroundColor(Color.GRAY);
        }else{
            setBackgroundColor(Color.RED);
        }
    }
}
