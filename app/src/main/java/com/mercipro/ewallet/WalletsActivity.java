package com.mercipro.ewallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class WalletsActivity extends AppCompatActivity {

    Toolbar toolbar;
    CardView addWallet, visa, mastercard, paypal, bitcoin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallets);

        addWallet = findViewById(R.id.add_wallet);
        visa = findViewById(R.id.visa);
        mastercard = findViewById(R.id.mastercard);
        paypal = findViewById(R.id.paypal);
        bitcoin = findViewById(R.id.bitcoin);

        initToolbar();

        addWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WalletsActivity.this, AddWalletsActivity.class));
                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
                finish();
            }
        });

        visa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoWallet();
            }
        });

        mastercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoWallet();
            }
        });

        paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoWallet();
            }
        });

        bitcoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoWallet();
            }
        });

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Wallets");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSystemBarColor(this, R.color.colorPrimaryDark);
    }

    public void gotoWallet() {
        startActivity(new Intent(getApplicationContext(), VisaActivity.class));
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
        finish();
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