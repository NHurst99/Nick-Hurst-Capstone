package com.cecremote;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.cecremote.ui.main.PlaceholderFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cecremote.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);
//        sectionsPagerAdapter.setCount(5);
        tabs.getTabAt(0).setText("TV");
//        tabs.addTab(tabs.newTab().setText("TV"));
//        tabs.addTab(tabs.newTab().setText("Chromecast"));
//        tabs.addTab(tabs .newTab().setText("Speaker"));
//        tabs.addTab(tabs.newTab().setText("Switch"));
//        tabs.addTab(tabs.newTab().setText("PS4"));
        SharedPreferences settings = getApplicationContext().getSharedPreferences("options", 0);
        checkedItems = optionStringToBool(settings.getString("options0", ""));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] listItems = {"Power Controls", "Navigation", "Volume", "Channel", "Media Control"};

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Choose items");
                builder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedItems[which] = isChecked;
//                        String options = optionsBoolToString(checkedItems);
//
//
//                        SharedPreferences settings = getApplicationContext().getSharedPreferences("options", 0);
//                        SharedPreferences.Editor editor = settings.edit();
//                        editor.putString("options0", options);
//                        editor.apply();
                    }

                });

                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences settings = getApplicationContext().getSharedPreferences("options", 0);
                        SharedPreferences.Editor editor = settings.edit();
                        String options = optionsBoolToString(checkedItems);
                        editor.putString("options0",  options);
                        editor.apply();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.constraintLayout, PlaceholderFragment.newInstance(0, options)).commit();

                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    boolean[] checkedItems = null;
    String[] words = new String[]{"poweronoff", "navigation", "volume", "channel", "media"}; //Possible options

    private boolean[] optionStringToBool(String options){
        boolean[] boolOptions = new boolean[words.length];
        for(int i=0; i < boolOptions.length; i++){
            if(options.contains(words[i]))
                boolOptions[i] = true;
        }
        return boolOptions;
    }

    private String optionsBoolToString(boolean[] options){
        String stringOptions = "";
        for(int i=0; i<options.length; i++){
            if(options[i])
                stringOptions+=words[i];
        }
        return stringOptions;
    }

}