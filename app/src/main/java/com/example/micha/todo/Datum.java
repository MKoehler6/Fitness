package com.example.micha.todo;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by micha on 31.01.2018.
 */

public class Datum {

    private int woche, monat, tag, tagInWoche, jahr, tagInJahr;
    GregorianCalendar aktDatum;

    Datum() {
        aktDatum = new GregorianCalendar();
        woche = aktDatum.get(Calendar.WEEK_OF_YEAR);
        monat = aktDatum.get(Calendar.MONTH);
        tag = aktDatum.get(Calendar.DAY_OF_MONTH);
        tagInWoche = aktDatum.get(Calendar.DAY_OF_WEEK);
        jahr = aktDatum.get(Calendar.YEAR);
        tagInJahr = aktDatum.get(Calendar.DAY_OF_YEAR);
    }

    Datum(int jahr, int monat, int tag, int tagInWoche) {
        this.woche = woche;
        this.monat = monat;
        this.tag = tag;
        this.tagInWoche = tagInWoche;
    }

    int gibWoche() {
        return woche;
    }

    int gibMonat() {
        return monat + 1;
    }

    int gibTag() {
        return tag;
    }

    int gibTagInWoche() {
        if (tagInWoche == 1) { tagInWoche = 8; }
        return tagInWoche - 1; }

    int gibJahr() { return jahr % 100; }

    int gibTagInJahr() { return tagInJahr; }

    String gibWocheString() {
        return ((Integer)woche).toString();
    }
    String gibJahrString() {
        return ((Integer)gibJahr()).toString();
    }
    String gibTagString() {
        return ((Integer)tag).toString();
    }
    String gibMonatString() {
        return ((Integer)gibMonat()).toString();
    }
    String gibTagInWocheString() { return ((Integer) gibTagInWoche()).toString();
    }
    Date gibAktuellesDatum() {
        return aktDatum.getTime();
    }
}