package com.example.revolut_test;

public class Currency {

    private String mCurrency;
    private String mValue;

    public Currency(String currency, String value) {
        mCurrency= currency;
        mValue = value;
    }

    public String getmCurrency() {
        return mCurrency;
    }

    public String getmValue() {
        return mValue;
    }

    public void setmValue(String param){
        mValue = param;
    }

}