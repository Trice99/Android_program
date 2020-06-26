package com.example.skill_it_2021_09.NumberPicker;

import android.view.View;
import android.widget.EditText;

public class DefaultOnFocusChangeListener implements View.OnFocusChangeListener {

    NumberPicker layout;

    public DefaultOnFocusChangeListener(NumberPicker layout) {
        this.layout = layout;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        EditText editText = (EditText) v;

        if (!hasFocus) {
            try {
                int value = Integer.parseInt(editText.getText().toString());
                layout.setValue(value);
            } catch (NumberFormatException e) {
                layout.refresh();
            }
        }
    }
}
