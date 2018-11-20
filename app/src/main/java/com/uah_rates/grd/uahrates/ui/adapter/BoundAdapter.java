package com.uah_rates.grd.uahrates.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.uah_rates.grd.uahrates.R;
import com.uah_rates.grd.uahrates.model.pojo.Bond;


import java.util.List;


public class BoundAdapter extends RecyclerView.Adapter<BoundAdapter.CustomViewHolder> {

    private List<Bond> dataList ;
    private Context context;

    public BoundAdapter(Context context, List<Bond> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        holder.mTextViewTitle.setText(dataList.get(position).getStockcode());
        holder.mTextViewСс.setText(dataList.get(position).getValcode());
        holder.mTextViewRate.setText((Float.toString(dataList.get(position).getAvglevel())) );
        holder.mTextViewExchangedate.setText(dataList.get(position).getAuctiondate());
        holder.coverImage.setImageResource(R.drawable.nbu_image01);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public void setData(List<Bond> data) {
        //  this.dataRx.addAll(data);
        this.dataList.addAll(data);
        notifyDataSetChanged();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView mTextViewTitle;
        TextView mTextViewСс;
        TextView mTextViewRate;
        TextView mTextViewExchangedate;

        private ImageView coverImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            mTextViewTitle = (TextView)  mView.findViewById(R.id.title_name);
            mTextViewСс = (TextView)  mView.findViewById(R.id.text_Cc);
            mTextViewRate = (TextView)  mView.findViewById(R.id.text_rate);
            mTextViewExchangedate = (TextView)  mView.findViewById(R.id.text_exchangedate);

            coverImage = mView.findViewById(R.id.coverImage);
        }
    }

}

