package com.example.micha.todo;

/**
 * Created by Michael on 09.12.2017.
 */

public class Aufgabe {

    int id;
    String name;
    int turnus;
    int pos;
    int erledigt;

    public Aufgabe (int id, String name, int position, int turnus, int erledigt){
        this.id = id;
        this.name = name;
        this.turnus = turnus;
        this.pos = position;
        this.erledigt = erledigt;
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
    public String gibTurnusString() {
        switch (turnus) {
            case 1:
                return "täglich";
            case 2:
                return "alle 2 Tage";
            case 3:
                return "2 mal pro Woche";
        }
        return "unbekannter Turnus";
    }
    public String gibPositionString() {
        switch (pos) {
            case 0:
                return " ";
            case 1:
                return "Anfang des Monats";
            case 2:
                return "Mitte des Monats";
            case 3:
                return "Ende des Monats";
        }
        return "unbekannte Position";
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
