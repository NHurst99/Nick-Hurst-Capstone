package com.cecremote;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.cecremote.ui.main.SectionsPagerAdapter;
import com.harrysoft.androidbluetoothserial.BluetoothManager;
import com.harrysoft.androidbluetoothserial.BluetoothSerialDevice;
import com.harrysoft.androidbluetoothserial.SimpleBluetoothDeviceInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Main2Activity extends AppCompatActivity {


//
//    /**
//     * The {@link android.support.v4.view.PagerAdapter} that will provide
//     * fragments for each of the sections. We use a
//     * {@link FragmentPagerAdapter} derivative, which will keep every
//     * loaded fragment in memory. If this becomes too memory intensive, it
//     * may be best to switch to a
//     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
//     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
//
//    /**
//     * The {@link ViewPager} that will host the section contents.
//     */
    private ViewPager mViewPager;
    BluetoothManager bluetoothManager = null;
    private SimpleBluetoothDeviceInterface deviceInterface;
    private String deviceMAC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bluetoothManager = BluetoothManager.getInstance();
        if (bluetoothManager == null) {
            // Bluetooth unavailable on this device :( tell the user
            Toast.makeText(this, "Bluetooth not available.", Toast.LENGTH_LONG).show(); // Replace context with your context instance.
            finish();
        }
        List<BluetoothDevice> pairedDevices = bluetoothManager.getPairedDevicesList();
        List<BluetoothDevice> matchingDevices = new ArrayList<>();

        for (BluetoothDevice device : pairedDevices) {
            if(device.getName().equals(getString( R.string.device_name))) {
                matchingDevices.add(device);
//                TextView tv = new TextView(this);
//                tv.setText(device.getName());
//                tv.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        // do stuff here
//                        connectDevice(device.getAddress());
//                    }
//                });
//                ((LinearLayout) findViewById(R.id.deviceList)).addView(tv);
            }

            Log.d("BluetoothDevices", "Device name: " + device.getName());
            Log.d("BluetoothDevices", "Device MAC Address: " + device.getAddress());
        }
        if (matchingDevices.size() == 1){
            Log.d("BluetoothConnection","One device found, attempting to connect");
            connectDevice(matchingDevices.get(0).getAddress());
            deviceMAC = matchingDevices.get(0).getAddress();
        }else if(matchingDevices.size() == 0){
            Log.d("BluetoothConnection","No devices found");
        }else {
            Log.d("BluetoothConnection","Multiple devices found, connecting to first");
            connectDevice(matchingDevices.get(0).getAddress());
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));



//        findViewById(R.id.main_content)

}

    public void blah(View v){
        Main2Activity2 cf =(Main2Activity2) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + mViewPager.getCurrentItem());
//
        findViewById(R.id.powerButton).setOnTouchListener(holdListener);
        findViewById(R.id.inputButton).setOnTouchListener(holdListener);

        findViewById(R.id.pad_0).setOnTouchListener(holdListener);
        findViewById(R.id.pad_1).setOnTouchListener(holdListener);
        findViewById(R.id.pad_2).setOnTouchListener(holdListener);
        findViewById(R.id.pad_3).setOnTouchListener(holdListener);
        findViewById(R.id.pad_4).setOnTouchListener(holdListener);
        findViewById(R.id.pad_5).setOnTouchListener(holdListener);
        findViewById(R.id.pad_6).setOnTouchListener(holdListener);
        findViewById(R.id.pad_7).setOnTouchListener(holdListener);
        findViewById(R.id.pad_8).setOnTouchListener(holdListener);
        findViewById(R.id.pad_9).setOnTouchListener(holdListener);
        findViewById(R.id.dash_button).setOnTouchListener(holdListener);


        findViewById(R.id.up_button).setOnTouchListener(holdListener);
        findViewById(R.id.left_button).setOnTouchListener(holdListener);
        findViewById(R.id.right_button).setOnTouchListener(holdListener);
        findViewById(R.id.down_button).setOnTouchListener(holdListener);
        findViewById(R.id.dot_button).setOnTouchListener(holdListener);


        findViewById(R.id.channel_down_button).setOnTouchListener(holdListener);
        findViewById(R.id.channel_up_button).setOnTouchListener(holdListener);
        findViewById(R.id.vol_down_button).setOnTouchListener(holdListener);
        findViewById(R.id.vol_up_btton).setOnTouchListener(holdListener);


    }

    public void generateCommand(View v) {
        //TODO: Change dest device according to page
        String command = "F0";
        //check which destination is checked

        //User control pressed RCPT
        command += ":44:";

        switch(v.getId()) {
            case R.id.powerButton:
                command += "40";
                break;
            case R.id.inputButton:
                command += "34";
                break;


            case R.id.pad_0:
                command += "20";
                break;
            case R.id.pad_1:
                command += "21";
                break;
            case R.id.pad_2:
                command += "22";
                break;
            case R.id.pad_3:
                command += "23";
                break;
            case R.id.pad_4:
                command += "24";
                break;
            case R.id.pad_5:
                command += "25";
                break;
            case R.id.pad_6:
                command += "26";
                break;
            case R.id.pad_7:
                command += "27";
                break;
            case R.id.pad_8:
                command += "28";
                break;
            case R.id.pad_9:
                command += "29";
                break;
            case R.id.dash_button:
                command += "2A";
                break;


            case R.id.up_button:
                command += "01";
                break;
            case R.id.left_button:
                command += "03";
                break;
            case R.id.right_button:
                command += "04";
                break;
            case R.id.down_button:
                command += "02";
                break;
            case R.id.dot_button:
                command += "00";
                break;

            case R.id.channel_up_button:
                command += "30";
                break;
            case R.id.channel_down_button:
                command += "31";
                break;
            case R.id.vol_up_btton:
                command += "41";
                break;
            case R.id.vol_down_button:
                command += "42";
                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }
        command += ":";
        transmitCommand(command);


    }
    private boolean isFirstPress = true;
    public View.OnTouchListener holdListener = new View.OnTouchListener() {
        @Override
        public  boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN && isFirstPress) {
                generateCommand(v);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                transmitCommand("F0:45:");
            }

            return true;
        }

    };
    private void transmitCommand(String command) {
        //TODO: Add bluetooth nonsense
        if(deviceInterface == null){
            connectDevice(deviceMAC);
        }else{
        deviceInterface.sendMessage(command);
        Log.d("Command", command);

        }
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
        Snackbar.make(findViewById(R.id.toolbar), "Connected", Snackbar.LENGTH_LONG).show();
    }

    private void onMessageSent(String message) {
        Log.d("MessageSentSuccess", message);
    }

    private void onMessageReceived(String message) {
        // We received a message! Handle it here.
//        ((TextView)findViewById(R.id.recievedText)).setText(((TextView)findViewById(R.id.recievedText)).getText()+" " + message);
        Snackbar.make(findViewById(R.id.toolbar), message, Snackbar.LENGTH_LONG).show();
//        Toast.makeText(this, "Received a message! Message was: " + message, Toast.LENGTH_LONG).show(); // Replace context with your context instance.
    }

    private void onError(Throwable error) {
        // Handle the error
        Log.e("Bluetooth Error", error.getMessage());
    }
    }

//    ((TextView)findViewById(R.id.generatedCommand)).setText(command);
//    deviceInterface.sendMessage(command);

//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main2, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    /**
//     * A placeholder fragment containing a simple view.
//     */
//    public static class PlaceholderFragment extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        public PlaceholderFragment() {
//        }
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_main2, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
//            return rootView;
//        }
//
//    }
//
//    /**
//     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
//     * one of the sections/tabs/pages.
//     */
//    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
//
//        public SectionsPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            // getItem is called to instantiate the fragment for the given page.
//            // Return a PlaceholderFragment (defined as a static inner class below).
//            return PlaceholderFragment.newInstance(position + 1);
//        }
//
//        @Override
//        public int getCount() {
//            // Show 3 total pages.
//            return 6;
//        }
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return "OBJECT " + (position + 1);
//        }
//    }
