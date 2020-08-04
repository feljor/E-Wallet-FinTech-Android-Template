package com.mercipro.ewallet.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.mercipro.ewallet.MainActivity;
import com.mercipro.ewallet.R;
import com.mercipro.ewallet.data.WalletData;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private MainActivity activity;

    private BarChart chart;
    private TextView days;
    private ArrayList<BarEntry> barEntryArrayList = new ArrayList<>();
    private ArrayList<String> labelNames = new ArrayList<>();
    private ArrayList<WalletData> walletData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        (v.findViewById(R.id.tv_filter)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendarData(v);
            }
        });

        chart = v.findViewById(R.id.chart);
        days = v.findViewById(R.id.days);

        populateWalletData();

        for (int i = 0; i < walletData.size(); i ++){
            String walletname = walletData.get(i).getName();
            int walletbalance = walletData.get(i).getBalance();

            barEntryArrayList.add(new BarEntry(i,walletbalance));
            labelNames.add(walletname);
        }

        BarDataSet barDataSet = new BarDataSet(barEntryArrayList, "Balance");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        Description description = new Description();
        description.setText("Wallets");
        chart.setDescription(description);

        BarData barData = new BarData(barDataSet);
        chart.setData(barData);

        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelNames));

        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(labelNames.size());

        chart.animateY(2000);
        chart.invalidate();

        return v;
    }

    private void showCalendarData(final View v) {
        final String[] array = new String[]{
                "Last 7 days", "Last 28 days", "Last 3 months","This year"
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select an option");
        builder.setSingleChoiceItems(array, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((TextView) v).setText(array[i]);
                days.setText(array[i]);
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }


    private void populateWalletData(){
        walletData.clear();
        walletData.add(new WalletData("Mastercard", 9903));
        walletData.add(new WalletData("Visa", 12113));
        walletData.add(new WalletData("Bitcoin", 6371));
        walletData.add(new WalletData("Paypal", 10046));
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

}
