package com.example.micha.todo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    static String PREF_NAME = "pref_name";
    Context context = this;
    static ImageView bild;
    ToDoDB toDoDB = new ToDoDB(context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bild = (ImageView) findViewById(R.id.imageView2);
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
        if (toDoDB.readAufgabeUnerledigt().size() == 0) {
            bild.setVisibility(View.VISIBLE);
            Log.d("MEINLOGneueActivity: ", " wird aufgerufen");
        } else {
            bild.setVisibility(View.INVISIBLE);
        }
    }
    public void listeActivity (View view) {
        AufgabenListe fragmentAufgListe = new AufgabenListe();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentPlatzhalter, fragmentAufgListe);
        fragmentTransaction.commit();
        bild.setVisibility(View.INVISIBLE);
    }
    public void neueAufgabeActivity (View view) {
        NeueAufgabe fragmentNeueAufgabe = new NeueAufgabe();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentPlatzhalter, fragmentNeueAufgabe);
        fragmentTransaction.commit();
        bild.setVisibility(View.INVISIBLE);
    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

}
