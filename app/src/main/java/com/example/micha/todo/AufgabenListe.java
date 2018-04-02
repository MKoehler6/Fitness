package com.example.micha.todo;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AufgabenListe extends Fragment {

    public static Context context;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_aufgaben_liste, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerAufgabenListe);

        AufgabenAdapter adapter = new AufgabenAdapter();
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        context = getActivity();

        return view;
    }
}



