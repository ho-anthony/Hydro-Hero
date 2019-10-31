package com.example.hydrohomie.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hydrohomie.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class HistoryFragment extends Fragment {
    GraphView graphView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        double x,y;
        x=-5.0;

        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        graphView = rootView.findViewById(R.id.graph);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(getDataPoint());
        graphView.addSeries(series);
        return rootView;
    }

    private DataPoint[] getDataPoint() {
        DataPoint[] dp = new DataPoint[] {
                new DataPoint(0,1),
                new DataPoint(1,4),
                new DataPoint(2,8),
                new DataPoint(3,3),
                new DataPoint(4,11),
                new DataPoint(5,5),
                new DataPoint(6,0),
        };
        return dp;
    }
}
