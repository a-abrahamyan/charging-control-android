package com.lifesoft.chc.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lifesoft.chc.chargingcontrol.R;
import com.lifesoft.chc.model.CCTransactions;

public class GeneralRecyclerViewAdapter extends RecyclerView.Adapter<GeneralRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private CCTransactions ccTransactions;

    public GeneralRecyclerViewAdapter(Context context, CCTransactions ccTransactions) {
        this.context = context;
        this.ccTransactions = ccTransactions;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_recycler_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      holder.date.setText(ccTransactions.getTransactions().get(position).getDate());
      holder.type.setText(ccTransactions.getTransactions().get(position).getType());
      holder.after.setText(ccTransactions.getTransactions().get(position).getAfter());
      holder.before.setText(ccTransactions.getTransactions().get(position).getBefore());
      holder.money.setText(ccTransactions.getTransactions().get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return ccTransactions.getTransactions().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView date;
        TextView money;
        TextView type;
        TextView before;
        TextView after;
        public MyViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.dateID);
            money = itemView.findViewById(R.id.moneyID);
            type = itemView.findViewById(R.id.typeID);
            before = itemView.findViewById(R.id.beforeID);
            after = itemView.findViewById(R.id.afterID);
        }
    }
}
