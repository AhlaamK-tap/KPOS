package com.tap.company.knetpos.kpos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.pax.unifiedsdk.factory.ITransAPI;
import com.pax.unifiedsdk.factory.TransAPIFactory;
import com.pax.unifiedsdk.message.BaseResponse;
import com.pax.unifiedsdk.message.GetLastTransMsg;
import com.pax.unifiedsdk.message.TransResponse;

public class LastTransactionActivity extends AppCompatActivity {
   TextView transactionDetailTv;
    ITransAPI transAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_transaction);
        transactionDetailTv = findViewById(R.id.lastTransTv);

GetLastTransMsg.Request  requestLast=  new GetLastTransMsg.Request();

        transAPI  = new KPOSConnect().connectTransAPI();

        transAPI.startTrans(this,requestLast);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

       BaseResponse baseResponse = transAPI.onResult(requestCode, resultCode, data );

        if (baseResponse instanceof GetLastTransMsg.Response){
               TransResponse transactionResponse =   (TransResponse ) baseResponse;

               StringBuilder sb = new StringBuilder();
            sb.append('\n' +transactionResponse.getTransactionNo() +
                    '\n' +transactionResponse.getRspMsg() +
                    '\n' +transactionResponse.getAmount() +
                    '\n' +transactionResponse.getAcquirerName() +
                    '\n' +transactionResponse.getCardNo() +
                    '\n' +transactionResponse.getAcqCode() +
                    '\n' +transactionResponse.getAid() +
                    '\n' +transactionResponse.getArpc() +
                    '\n' +transactionResponse.getArqc() +
                    '\n' +transactionResponse.getAtc() +
                    '\n' +transactionResponse.getBatchNo() +
                    '\n' +transactionResponse.getAuthCode() +
                    '\n' +transactionResponse.getAuthCode() +
                    '\n' +transactionResponse.getVoucherNo() +
                    '\n' +transactionResponse.getCardType() +
                    '\n' +transactionResponse.getCashAmount() +
                    '\n' +transactionResponse.getCountry() +
                    '\n' +transactionResponse.getEmvAppLabel() +
                    '\n' +transactionResponse.getEmvAppName() +
                    '\n' +transactionResponse.getIssuerCode() +
                    '\n' +transactionResponse.getIssuerName() +
                    '\n' +transactionResponse.getLanguage() +
                    '\n' +transactionResponse.getMerchantId() +
                    '\n' +transactionResponse.getMerchantName() +
                    '\n' +transactionResponse.getOrigTransactionNo() +
                    '\n' +transactionResponse.getOrigTradeNo() +
                    '\n' +transactionResponse.getOrigTransTime() +
                    '\n' +transactionResponse.getRefNo()+
                    '\n' +transactionResponse.getOrigVoucherNo() +
                    '\n' +transactionResponse.getExtraBundle() +
                    '\n' +transactionResponse.getTerminalId() +
                    '\n' +transactionResponse.getTradeType() +
                    '\n' +transactionResponse.getTransTime()



            );
            Log.e("last tran>>", sb.toString());

            transactionDetailTv.setText( "Last Transaction Details::"+"\n"+
                    "Transaction Response : " +transactionResponse.getRspMsg() + "\n" +
                    "Merchant name : "+transactionResponse.getMerchantName()+"\n"+
                    "MerchantID :" + transactionResponse.getMerchantId() +"\n"+"CardNumber :"+transactionResponse.getCardNo()+"\n"+"Terminal:"+transactionResponse.getTerminalId()+
                    "\n"+    "issuerName :"+transactionResponse.getIssuerName()+"\n"+"amount :"+transactionResponse.getAmount()+"\n"+"acquirerName :"+transactionResponse.getAcquirerName()+
                    "\n"+"transTime :"+transactionResponse.getTransTime()+ "\n"+"cardType :"+transactionResponse.getCardType()
            );
        }
    }
}