package com.example.skill_it_2021_09.NumberPicker;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

public class DefaultOnEditorActionListener implements TextView.OnEditorActionListener {

    NumberPicker layout;

    public DefaultOnEditorActionListener(NumberPicker layout) {
        this.layout = layout;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            try {
                int value = Integer.parseInt(v.getText().toString());
                layout.setValue(value);
            } catch (NumberFormatException e) {
                layout.refresh();
            }
        }
        return true;
    }
}
