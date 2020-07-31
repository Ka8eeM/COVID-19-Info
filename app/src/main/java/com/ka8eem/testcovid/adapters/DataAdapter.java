package com.ka8eem.testcovid.adapters;

import android.content.Context;
import android.content.Intent;
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

import com.ka8eem.testcovid.models.ItemModel;
import com.ka8eem.testcovid.R;
import com.ka8eem.testcovid.ui.DetailsActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> implements Filterable {
    Context context;
    ArrayList<ItemModel> list;
    ArrayList<ItemModel> fullList;

    public DataAdapter() {
        list = new ArrayList<>();
    }

    public DataAdapter(ArrayList<ItemModel> list) {
        this.list = list;
        fullList = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    public void setList(ArrayList<ItemModel> itemModels) {
        this.list = new ArrayList<>();
        this.list.addAll(itemModels);
        fullList = new ArrayList<>(list);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.txtCountryName.setText(list.get(position).getCountryText());
        holder.txtConfirmed.setText(list.get(position).getTotalCasesText());
        holder.txtRecovered.setText(list.get(position).getTotalRecoveredText());
        holder.txtDeaths.setText(list.get(position).getTotalDeathsText());
        holder.txtLastUpdate.setText(list.get(position).getLastUpdateText());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemModel model = list.get(position);
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("MyClass", (Serializable) model);
                context.startActivity(intent);
            }
        });
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
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(fullList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ItemModel it : fullList) {
                    if (it.getCountryText() == null)
                        continue;
                    if (it.getCountryText().toLowerCase().contains(filterPattern))
                        filteredList.add(it);
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((ArrayList<ItemModel>) results.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtCountryName;
        TextView txtConfirmed;
        TextView txtDeaths;
        TextView txtRecovered;
        TextView txtLastUpdate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCountryName = itemView.findViewById(R.id.txt_view_country_name);
            txtDeaths = itemView.findViewById(R.id.txt_view_deaths);
            txtConfirmed = itemView.findViewById(R.id.txt_view_confirmed);
            txtRecovered = itemView.findViewById(R.id.txt_view_recovered);
            txtLastUpdate = itemView.findViewById(R.id.txt_view_lastupdate);
        }

    }
}
