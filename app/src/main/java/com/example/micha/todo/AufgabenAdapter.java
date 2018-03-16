package com.example.micha.todo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Michael on 25.12.2017.
 */

public class AufgabenAdapter extends RecyclerView.Adapter {

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
        return MainActivity.tododb.readAufgabeAlle().size();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView mItemText1;
        private TextView mItemText2;

        public ListViewHolder(View itemView) {

            super(itemView);
            mItemText1 = (TextView) itemView.findViewById(R.id.textzeile1);
            mItemText2 = (TextView) itemView.findViewById(R.id.textzeile2);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        public void bindView(int position) {
            mItemText1.setText(MainActivity.tododb.readAufgabeAlle().get(position).gibName());
            mItemText2.setText(MainActivity.tododb.readAufgabeAlle().get(position).gibErledigtString() + "   -   " + MainActivity.tododb.readAufgabeAlle().get(position).gibTurnusString() + "      " + MainActivity.tododb.readAufgabeAlle().get(position).gibPositionString());
        }


        public void onClick(View view) {
            String id = MainActivity.tododb.readAufgabeAlle().get(getPosition()).gibIDString();
            MainActivity.tododb.setUndone(id);
            notifyItemChanged(getPosition());
        }
        public boolean onLongClick(View view) {
            String id = MainActivity.tododb.readAufgabeAlle().get(getPosition()).gibIDString();
            MainActivity.tododb.delete(id);
            Toast.makeText(AufgabenListe.context, R.string.deleted, Toast.LENGTH_SHORT).show();
            notifyItemRemoved(getPosition());
            return true;
        }
    }
}


