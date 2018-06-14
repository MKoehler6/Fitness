package com.example.micha.todo;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Heute extends Fragment {

    static String PREF_TAG = "tag";
    static String PREF_MONAT = "monat";
    static String PREF_WOCHE = "woche";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Datum datum = new Datum();
        int tag = datum.gibTag();
        int monat = datum.gibMonat();
        int woche = datum.gibWoche();

        Context context = getActivity();
        ToDoDB toDoDB = new ToDoDB(context);

        SharedPreferences sharedPreferences1 = context.getSharedPreferences(PREF_TAG, Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences2 = context.getSharedPreferences(PREF_MONAT, Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences3 = context.getSharedPreferences(PREF_WOCHE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();
        SharedPreferences.Editor editor3 = sharedPreferences3.edit();
        int savedTag = sharedPreferences1.getInt(PREF_TAG, 0);
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
            savedTag = sharedPreferences1.getInt(PREF_TAG, 0);

            toDoDB.setDailyUndone();
            if (tag > 10 & tag < 21) {
                toDoDB.setMonthlyUndone2();
            }
            if (tag > 20 & tag < 32) {
                toDoDB.setMonthlyUndone3();
            }
        }

        if (savedMonat != monat) {
            editor2.putInt(PREF_MONAT, monat);
            editor2.commit();
            savedMonat = sharedPreferences2.getInt(PREF_MONAT, 0);

            toDoDB.setMonthlyUndone1();
        }
        if (savedWoche != woche) {
            editor3.putInt(PREF_WOCHE, woche);
            editor3.commit();
            savedWoche = sharedPreferences3.getInt(PREF_WOCHE, 0);

            toDoDB.setWeeklyUndone();
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
