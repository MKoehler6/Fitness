package com.example.micha.todo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    static String PREF_NAME = "pref_name";
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Heute fragmentHeute = new Heute();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentPlatzhalter, fragmentHeute);
        fragmentTransaction.commit();
    }

    public void heuteActivity (View view) {
        Heute fragmentHeute = new Heute();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentPlatzhalter, fragmentHeute);
        fragmentTransaction.commit();
    }
    public void listeActivity (View view) {
        AufgabenListe fragmentAufgListe = new AufgabenListe();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentPlatzhalter, fragmentAufgListe);
        fragmentTransaction.commit();
    }
    public void neueAufgabeActivity (View view) {
        NeueAufgabe fragmentNeueAufgabe = new NeueAufgabe();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentPlatzhalter, fragmentNeueAufgabe);
        fragmentTransaction.commit();
    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

}
