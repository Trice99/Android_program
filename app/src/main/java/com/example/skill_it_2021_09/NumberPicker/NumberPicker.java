package com.example.skill_it_2021_09.NumberPicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.skill_it_2021_09.R;

import org.jetbrains.annotations.NotNull;

public class NumberPicker extends LinearLayout {

    private int unit;
    private int currentValue;

    private EditText displayEditText;


    public NumberPicker(Context context) {
        super(context, null);
    }

    public NumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initialize(context, attrs);
    }

    public NumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initialize(@NotNull Context context, AttributeSet attrs) {
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.NumberPicker, 0, 0);
        int minValue = attributes.getInteger(R.styleable.NumberPicker_min, 0);
        int maxValue = attributes.getInteger(R.styleable.NumberPicker_max, 999999);
        this.currentValue = attributes.getInteger(R.styleable.NumberPicker_value, 0);
        this.unit = attributes.getInteger(R.styleable.NumberPicker_unit, 1);
        int layout = attributes.getResourceId(R.styleable.NumberPicker_custom_layout, R.layout.custom_numberpicker);
        boolean focusable = attributes.getBoolean(R.styleable.NumberPicker_focusable, false);

        if (this.currentValue > maxValue) this.currentValue = maxValue;
        if (this.currentValue < minValue) this.currentValue = minValue;

        LayoutInflater.from(context).inflate(layout, this, true);

        Button decrementButton = findViewById(R.id.decrement);
        Button incrementButton = findViewById(R.id.increment);
        this.displayEditText = findViewById(R.id.display);

        incrementButton.setOnClickListener(new ActionListener(this, this.displayEditText, ActionEnum.INCREMENT));
        decrementButton.setOnClickListener(new ActionListener(this, this.displayEditText, ActionEnum.DECREMENT));

        this.setOnFocusChangeListener(new DefaultOnFocusChangeListener(this));
        this.setOnEditorActionListener(new DefaultOnEditorActionListener(this));
        this.setDisplayFocusable(focusable);
        this.refresh();
    }

    @SuppressLint("SetTextI18n")
    public void refresh() {
        this.displayEditText.setText(Integer.toString(this.currentValue));
    }

    public void clearFocus() {
        this.displayEditText.clearFocus();
    }

    public void setValue(int value) {
        this.currentValue = value;
        this.refresh();
    }

    public int getValue() {
        return this.currentValue;
    }

    public void setOnEditorActionListener(TextView.OnEditorActionListener onEditorActionListener) {
        this.displayEditText.setOnEditorActionListener(onEditorActionListener);
    }

    public void setOnFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        this.displayEditText.setOnFocusChangeListener(onFocusChangeListener);
    }

    public void setDisplayFocusable(boolean focusable) {
        this.displayEditText.setFocusable(focusable);
        if (focusable) {
            this.displayEditText.setFocusableInTouchMode(true);
        }
    }

    public void increment() {
        this.changeValueBy(this.unit);
    }

    public void decrement() {
        this.changeValueBy(-this.unit);
    }

    @SuppressLint("SetTextI18n")
    private void changeValueBy(int unit) {
        this.setValue(this.currentValue + unit);
    }
}