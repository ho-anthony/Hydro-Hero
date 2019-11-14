package com.example.hydrohomie.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hydrohomie.R;
import com.example.hydrohomie.database.DatabaseHelper;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryFragment extends Fragment {
    GraphView graphView;
    DatabaseHelper database;
    SimpleDateFormat datePattern = new SimpleDateFormat("MM/dd");
    TextView totalText;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        database = new DatabaseHelper(getContext());

        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        graphView = rootView.findViewById(R.id.graph);
        totalText = rootView.findViewById(R.id.totalText);
        datePattern.setTimeZone(java.util.TimeZone.getTimeZone("GMT-8"));


        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return datePattern.format(new Date((long) value*1000));
                } else {
                    // show currency for y values
                    return super.formatLabel(value, isValueX);
                }
            }
        });

        graphView.getViewport().setScrollable(true);
        //graphView.getGridLabelRenderer().setNumHorizontalLabels(3);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(database.getDataPoint());
        graphView.addSeries(series);
        return rootView;
    }
    
}
