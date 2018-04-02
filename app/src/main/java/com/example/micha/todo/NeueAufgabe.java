package com.example.micha.todo;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class NeueAufgabe extends Fragment implements View.OnClickListener {

    private EditText editTextName;
    private RadioButton radioButtonTurnus1;
    private RadioButton radioButtonTurnus2;
    private RadioButton radioButtonPos1;
    private RadioButton radioButtonPos2;
    private RadioButton radioButtonPos3;
    private TextView textViewPos;
    private RadioGroup radioGroupPos;
    private Button btnSpeichern;
    boolean rBT1;
    boolean rBT2;
    boolean rBP1;
    boolean rBP2;
    boolean rBP3;
    int turn;
    int pos = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.activity_neue_aufgabe, container, false);

        btnSpeichern = view.findViewById(R.id.speichern);
        btnSpeichern.setOnClickListener(this);
        editTextName = view.findViewById(R.id.editText);
        radioButtonTurnus1 = view.findViewById(R.id.radioButton);
        radioButtonTurnus1.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rBT1 = true;
                    hideKeyboardFrom(getContext(), view);
                    radioGroupPos.setVisibility(View.INVISIBLE);
                    textViewPos.setVisibility(View.INVISIBLE);
                } else {
                    rBT1 = false;
                }
            }

        });
        radioButtonTurnus2 = view.findViewById(R.id.radioButton9);
        radioButtonTurnus2.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rBT2 = true;
                    hideKeyboardFrom(getContext(), view);
                    radioGroupPos.setVisibility(View.VISIBLE);
                    textViewPos.setVisibility(View.VISIBLE);
                } else {
                    rBT2 = false;
                }
            }

        });

        radioGroupPos = view.findViewById(R.id.rgpos);
        radioGroupPos.setVisibility(View.INVISIBLE);
        textViewPos = view.findViewById(R.id.textView4);
        textViewPos.setVisibility(View.INVISIBLE);
        radioButtonPos1 = view.findViewById(R.id.radioButton12);
        radioButtonPos1.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rBP1 = true;
                } else {
                    rBP1 = false;
                }
            }

        });
        radioButtonPos2 = view.findViewById(R.id.radioButton13);
        radioButtonPos2.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rBP2 = true;
                } else {
                    rBP2 = false;
                }
            }

        });
        radioButtonPos3 = view.findViewById(R.id.radioButton14);
        radioButtonPos3.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rBP3 = true;
                } else {
                    rBP3 = false;
                }
            }

        });

        //MainActivity.tododb.insert("DB Aufgabe täglich", 1, 1, 0);
        //MainActivity.tododb.insert("DB Aufgabe2 täglich", 1, 1, 0);

        return view;

    }


    @Override
    public void onClick(View v) {

        if (rBT1){
            turn = 1;
        }
        if (rBT2) {
            turn = 2;
        }
        if (rBP1){
            pos = 1;
        }
        if (rBP2) {
            pos = 2;
        }
        if (rBP3) {
            pos = 3;
        }
        MainActivity.tododb.insert(editTextName.getText().toString(), pos, turn, 0);

        Toast.makeText(getContext(), R.string.saved, Toast.LENGTH_SHORT).show();

        hideKeyboardFrom(getContext(), v);

        Heute fragmentHeute = new Heute();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentPlatzhalter, fragmentHeute);
        fragmentTransaction.commit();

    }
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
