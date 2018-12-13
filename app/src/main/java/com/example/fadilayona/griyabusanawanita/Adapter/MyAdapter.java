package com.example.fadilayona.griyabusanawanita.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.fadilayona.griyabusanawanita.Model.Transaksi;
import com.example.fadilayona.griyabusanawanita.R;
import com.example.fadilayona.griyabusanawanita.LayarDetail;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Transaksi> mTransaksiList;
    public MyAdapter(List<Transaksi> transaksiList) {
        mTransaksiList = transaksiList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mTextViewIdTransaksi.setText("Id Transaksi : " + mTransaksiList.get(position).getId_transaksi());
        holder.mTextViewIdPembeli.setText("Id Pembeli : " + mTransaksiList.get(position).getId_pembeli());
        holder.mTextViewIdBaju.setText("Id Baju : " + mTransaksiList.get(position).getId_baju());
        holder.mTextViewTotalHarga.setText("Total Harga : " + String.valueOf(mTransaksiList.get(position).getTotal_harga()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent mIntent =  new Intent(view.getContext(), LayarDetail.class);
                mIntent.putExtra("id_pembelian", mTransaksiList.get(position).getId_transaksi());
                mIntent.putExtra("id_pembeli", mTransaksiList.get(position).getId_pembeli());
                mIntent.putExtra("id_baju", mTransaksiList.get(position).getId_transaksi());
                mIntent.putExtra("total_harga",String.valueOf(mTransaksiList.get(position).getTotal_harga()));
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTransaksiList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewIdTransaksi, mTextViewIdPembeli, mTextViewIdBaju, mTextViewTotalHarga;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewIdTransaksi = (TextView) itemView.findViewById(R.id.tvIdPembelian);
            mTextViewIdPembeli   = (TextView) itemView.findViewById(R.id.tvIdPembeli);
            mTextViewIdBaju     = (TextView) itemView.findViewById(R.id.tvIdBaju);
            mTextViewTotalHarga  = (TextView) itemView.findViewById(R.id.tvTotalHarga);
        }
    }
}