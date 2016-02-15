package com.example.edson.convertemoeda1;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by edson on 15/02/2016.
 */
public class UC01ConsultaCotacao extends ActivityInstrumentationTestCase2<ConverteMoedaActivity> {
    private ConverteMoedaActivity mActivity;
    public UC01ConsultaCotacao(){
        super(ConverteMoedaActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        //obtem a activity sob teste
        mActivity = getActivity();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
