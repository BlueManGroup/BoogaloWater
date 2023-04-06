package com.example.myloginapp;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.myloginapp.R;

public class ThreeOptionButton extends LinearLayout {

    private Button button1, button2, button3;
    private Button activeButton;

    public ThreeOptionButton(Context context) {
        super(context);
        init();
    }

    public ThreeOptionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ThreeOptionButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.activity_three_button_option, this, true);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeButtonColor(button1);
            }
        });
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeButtonColor(button2);
            }
        });
        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeButtonColor(button3);
            }
        });

    }

    private void changeButtonColor(Button button) {
        if (activeButton != null) {
            activeButton.setBackgroundColor(Color.WHITE);
        }
        button.setBackgroundColor(Color.BLUE);
        activeButton = button;
    }

}


