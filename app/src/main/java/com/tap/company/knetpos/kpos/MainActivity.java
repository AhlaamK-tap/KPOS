package com.tap.company.knetpos.kpos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void openSale( View view) {
        Intent intent = new Intent(getBaseContext(), SaleActivity.class);
        startActivity(intent);
    }

    public void showLastTransaction(View view) {
        Intent intent = new Intent(getBaseContext(), LastTransactionActivity.class);
        startActivity(intent);
    }

}