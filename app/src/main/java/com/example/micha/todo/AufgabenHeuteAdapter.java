package com.example.micha.todo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by micha on 24.01.2018.
 */

public class AufgabenHeuteAdapter extends RecyclerView.Adapter {

    ToDoDB toDoDB;
    Datum datum = new Datum();

    AufgabenHeuteAdapter(ToDoDB toDoDB) {
        this.toDoDB = toDoDB;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_text_zweizeilen, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return toDoDB.readAufgabeUnerledigt().size();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mItemText1;
        private TextView mItemText2;
        private TextView mItemText5;
        private TextView mItemText6;
        private TextView mItemText7;

        public ListViewHolder(View itemView) {

            super(itemView);
            mItemText1 = (TextView) itemView.findViewById(R.id.textzeile1);
            mItemText2 = (TextView) itemView.findViewById(R.id.textzeile2);
            mItemText5 = (TextView) itemView.findViewById(R.id.textView11);
            mItemText6 = (TextView) itemView.findViewById(R.id.textView12);
            mItemText7 = (TextView) itemView.findViewById(R.id.textView13);

            itemView.setOnClickListener(this);

        }

        public void bindView(int position) {
            mItemText1.setText(toDoDB.readAufgabeUnerledigt().get(position).gibName());
            if (toDoDB.readAufgabeUnerledigt().get(position).gibTurnus() == 1){
                mItemText2.setText(toDoDB.readAufgabeUnerledigt().get(position).gibTurnusString());
            } else {
                mItemText2.setText(toDoDB.readAufgabeUnerledigt().get(position).gibPausenString());
            }
            mItemText5.setText(toDoDB.readAufgabeUnerledigt().get(position).gibAnzahlInWoche());
            mItemText6.setText(toDoDB.readAufgabeUnerledigt().get(position).gibAnzahlInMonat());
            mItemText7.setText(toDoDB.readAufgabeUnerledigt().get(position).gibAnzahlInJahr());
        }

        public void onClick(View view) {
            String id = toDoDB.readAufgabeUnerledigt().get(getAdapterPosition()).gibIDString();
            String name = toDoDB.readAufgabeUnerledigt().get(getAdapterPosition()).name;
            String alt = toDoDB.readAufgabeUnerledigt().get(getAdapterPosition()).events;
            String dateNeu = alt + ";" + datum.gibTagInWocheString() + " " + datum.gibWocheString() + " " + datum.gibMonatString() + " " + datum.gibJahrString();
            toDoDB.setDate(id, dateNeu);
            toDoDB.setDone(id, name);
            notifyItemRemoved(getAdapterPosition());
            if (toDoDB.readAufgabeUnerledigt().size() == 0) {
                MainActivity.bild.setVisibility(View.VISIBLE);
                Log.d("MEINLOGneueActivity: ", " wird aufgerufen");
            } else {
                MainActivity.bild.setVisibility(View.INVISIBLE);
            }
        }
    }
}
