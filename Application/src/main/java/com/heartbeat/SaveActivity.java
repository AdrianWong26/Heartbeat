package com.heartbeat;

import android.app.Activity;
import android.content.ContextWrapper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bluetoothchat.R;
import com.pixplicity.easyprefs.library.Prefs;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SaveActivity extends Activity {

    public static final String savedStat1 = "saved_stat_1";
    public static final String savedStat2 = "saved_stat_2";

    @Bind(R.id.stat1) EditText mStat1TextView;
    @Bind(R.id.stat2) EditText mStat2TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        ButterKnife.bind(this);

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();


        loadSavedValue();
    }

    @OnClick(R.id.load)
    public void loadSavedValue() {
        double stat1 = Prefs.getDouble(savedStat1, 0);
        double stat2 = Prefs.getDouble(savedStat2, 0);

        mStat1TextView.setText(Double.toString(stat1));
        mStat2TextView.setText(Double.toString(stat2));

        Toast.makeText(this, "Loaded values", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.save)
    public void saveValue() {
        String value1String = mStat1TextView.getText().toString().trim();
        double value1 = Double.parseDouble(value1String);

        String value2String = mStat2TextView.getText().toString().trim();
        double value2 = Double.parseDouble(value2String);

        Prefs.putDouble(savedStat1, value1);
        Prefs.putDouble(savedStat2, value2);

        Toast.makeText(this, "Value saved", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_save, menu);
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
}
