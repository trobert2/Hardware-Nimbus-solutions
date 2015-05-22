package com.solutions.nimbus.doorbell;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.solutions.nimbus.doorbell.gcm.AdventureGCMUtil;
import com.solutions.nimbus.doorbell.util.DoorbellEntrySource;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class Home extends ListActivity {
    private DoorbellEntrySource datasource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        datasource = new DoorbellEntrySource(this);


        List<DoorbellEntry> doorbellEntryList = null;
        try {
            doorbellEntryList = datasource.getAllComments();
            Collections.reverse(doorbellEntryList);
//            Arrays.sort(doorbellEntryList, Collections.reverseOrder());
            ArrayAdapter<DoorbellEntry> adapter = new ArrayAdapter<DoorbellEntry>(this,
                    android.R.layout.simple_list_item_1, doorbellEntryList);

            setListAdapter(adapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (AdventureGCMUtil.checkPlayServices(this)) {

            AdventureGCMUtil.registrateInGCM(getApplicationContext(), this);

        } else {
//            Log.i(TAG, "No valid Google Play Services APK found.");
        }

//        Log.i(TAG, "ID " + AdventureGCMUtil.getRegId());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {

        super.onResume();
        this.onCreate(null);
    }
}
