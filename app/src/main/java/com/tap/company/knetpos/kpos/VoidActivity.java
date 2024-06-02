package com.tap.company.knetpos.kpos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pax.unifiedsdk.factory.ITransAPI;
import com.pax.unifiedsdk.message.AuthComMsg;
import com.pax.unifiedsdk.message.BaseResponse;
import com.pax.unifiedsdk.message.SaleMsg;
import com.pax.unifiedsdk.message.VoidMsg;
import com.pax.unifiedsdk.sdkconstants.SdkConstants;

import java.util.Objects;

public class VoidActivity extends AppCompatActivity {
    AppCompatEditText amountET;
    AppCompatEditText tipAmountET;
    Button startSaleBtn;
    ITransAPI transAPI;
    String transactionType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_void);
        initVews();
        transactionType =   Objects.requireNonNull(Objects.requireNonNull(getIntent().getExtras()).get("typeTransaction")).toString();

    }

    private void initVews () {
        amountET = findViewById(R.id.amountEdit);
        tipAmountET = findViewById(R.id.tipAmountEdit);
        startSaleBtn = findViewById(R.id.btnStartSale);

        if(transactionType.equals("VOID")){
            tipAmountET.setVisibility(View.GONE);
            amountET.setVisibility(View.GONE);
        }

    /*   startSaleBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(transactionType.equals("AUTH")){
                   startAuth(v);
               }else if(transactionType .equals("VOID")){

               }

           }
       });*/

    }



    public void startVoid(View view) {

        //Create transaction API
        transAPI = new KPOSConnect().connectTransAPI();
        System.out.println("transAPI>>"+transAPI);

        //Create request message which is a object
        VoidMsg.Request request = new VoidMsg.Request();
        request.setCategory(SdkConstants.CATEGORY_VOID);

//Active sale transaction
        transAPI.startTrans(this, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        BaseResponse baseResponse = transAPI.onResult(requestCode, resultCode, data);
        // SaleMsg.Response transactionResponse =   (SaleMsg.Response ) baseResponse;

        StringBuilder sb = new StringBuilder();
        sb.append('\n' + "transaction no : " +((SaleMsg.Response) baseResponse).getTransactionNo() +
                        '\n'+ "resg msg  : " + ((SaleMsg.Response) baseResponse).getRspMsg() +// important
                        '\n'+ "amount  : " + ((SaleMsg.Response) baseResponse).getAmount() +
                        '\n' + "acquirer name  : " +((SaleMsg.Response) baseResponse).getAcquirerName() +
                        '\n' + "cardNo : " +((SaleMsg.Response) baseResponse).getCardNo() +
                        '\n' + "acquirer code : " +((SaleMsg.Response) baseResponse).getAcqCode() +
                        '\n' + "AID : " +((SaleMsg.Response) baseResponse).getAid() +
                        '\n' + "ARPC: " +((SaleMsg.Response) baseResponse).getArpc() +
                        '\n' + "argc no : " +((SaleMsg.Response) baseResponse).getArqc() +
                        '\n' + "atc: " +((SaleMsg.Response) baseResponse).getAtc() +
                        '\n' + "acq code : " +((SaleMsg.Response) baseResponse).getAcqCode() +
                        '\n' + "acquire name: " +((SaleMsg.Response) baseResponse).getAcquirerName() +
                        '\n' + "auth code : " +((SaleMsg.Response) baseResponse).getAuthCode() + // important in case of approved transaction
                        '\n' + "batch no : " +((SaleMsg.Response) baseResponse).getBatchNo() +
                        '\n' + "cardType : " +((SaleMsg.Response) baseResponse).getCardType() +
                        '\n' + "card holder signature : " +((SaleMsg.Response) baseResponse).getCardholderSignature() +
                        '\n' + "cash amount: " +((SaleMsg.Response) baseResponse).getCashAmount() +
                        '\n' + "country : " +((SaleMsg.Response) baseResponse).getCountry() +
                        '\n' + "issuer code : " +((SaleMsg.Response) baseResponse).getIssuerCode() +
                        '\n' + "issuer name : " +((SaleMsg.Response) baseResponse).getIssuerName() +
                        '\n' + "language : " +((SaleMsg.Response) baseResponse).getLanguage() +
                        '\n' + "merchantId : " +((SaleMsg.Response) baseResponse).getMerchantId() +
                        '\n' + "merchantName : " +((SaleMsg.Response) baseResponse).getMerchantName() +
                        '\n' + "ori transaction type : " +((SaleMsg.Response) baseResponse).getOriTransactionType() +
                        '\n' + "ori Trade No : " +((SaleMsg.Response) baseResponse).getOrigTradeNo() +
                        '\n' + "orig Trans Time : " +((SaleMsg.Response) baseResponse).getOrigTransTime() +
                        '\n' + "ref no : " +((SaleMsg.Response) baseResponse).getRefNo()+
                        '\n' + "voucherNo : " +((SaleMsg.Response) baseResponse).getVoucherNo() +// transaction sequence no - similar to receipt // if we will print Tap receipt - we have to use
                        '\n' + "extra bundle : " +((SaleMsg.Response) baseResponse).getExtraBundle() +
                        '\n' + "terminalId : " +((SaleMsg.Response) baseResponse).getTerminalId() +
                        '\n' + "Trade type : " +((SaleMsg.Response) baseResponse).getTradeType() +
                        '\n' + "transaction time : " +((SaleMsg.Response) baseResponse).getTransTime() +
                        '\n' + "transactionType : " +((SaleMsg.Response) baseResponse).getTransactionType() // important
                //  '\n' + "getTransRspMsg time : " + ((SaleMsg.Response) baseResponse).getTransRspMsg()

        );

        Log.e("VOID Activity", sb.toString() );
/*try {

    jsonObject.put("responseType", "PaymentResponse");

    jsonObject.put("transId", transId);

    jsonObject.put("merchantName", ((SaleMsg.Response) baseResponse).getMerchantName());

    jsonObject.put("merchantId", ((SaleMsg.Response) baseResponse).getMerchantId());

    jsonObject.put("terminalId", ((SaleMsg.Response) baseResponse).getTerminalId());

    jsonObject.put("cardNo", ((SaleMsg.Response) baseResponse).getCardNo());

    jsonObject.put("cardType", ((SaleMsg.Response) baseResponse).getCardType());

    jsonObject.put("voucherNo", ((SaleMsg.Response) baseResponse).getVoucherNo());
        // transaction sequence no - similar to receipt
        // if we will print Tap receipt - we have to use


    jsonObject.put("issuerName", ((SaleMsg.Response) baseResponse).getIssuerName());

    jsonObject.put("acquirerName", ((SaleMsg.Response) baseResponse).getAcquirerName());

    jsonObject.put("refNo", ((SaleMsg.Response) baseResponse).getRefNo());

    jsonObject.put("transTime", ((SaleMsg.Response) baseResponse).getTransTime());

    jsonObject.put("amount", ((SaleMsg.Response) baseResponse).getAmount());

    jsonObject.put("cashAmount", ((SaleMsg.Response) baseResponse).getCashAmount());

    jsonObject.put("authCode", ((SaleMsg.Response) baseResponse).getAuthCode()); - important in case of approved transaction

    jsonObject.put("transRespCode", ((SaleMsg.Response) baseResponse).getTransRspCode());

    jsonObject.put("transRspMsg", ((SaleMsg.Response) baseResponse).getTransRspMsg()); - important

    jsonObject.put("transactionNo", ((SaleMsg.Response) baseResponse).getTransactionNo());

   jsonObject.put("tradeNo", ((SaleMsg.Response) baseResponse).getTradeNo());

    jsonObject.put("tradeType", ((SaleMsg.Response) baseResponse).getTradeType());

    jsonObject.put("issuerCode", ((SaleMsg.Response) baseResponse).getIssuerCode());

    jsonObject.put("acqCode", ((SaleMsg.Response) baseResponse).getAcqCode());

    jsonObject.put("origAuthNo", ((SaleMsg.Response) baseResponse).getOrigAuthNo());

    jsonObject.put("origVoucherNo", ((SaleMsg.Response) baseResponse).getVoucherNo());

    jsonObject.put("origRefNo", ((SaleMsg.Response) baseResponse).getRefNo());

   jsonObject.put("origTransTime", ((SaleMsg.Response) baseResponse).getOrigTransTime());

    jsonObject.put("origTransactionNo", ((SaleMsg.Response) baseResponse).getOrigTransactionNo());

   jsonObject.put("origTradeNo", ((SaleMsg.Response) baseResponse).getOrigTradeNo());

    jsonObject.put("transactionType", ((SaleMsg.Response) baseResponse).getTransactionType()); // important

    jsonObject.put("oriTransactionType", ((SaleMsg.Response) baseResponse).getOriTransactionType());

    jsonObject.put("language", ((SaleMsg.Response) baseResponse).getLanguage());

    jsonObject.put("tips", ((SaleMsg.Response) baseResponse).getTips());

   jsonObject.put("aid", ((SaleMsg.Response) baseResponse).getAid()); // important for receipt

    jsonObject.put("emvAppLabel", ((SaleMsg.Response) baseResponse).getEmvAppLabel());

    jsonObject.put("emvAppName", ((SaleMsg.Response) baseResponse).getEmvAppName());

   jsonObject.put("rspMsg", ((SaleMsg.Response) baseResponse).getRspMsg()); // important

    jsonObject.put("deviceId" , deviceID); // extra


} catch (JSONException e) {
}
 */

        Toast.makeText(this,baseResponse.getRspMsg(),Toast.LENGTH_LONG).show();
    }
}