package com.uah_rates.grd.uahrates.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.uah_rates.grd.uahrates.R;
import com.uah_rates.grd.uahrates.ui.adapter.listener.RecyclerViewClickListener;
import com.uah_rates.grd.uahrates.model.pojo.Rate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.Holder>{

    private final String LOG_TAG = DataAdapter.class.getName();

    private final LayoutInflater inflator;
    private List<Rate> tempList;
    private List<Rate> ratesListSorted;
    public Map<Integer,Integer> tickerMap;
   // private RecyclerViewClickListener mListener;

    private  int template ;


    public DataAdapter(LayoutInflater inflator,int template) {
        this.inflator = inflator;
        this.template = template;

        this.tempList = new ArrayList<>();
        this.ratesListSorted = new ArrayList<>();

        tickerMap = null;
        tickerMap = new HashMap<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(template, null);
        Holder viewHolder = new Holder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.onBind(holder,position);
    }

    @Override
    public int getItemCount() {

        return ratesListSorted.size();
    }

    public void addRates(List<Rate> rates) {
        tempList.clear();
        tempList.addAll(rates);
        ratesListSorted = tempList;
        notifyDataSetChanged();
    }

    public void clearRates() {
        tempList.clear();
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder    {

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

            final Rate rates = ratesListSorted.get(position);
            tickerMap.put(position,rates.getR030());

            holder.mTextViewTitle.setText(rates.getTxt());
            holder.mTextViewСс.setText(rates.getCc());
            holder.mTextViewRate.setText(Float.toString(rates.getRate()));
            holder.mTextViewExchangedate.setText(rates.getExchangedate());


        }
    }

}