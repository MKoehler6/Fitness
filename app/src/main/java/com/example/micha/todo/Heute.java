package com.example.micha.todo;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Heute extends Fragment {

    static String PREF_TAG = "tag";
    static String PREF_MONAT = "monat";
    static String PREF_WOCHE = "woche";
     List<Aufgabe> aufgaben;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Datum datum = new Datum();
        int tag = datum.gibTag();
        int monat = datum.gibMonat();
        int woche = datum.gibWoche();

        Context context = getActivity();
        ToDoDB toDoDB = new ToDoDB(context);

        aufgaben = toDoDB.readAufgabeAlle();

        SharedPreferences sharedPreferences1 = context.getSharedPreferences(PREF_TAG, Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences2 = context.getSharedPreferences(PREF_MONAT, Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences3 = context.getSharedPreferences(PREF_WOCHE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();
        SharedPreferences.Editor editor3 = sharedPreferences3.edit();
        //int savedTag = sharedPreferences1.getInt(PREF_TAG, 0);
        int savedTag = 22;
        int savedMonat = sharedPreferences2.getInt(PREF_MONAT, 0);
        int savedWoche = sharedPreferences3.getInt(PREF_WOCHE, 0);
        if (savedTag == 0 | savedMonat == 0 | savedWoche == 0) {
            editor1.putInt(PREF_TAG, tag);
            editor2.putInt(PREF_MONAT, monat);
            editor3.putInt(PREF_WOCHE, woche);
            editor1.apply();
            editor2.apply();
            editor3.apply();
            savedTag = sharedPreferences1.getInt(PREF_TAG, 0);
            savedMonat = sharedPreferences2.getInt(PREF_MONAT, 0);
            savedWoche = sharedPreferences3.getInt(PREF_WOCHE, 0);
        }
        if (savedTag != tag | savedMonat != monat) {
            editor1.putInt(PREF_TAG, tag);
            editor1.commit();
            toDoDB.setDailyUndone();
            for (int i=0; i < aufgaben.size(); i++) {
                int tage = aufgaben.get(i).tageVergangen();
                Log.d("MEINLOG", " " + tage);
                if (tage % 4 == 0 & aufgaben.get(i).gibPausenString() == "3") {
                    toDoDB.setUndone(aufgaben.get(i).gibIDString());
                }
                if (tage % 3 == 0 & aufgaben.get(i).gibPausenString() == "2") {
                    toDoDB.setUndone(aufgaben.get(i).gibIDString());
                }
                if (tage % 2 == 0 & aufgaben.get(i).gibPausenString() == "1") {
                    toDoDB.setUndone(aufgaben.get(i).gibIDString());
                }
            }
        }

        View view = inflater.inflate(R.layout.activity_heute, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerAufgabenListeHeute);
        AufgabenHeuteAdapter adapter = new AufgabenHeuteAdapter(toDoDB);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }
}
