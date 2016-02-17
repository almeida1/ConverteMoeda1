package com.example.edson.convertemoeda1;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by edson on 15/02/2016.
 */
public class UC01ConsultaCotacao extends ActivityInstrumentationTestCase2<ConverteMoedaActivity> {
    private ConverteMoedaActivity mActivity;
    private String mKey;
    public UC01ConsultaCotacao(){
        super(ConverteMoedaActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        //obtem a activity sob teste
        mActivity = getActivity();
        mKey = mActivity.getKey("open_key"); //getKey foi modificado para public para permitir o teste
    }
    public void testCT01UC01CarregaMoedas_valida_chave_de_desenvolvedor() throws Exception, Throwable {
        assertEquals("f0ec339528da40cbb7836d39b8643cbb", mKey);

    }
    public void testCT02UC01CarregaMoedas_verifica_conexao_com_a_rede() throws Exception, Throwable {
        assertTrue(mActivity.isOnline());
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
