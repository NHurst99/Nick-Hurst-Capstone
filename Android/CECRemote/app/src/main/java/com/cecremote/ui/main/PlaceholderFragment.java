package com.cecremote.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.cecremote.R;
import com.google.android.material.snackbar.Snackbar;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private static String stringOptions;
    public static PlaceholderFragment newInstance(int index, String options){
        stringOptions = options;
        return newInstance(index);
    }

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }
    static View root;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        if(stringOptions.contains("poweronoff")){
            ((ConstraintLayout)root.findViewById(R.id.constraintLayout)).addView(generateButton("On",'a',50,100,16,8));
            ((ConstraintLayout)root.findViewById(R.id.constraintLayout)).addView(generateButton("Off",'b',50,100,132, 16));
        }
        if(stringOptions.contains("navigation")){
            ((ConstraintLayout)root.findViewById(R.id.constraintLayout)).addView(generateButton("⬅",'c',50,50,132, 16));
            ((ConstraintLayout)root.findViewById(R.id.constraintLayout)).addView(generateButton("➡",'d',50,50,16,8));
            ((ConstraintLayout)root.findViewById(R.id.constraintLayout)).addView(generateButton("⬆",'e',50,50,16,8));
            ((ConstraintLayout)root.findViewById(R.id.constraintLayout)).addView(generateButton("⬇",'f',50,50,132, 16));
            ((ConstraintLayout)root.findViewById(R.id.constraintLayout)).addView(generateButton(" ⏺",'g',50,50,132, 16));

        }
        if(stringOptions.contains("volume")){
            ((ConstraintLayout)root.findViewById(R.id.constraintLayout)).addView(generateButton("Vol +",'h',50,100,16,8));
            ((ConstraintLayout)root.findViewById(R.id.constraintLayout)).addView(generateButton("Vol -",'i',50,100,132, 16));
        }
        if(stringOptions.contains("channel")){
            ((ConstraintLayout)root.findViewById(R.id.constraintLayout)).addView(generateButton("Cha +",'j',50,100,16,8));
            ((ConstraintLayout)root.findViewById(R.id.constraintLayout)).addView(generateButton("Cha -",'k',50,100,132, 16));
        }

        return root;
    }

    private int getDP(int size){
        return (int) (size * this.getContext().getResources().getDisplayMetrics().density);
    }

    private Button generateButton(String text, char command, int height, int width, int left, int top){
        CoordinatorLayout.LayoutParams lp = new CoordinatorLayout.LayoutParams(getDP(left),getDP(top));
//        lp.setMargins(getDP(left),getDP(top),getDP(left),getDP(top));
        lp.height = getDP(height);
        lp.width = getDP(width);
        lp.leftMargin =getDP(left);
        lp.topMargin = getDP(top);
        Button button = new Button(this.getContext());
        button.setText(text);
        button.setLayoutParams(lp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("CECCommand",""+ command);

            }
            private char command;
            private View.OnClickListener init(char var){
                command = var;
                return this;
            }
        }.init(command));
        return button;

    }
}