package com.example.micha.todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

/**
 * Created by Michael on 09.12.2017.
 */

public class Aufgabe {

    int id;
    String name;
    int turnus;
    int pausen;
    int erledigt;
    String events;
    Date aktDatum;

    public Aufgabe (int id, String name, int pausen, int turnus, int erledigt, String events){
        this.id = id;
        this.name = name;
        this.turnus = turnus;
        this.pausen = pausen;
        this.erledigt = erledigt;
        this.events = events;
        aktDatum = new Date();
    }

    public String gibName(){
        return name;
    }
    public int gibID() { return id; }
    public void setzeErledigt(){
        erledigt = 1;
    }
    public void setzeNichtErledigt(){
        erledigt = 0;
    }
    public int gibTurnus(){
        return turnus;
    }

    public String gibAnzahlInWoche() {
        String[] arrEvents = events.split(";");
        int zaehler = 0;
        Datum datum = new Datum();
        for (int i = 1; i < arrEvents.length; i++) {
            String[] s = arrEvents[i].split(" ");
            int woche = datum.gibWoche();
            if (datum.gibTagInWoche() == 1) { // weil die Woche hier am Sonntag beginnt = Tag 1
                woche = woche - 1;
            }
            if (Integer.parseInt(s[3]) == datum.gibJahr() & Integer.parseInt(s[1]) == datum.gibWoche()) {
                zaehler++;
            }
        }
        Integer meinInteger = new Integer(zaehler);
        return meinInteger.toString();
    }

    public String gibAnzahlInMonat() {
        String[] arrEvents = events.split(";");
        int zaehler = 0;
        Datum datum = new Datum();
        for (int i = 1; i < arrEvents.length; i++) {
            String[] s = arrEvents[i].split(" ");
            if (Integer.parseInt(s[3]) == datum.gibJahr() & Integer.parseInt(s[2]) == datum.gibMonat()) {
                zaehler++;
            }
        }
        return String.valueOf(zaehler);
    }

    public String gibAnzahlInJahr() {
        String[] arrEvents = events.split(";");
        int zaehler = 0;
        Datum datum = new Datum();
        for (int i = 1; i < arrEvents.length; i++) {
            String[] s = arrEvents[i].split(" ");
            if (Integer.parseInt(s[3]) == datum.gibJahr()) {
                zaehler++;
            }
        }
        return String.valueOf(zaehler);
    }

    public String gibStartDatum() {
        String[] arrEvents = events.split(";");
        return arrEvents[0];
    }

    public int tageVergangen() {
        String dtStart = gibStartDatum() + " 00 00 00";
        int tage = 0;
        SimpleDateFormat format = new SimpleDateFormat("dd MM yy HH mm ss");
        try {
            Date date = format.parse(dtStart);
            tage = (int) ((aktDatum.getTime() - date.getTime())/1000/60/60/24);
            return tage;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tage;
    }

    public String tageVergangenString() {
        return ((Integer) tageVergangen()).toString();
    }

    public String gibTurnusString() {
        switch (turnus) {
            case 1:
                return "täglich";
            case 2:
                return "mit Pausen";
        }
        return "unbekannter Turnus";
    }
    public String gibPausenString() {
        switch (pausen) {
            case 0:
                return " ";
            case 1:
                return "1 Tag Pause";
            case 2:
                return "2 Tage Pause";
            case 3:
                return "3 Tage Pause";
        }
        return "unbekannte Pause";
    }
    public String gibErledigtString() {
        switch (erledigt) {
            case 0:
                return "nicht erledigt";
            case 1:
                return "erledigt";
        }
        return "unbekannter Status";
    }
    public String gibIDString(){
        Integer meinInteger = new Integer(id);
        return meinInteger.toString();
    }

}
