package com.example.edson.convertemoeda1;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConverteMoedaActivity extends AppCompatActivity {
    private ListView mListView;
    private String mKey;
    public static final String URL_CODES = "http://openexchangerates.org/api/currencies.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converte_moeda);
        mListView = (ListView) findViewById(R.id.convertemoeda_listView);
        mKey = getKey("open_key");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                //contexto
                this,
                //layout (view)
                R.layout.converte_moeda_linha,
                //linha (view)
                R.id.linha_texto,
                //dados (model)
                new String[]{"primeiro registro", "segundo registro", "terceiro registro", mKey,
                        "outro registro","Obtem codigo das moedas"}

        );
        mListView.setAdapter(arrayAdapter);
        new ObtemCodigoDasMoedas().execute(URL_CODES);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_converte_moeda, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public String getKey(String keyName){
        AssetManager assetManager = this.getResources().getAssets();
        Properties properties = new Properties();
        try {
            InputStream inputStream = assetManager.open("keys.properties");
            properties.load(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  properties.getProperty(keyName);

    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
    private class ObtemCodigoDasMoedas extends AsyncTask<String, Void, JSONObject>{

        @Override
        protected JSONObject doInBackground(String... params) {
            return new JSONParser().getJSONFromUrl(URL_CODES);
        }
        @Override
        protected void onPostExecute(JSONObject jsonObject){
            try {
                if (jsonObject == null) {
                    throw new JSONException("dados nao disponiveis");
                }
            }
                catch (JSONException e){
                    Toast.makeText(ConverteMoedaActivity.this, "execeção do JSON " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
                }
            }
        }
}
