package com.tap.company.knetpos.kpos;

import com.pax.unifiedsdk.factory.ITransAPI;
import com.pax.unifiedsdk.factory.TransAPIFactory;

public class KPOSConnect {
    ITransAPI transAPI;

    public  ITransAPI connectTransAPI() {
        transAPI =  TransAPIFactory.createTransAPI();
        return transAPI;
    }
}
