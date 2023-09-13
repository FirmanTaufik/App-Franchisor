package com.appfranchisor.app.helper;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

public class XAxisValueFormatter extends ValueFormatter {
    private String[] values;

    public XAxisValueFormatter(String[] values) {
        this.values = values;
    }

    @Override
    public String getFormattedValue(float value) {
        return values[(int) value];
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        if((int) value < 0 && (int) value > values.length - 1){
            return "";
        }

        return values[(int) value];
    }
}
