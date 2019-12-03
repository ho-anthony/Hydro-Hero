package com.example.hydrohomie.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

        final View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        graphView = rootView.findViewById(R.id.graph);
        totalText = rootView.findViewById(R.id.totalText);
        datePattern.setTimeZone(java.util.TimeZone.getTimeZone("GMT-8"));
        ArrayList<String> dateList = database.getAllDates();
        Spinner sp = rootView.findViewById(R.id.dateFilter);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,dateList);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.fragment_history, R.id.dateText,dateList);
        sp.setAdapter(adapter);


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

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                graphView.removeAllSeries();
                graphView.getViewport().setScrollable(true);
                int size = database.numberOfDates(i);
                graphView.getGridLabelRenderer().setNumHorizontalLabels(size-3);
                if(size == 7) {
                    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(database.getDataPoint(i*7,size));
                    graphView.refreshDrawableState();
                    series.setDrawDataPoints(true);
                    series.setDataPointsRadius(12);
                    series.setThickness(5);
                    graphView.addSeries(series);
                    totalText = (TextView) rootView.findViewById(R.id.totalText);
                    totalText.setText("Total Hydration: " + database.totalHydration(i) + " OZ");
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return rootView;
    }
    
}
