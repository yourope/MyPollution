package com.example.europeolympia.myapplication.activities;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.europeolympia.myapplication.R;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by EuropeOlympia on 21/11/2016.
 */

public class BtActivity extends Activity {
    Button mVisibleButton;
    ToggleButton mOnOffButton;
    ImageButton mBtButton;
    ListView lv;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt);

        mVisibleButton = (Button) findViewById(R.id.buttonVisible);
        mOnOffButton = (ToggleButton) findViewById(R.id.toggleButton);
        mBtButton = (ImageButton) findViewById(R.id.imageButton);

        BA = BluetoothAdapter.getDefaultAdapter();
        lv = (ListView) findViewById(R.id.listView);

    }

    public void onToggleClicked(View view) {
        // Is the toggle on?
        boolean on = ((ToggleButton) view).isChecked();
        if (on) {
            if (!BA.isEnabled()) {
                Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnOn, 0);
                Toast.makeText(getApplicationContext(), "Turned on", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Already on", Toast.LENGTH_LONG).show();
            }
        } else {
            BA.disable();
            Toast.makeText(getApplicationContext(), "Turned off", Toast.LENGTH_LONG).show();
        }
    }

/*
    public void on(View v) {

    }

    public void off(View v) {

    }
*/
    public void visible(View v) {
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }


    public void listing(View v) {
        pairedDevices = BA.getBondedDevices();

        ArrayList myList = new ArrayList();

        for (BluetoothDevice bt : pairedDevices) myList.add(bt.getName());
        Toast.makeText(getApplicationContext(), "Showing Paired Devices", Toast.LENGTH_SHORT).show();

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myList);

        lv.setAdapter(adapter);
    }

}
