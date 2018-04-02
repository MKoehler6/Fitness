package com.example.micha.todo;

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

    public Aufgabe (int id, String name, int pausen, int turnus, int erledigt, String events){
        this.id = id;
        this.name = name;
        this.turnus = turnus;
        this.pausen = pausen;
        this.erledigt = erledigt;
        this.events = events;
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
        for (int i = 0; i < arrEvents.length; i++) {
            String[] s = arrEvents[i].split(" ");
            int woche = datum.gibWoche();
            if (datum.gibTagInWoche() == 1) { //weil die Woche hier am Sonntag beginnt = Tag 1
                woche = woche - 1;
            }
            if (Integer.parseInt(s[1]) == datum.gibWoche()) {
                zaehler++;
            }
        }
        Integer meinInteger = new Integer(zaehler);
        return meinInteger.toString();
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
