package com.example.micha.todo;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by micha on 31.01.2018.
 */

public class Datum {

    private int woche, monat, tag, tagInWoche;

    Datum() {
        GregorianCalendar aktDatum = new GregorianCalendar();
        woche = aktDatum.get(Calendar.WEEK_OF_YEAR);
        monat = aktDatum.get(Calendar.MONTH);
        tag = aktDatum.get(Calendar.DAY_OF_MONTH);
        tagInWoche = aktDatum.get(Calendar.DAY_OF_WEEK);
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

    int gibTagInWoche() { return tagInWoche; }
    String gibTagString() {
        return ((Integer)tag).toString();
    }
    String gibMonatString() {
        return ((Integer)monat).toString();
    }
}