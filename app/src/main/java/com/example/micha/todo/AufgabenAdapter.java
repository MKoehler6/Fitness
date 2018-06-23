package com.example.micha.todo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Michael on 25.12.2017.
 */

public class AufgabenAdapter extends RecyclerView.Adapter {

    ToDoDB toDoDB;
    Context context;

    AufgabenAdapter(ToDoDB toDoDB, Context context) {
        this.toDoDB = toDoDB;
        this.context = context;
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
        return toDoDB.readAufgabeAlle().size();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

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
            itemView.setOnLongClickListener(this);

        }

        public void bindView(int position) {
            mItemText1.setText(toDoDB.readAufgabeAlle().get(position).gibName());
            if (toDoDB.readAufgabeAlle().get(position).gibTurnusString() == "täglich") {
                mItemText2.setText(toDoDB.readAufgabeAlle().get(position).gibErledigtString() + "   -   " + toDoDB.readAufgabeAlle().get(position).gibTurnusString() + "      " + toDoDB.readAufgabeAlle().get(position).gibPausenString());

            } else {
                mItemText2.setText(toDoDB.readAufgabeAlle().get(position).gibErledigtString() + "   -   " + toDoDB.readAufgabeAlle().get(position).gibPausenString());
            }
            mItemText5.setText(toDoDB.readAufgabeAlle().get(position).gibAnzahlInWoche());
            mItemText6.setText(toDoDB.readAufgabeAlle().get(position).gibAnzahlInMonat());
            mItemText7.setText(toDoDB.readAufgabeAlle().get(position).gibAnzahlInJahr());
            List<String> test = toDoDB.readDate();
            Log.d("MEINLOGlisteLaenge", "" + test.size());
            for (int i = 0; i < test.size(); i++) {
                Log.d("MEINLOGArrayList", i + " " + test.get(i));
            }
        }


        public void onClick(View view) {
            String id = toDoDB.readAufgabeAlle().get(getAdapterPosition()).gibIDString();
            toDoDB.setUndone(id);
            notifyItemChanged(getAdapterPosition());
        }
        public boolean onLongClick(View view) {
            String id = toDoDB.readAufgabeAlle().get(getAdapterPosition()).gibIDString();
            toDoDB.delete(id);
            Toast.makeText(context, R.string.deleted, Toast.LENGTH_SHORT).show();
            notifyItemRemoved(getAdapterPosition());
            return true;
        }
    }
}


