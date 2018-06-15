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

import java.time.LocalDate;
import java.util.GregorianCalendar;

public class NeueAufgabe extends Fragment {

    private EditText editTextName;
    private RadioButton radioButtonTurnus1;
    private RadioButton radioButtonTurnus2;
    private RadioButton radioButtonPause1;
    private RadioButton radioButtonPause2;
    private RadioButton radioButtonPause3;
    private TextView textViewPos;
    private RadioGroup radioGroupPos;
    private Button btnSpeichern;
    boolean rBT1;
    boolean rBT2;
    boolean rBP1;
    boolean rBP2;
    boolean rBP3;
    int turn;
    int pause = 0;

    Datum datum = new Datum();
    int tag = datum.gibTag();
    int monat = datum.gibMonat();
    int jahr = datum.gibJahr();
    String aktDatum = "" + tag + " " + monat + " " + jahr;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Context context = getActivity();
        final ToDoDB toDoDB = new ToDoDB(context);

        final View view = inflater.inflate(R.layout.activity_neue_aufgabe, container, false);

        btnSpeichern = view.findViewById(R.id.speichern);
        btnSpeichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rBT1){
                    turn = 1;
                }
                if (rBT2) {
                    turn = 2;
                }
                if (rBP1){
                    pause = 1;
                }
                if (rBP2) {
                    pause = 2;
                }
                if (rBP3) {
                    pause = 3;
                }

                toDoDB.insert(editTextName.getText().toString(), pause, turn, 0, aktDatum);

                Toast.makeText(getContext(), R.string.saved, Toast.LENGTH_SHORT).show();

                hideKeyboardFrom(getContext(), v);

                Heute fragmentHeute = new Heute();
                android.app.FragmentManager fragmentManager = getFragmentManager();
                android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentPlatzhalter, fragmentHeute);
                fragmentTransaction.commit();
            }
        });
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
        radioButtonPause1 = view.findViewById(R.id.radioButton12);
        radioButtonPause1.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rBP1 = true;
                } else {
                    rBP1 = false;
                }
            }

        });
        radioButtonPause2 = view.findViewById(R.id.radioButton13);
        radioButtonPause2.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rBP2 = true;
                } else {
                    rBP2 = false;
                }
            }

        });
        radioButtonPause3 = view.findViewById(R.id.radioButton14);
        radioButtonPause3.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rBP3 = true;
                } else {
                    rBP3 = false;
                }
            }

        });

        return view;

    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



}
