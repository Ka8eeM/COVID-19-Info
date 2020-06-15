package com.example.testcovid;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> implements Filterable {
    Context context;
    ArrayList<ItemModel> list;
    ArrayList<ItemModel> tempList;
    LayoutInflater inflater;
    OnItemListener itemListener;

    public DataAdapter(Context context, ArrayList<ItemModel> list, OnItemListener itemListener) {
        this.list = list;
        this.context = context;
        tempList = new ArrayList<>(list);
        inflater = LayoutInflater.from(context);
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = inflater.inflate(R.layout.item_list, parent, false);
        MyViewHolder holder = new MyViewHolder(view, itemListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //Log.e("Country Name", list.get(position).getCountryText());
        holder.txtCountryName.setText(list.get(position).getCountryText());
        holder.txtConfirmed.setText(list.get(position).getTotalCasesText());
        holder.txtRecovered.setText(list.get(position).getTotalRecoveredText());
        holder.txtDeaths.setText(list.get(position).getTotalDeathsText());
        holder.txtLastUpdate.setText("  " + list.get(position).getLastUpdateText());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<ItemModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0)
                filteredList.addAll(tempList);
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ItemModel it : tempList)
                    if (it.getCountryText().toLowerCase().contains(filterPattern))
                        filteredList.add(it);
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtCountryName;
        TextView txtConfirmed;
        TextView txtDeaths;
        TextView txtRecovered;
        TextView txtLastUpdate;
        OnItemListener listener;

        public MyViewHolder(@NonNull View itemView, OnItemListener listener) {
            super(itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);
            txtCountryName = itemView.findViewById(R.id.txt_view_country_name);
            txtDeaths = itemView.findViewById(R.id.txt_view_deaths);
            txtConfirmed = itemView.findViewById(R.id.txt_view_confirmed);
            txtRecovered = itemView.findViewById(R.id.txt_view_recovered);
            txtLastUpdate = itemView.findViewById(R.id.txt_view_lastupdate);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemListener {
        void onItemClick(int pos);
    }
}
