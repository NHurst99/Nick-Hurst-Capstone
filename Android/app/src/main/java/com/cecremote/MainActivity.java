package com.cecremote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void commandSend(View v) {
        // Code here executes on main thread after user presses button
        String command = "F";
        //check which destination is checked

        Button checkedDestButton = findViewById(((RadioGroup)(findViewById(R.id.radioGroup))).getCheckedRadioButtonId());
        if (checkedDestButton.getId() == -1)
        {
            // no radio buttons are checked
        }
        else
        {
            switch (checkedDestButton.getId()){
                case R.id.destPlay1:
                    command += "4";
                    break;
                case R.id.destTV:
                    command += "0";
                    break;
            }
            // one of the radio buttons is checked
        }

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
    }
}
