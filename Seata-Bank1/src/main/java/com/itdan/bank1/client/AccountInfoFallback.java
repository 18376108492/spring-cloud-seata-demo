package com.itdan.bank1.client;

public class AccountInfoFallback implements AccountInfoSvc {


    @Override
    public boolean updateAmt(Double amt) {
        return false;
    }
}
