package com.uah_rates.grd.uahrates.presentation.ui.adapter;

import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.uah_rates.grd.uahrates.R;
import com.uah_rates.grd.uahrates.domain.model.pojo.Bond;
import com.uah_rates.grd.uahrates.domain.model.pojo.Rate;

import java.util.*;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.Holder> {

    private final String LOG_TAG = DataAdapter.class.getName();

    private List<? extends Rate> list;


    public DataAdapter(List<?> list) {
        Log.d(LOG_TAG, "-- LIST SIZE " + list.size());
        this.list = (List<? extends Rate>) list;
        // this.list2= list;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_row, null);
        Holder viewHolder = new Holder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.onBind(holder, position);
    }

    @Override
    public int getItemCount() {

        return list.size();
    }


    public class Holder extends RecyclerView.ViewHolder {

        TextView mTextViewTitle;
        TextView mTextViewСс;
        TextView mTextViewRate;
        TextView mTextViewExchangedate;

        public Holder(View v) {
            super(v);
            mTextViewTitle = (TextView) v.findViewById(R.id.title_name);
            mTextViewСс = (TextView) v.findViewById(R.id.text_Cc);
            mTextViewRate = (TextView) v.findViewById(R.id.text_rate);
            mTextViewExchangedate = (TextView) v.findViewById(R.id.text_exchangedate);

        }

        public void onBind(Holder holder, int position) {

            holder.mTextViewTitle.setText(list.get(position).getTxt());
            holder.mTextViewСс.setText(list.get(position).getCc());
            holder.mTextViewRate.setText(Float.toString(list.get(position).getRate()));
            holder.mTextViewExchangedate.setText(list.get(position).getExchangedate());


        }
    }

}