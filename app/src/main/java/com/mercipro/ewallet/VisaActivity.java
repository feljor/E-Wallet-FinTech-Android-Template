package com.mercipro.ewallet;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mercipro.ewallet.data.WalletData;

import java.util.ArrayList;

public class VisaActivity extends AppCompatActivity {

    Toolbar toolbar;
    private BarChart chart;
    private LinearLayout showChart;
    private TextView textView, days;
    private FloatingActionButton fab;
    private ArrayList<BarEntry> barEntryArrayList;
    private ArrayList<String> labelNames;
    private ArrayList<WalletData> visaData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visa);

        initToolbar();
        chart = findViewById(R.id.chart);
        chart.setVisibility(View.GONE);
        textView = findViewById(R.id.textView);
        days = findViewById(R.id.days);
        textView.setText(getString(R.string.show_chart));
        fab = findViewById(R.id.fab_add);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VisaActivity.this, AddWalletsActivity.class));
                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
                finish();
            }
        });

        (findViewById(R.id.tv_filter)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(v);
            }
        });

        (findViewById(R.id.showChart)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doShowChart();
            }
        });

        labelNames = new ArrayList<>();
        barEntryArrayList = new ArrayList<>();

        populatevisaData();

        for (int i = 0; i < visaData.size(); i ++){
            String walletname = visaData.get(i).getName();
            int walletbalance = visaData.get(i).getBalance();

            barEntryArrayList.add(new BarEntry(i,walletbalance));
            labelNames.add(walletname);
        }

        BarDataSet barDataSet = new BarDataSet(barEntryArrayList, "Balance");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        Description description = new Description();
        description.setText("Visa");
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

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Manage Wallet");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSystemBarColor(this, R.color.colorPrimaryDark);
    }

    private void showDateDialog(final View v) {
        final String[] array = new String[]{
                "Last 7 days", "Last 28 days", "Last 3 months","This year"
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose an option");
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

    private void doShowChart() {

        if (chart.getVisibility()==View.GONE){
            chart.setVisibility(View.VISIBLE);
            textView.setText(getString(R.string.hide_chart));
        }
        else  {
            chart.setVisibility(View.GONE);
            textView.setText(getString(R.string.show_chart));
        }

    }

    private void populatevisaData(){
        visaData.clear();
        visaData.add(new WalletData("Income", 123));
        visaData.add(new WalletData("Spending", 326));
    }

    public static void setSystemBarColor(Activity act, @ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(act.getResources().getColor(color));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_right);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}