package com.tap.company.knetpos.kpos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pax.unifiedsdk.factory.ITransAPI;
import com.pax.unifiedsdk.factory.TransAPIFactory;

import java.io.Serializable;

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

    public void openAuthorize(View view) {

        Intent intent = new Intent(getBaseContext(), AuthActivity.class);
       intent.putExtra("typeTransaction","AUTH");
        startActivity(intent);
    }

    public void openVoid(View view) {

        Intent intent = new Intent(getBaseContext(), VoidActivity.class);
       intent.putExtra("typeTransaction","VOID");
        startActivity(intent);
    }
    public void openRefund(View view) {
        Intent intent = new Intent(getBaseContext(), VoidActivity.class);
       intent.putExtra("typeTransaction","REFUND");
        startActivity(intent);
    }
    public void openSettle(View view) {
        Intent intent = new Intent(getBaseContext(), VoidActivity.class);
       intent.putExtra("typeTransaction","SETTLE");
        startActivity(intent);
    }

    public void openCashBack(View view) {
        Intent intent = new Intent(getBaseContext(), VoidActivity.class);
        intent.putExtra("typeTransaction","CASHBACK");
        startActivity(intent);
    }

    public void openReversal(View view) {
        Intent intent = new Intent(getBaseContext(), VoidActivity.class);
        intent.putExtra("typeTransaction","REVERSAL");
        startActivity(intent);
    }
}