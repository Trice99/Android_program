package com.example.skill_it_2021_09.NumberPicker;

import android.view.View;
import android.widget.EditText;

public class ActionListener implements View.OnClickListener {

    NumberPicker layout;
    ActionEnum action;
    EditText display;

    public ActionListener(NumberPicker layout, EditText display, ActionEnum action) {
        this.layout = layout;
        this.action = action;
        this.display = display;
    }

    @Override
    public void onClick(View v) {
        try {
            int newValue = Integer.parseInt(this.display.getText().toString());
            this.layout.setValue(newValue);
        } catch (NumberFormatException e) {
            this.layout.refresh();
        }

        switch (this.action) {
            case INCREMENT:
                this.layout.increment();
                break;
            case DECREMENT:
                this.layout.decrement();
                break;
        }
    }
}
