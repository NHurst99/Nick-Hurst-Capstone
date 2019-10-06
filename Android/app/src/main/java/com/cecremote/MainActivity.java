package com.cecremote;

import android.bluetooth.BluetoothDevice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.harrysoft.androidbluetoothserial.BluetoothManager;
import com.harrysoft.androidbluetoothserial.BluetoothSerialDevice;
import com.harrysoft.androidbluetoothserial.SimpleBluetoothDeviceInterface;

import org.w3c.dom.Text;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    BluetoothManager bluetoothManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothManager = BluetoothManager.getInstance();
        if (bluetoothManager == null) {
            // Bluetooth unavailable on this device :( tell the user
            Toast.makeText(this, "Bluetooth not available.", Toast.LENGTH_LONG).show(); // Replace context with your context instance.
            finish();
        }
        List<BluetoothDevice> pairedDevices = bluetoothManager.getPairedDevicesList();
        for (BluetoothDevice device : pairedDevices) {
            TextView tv = new TextView(this);
            tv.setText(device.getName());
            tv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // do stuff here
                    connectDevice(device.getAddress());
                }
            });
            ((LinearLayout)findViewById(R.id.deviceList)).addView(tv );
            Log.d("My Bluetooth App", "Device name: " + device.getName());
            Log.d("My Bluetooth App", "Device MAC Address: " + device.getAddress());
        }



    }

    private SimpleBluetoothDeviceInterface deviceInterface;


    public void commandSend(View v) {
        // Code here executes on main thread after user presses button
        String command = "F0";
        //check which destination is checked

//        Button checkedDestButton = findViewById(((RadioGroup)(findViewById(R.id.radioGroup))).getCheckedRadioButtonId());
//        if (checkedDestButton.getId() == -1)
//        {
//            // no radio buttons are checked
//        }
//        else
//        {
//            switch (checkedDestButton.getId()){
//                case R.id.destPlay1:
//                    command += "4";
//                    break;
//                case R.id.destTV:
//                    command += "0";
//                    break;
//            }
//            // one of the radio buttons is checked
//        }

        command += ":44:";

        switch(v.getId()) {
            case R.id.powerButton:
                // handle button A click;
                command += "40:";
                break;
            case R.id.inputButton:
                // handle button B click;
                command += "34:";
                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }

        ((TextView)findViewById(R.id.generatedCommand)).setText(command);
        deviceInterface.sendMessage(command);

    }
    private void connectDevice(String mac) {
        bluetoothManager.openSerialDevice(mac)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onConnected, this::onError);
    }
    private void onConnected(BluetoothSerialDevice connectedDevice) {
        // You are now connected to this device!
        // Here you may want to retain an instance to your device:
        deviceInterface = connectedDevice.toSimpleDeviceInterface();

        // Listen to bluetooth events
        deviceInterface.setListeners(this::onMessageReceived, this::onMessageSent, this::onError);

//        // Let's send a message:
//        deviceInterface.sendMessage("Hello world!");

        Toast.makeText(this, "Connected", Toast.LENGTH_LONG).show(); // Replace context with your context instance.
    }

    private void onMessageSent(String message) {
        // We sent a message! Handle it here.
        Toast.makeText(this, "Sent a message! Message was: " + message, Toast.LENGTH_LONG).show(); // Replace context with your context instance.
    }

    private void onMessageReceived(String message) {
        // We received a message! Handle it here.
        ((TextView)findViewById(R.id.recievedText)).setText(message);
        Toast.makeText(this, "Received a message! Message was: " + message, Toast.LENGTH_LONG).show(); // Replace context with your context instance.
    }

    private void onError(Throwable error) {
        // Handle the error
    }

}
