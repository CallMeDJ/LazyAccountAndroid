package com.callmedj.accountforlazy.counter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activeandroid.query.Select;
import com.callmedj.accountforlazy.entity.FinancialRecord;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import callmedj.com.accountforlazy.R;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StatisticsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StatisticsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatisticsFragment extends Fragment {

    private Context context;
    private View rootView ;
    private PieChart pieChart;
    private PieData pieData = new PieData();
    private ArrayList<Integer> colors = new ArrayList<Integer>();
    private Integer colorsLenth = 0;
    public StatisticsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_statistics, container, false);
        return rootView;
    }

    private void initData(){
        List<FinancialRecord> financialRecordList = new Select().from(FinancialRecord.class).execute();
        List<PieEntry> entries = new ArrayList<PieEntry>();
        for(FinancialRecord financialRecord : financialRecordList){
            PieEntry sliceValue = new PieEntry(financialRecord.getAccount().floatValue(),financialRecord.getItem());

            entries.add(sliceValue);
        }
        PieDataSet pieDataSet = new PieDataSet(entries,"败家记录");

        pieDataSet.setColors(colors);
        pieDataSet.setValueFormatter(new PercentFormatter());
        pieDataSet.setValueTextSize(11f);
        pieDataSet.setValueTextColor(getRandomColor());

        pieData.setDataSet(pieDataSet);

        pieChart.setData(pieData);
        pieChart.invalidate();
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        Legend l = pieChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        pieChart.setEntryLabelColor(getRandomColor());
        pieChart.setEntryLabelTextSize(12f);


    }

    public int getRandomColor(){
        List<Integer> colorList = this.getColors();
        return colorList.get((int) Math.round(Math.random() * colorsLenth));
    }

    public List<Integer> getColors(){
        if(colors.isEmpty()){

            for (int c : ColorTemplate.VORDIPLOM_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.JOYFUL_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.COLORFUL_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.LIBERTY_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.PASTEL_COLORS)
                colors.add(c);

            colors.add(ColorTemplate.getHoloBlue());
            colorsLenth = colors.size();
        }

        return colors;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }
    @Override
    public void onStart(){
        super.onStart();
        pieChart = (PieChart) rootView.findViewById(R.id.pieChart);
        initData();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
