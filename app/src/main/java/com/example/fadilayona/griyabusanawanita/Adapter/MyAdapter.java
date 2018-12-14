package com.example.fadilayona.griyabusanawanita.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fadilayona.griyabusanawanita.LayarDetail;
import com.example.fadilayona.griyabusanawanita.Model.Transaksi;
import com.example.fadilayona.griyabusanawanita.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Transaksi> mTransaksiList;

    public MyAdapter(List<Transaksi> transaksiList) { mTransaksiList = transaksiList; }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mTextViewIdTransaksi.setText("Id Transaksi :  " + mTransaksiList.get(position)
                .getIdTransaksi());
        holder.mTextViewIdBaju.setText("Id Laptop :  " + mTransaksiList.get(position)
                .getIdBaju());
        holder.mTextViewIdPembeli.setText("Id User :  " + mTransaksiList.get(position).getIdPembeli
                ());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mintent = new Intent(v.getContext(), LayarDetail.class);
                mintent.putExtra("id_transaksi",mTransaksiList.get(position).getIdTransaksi());
                mintent.putExtra("id_baju",mTransaksiList.get(position).getIdBaju());
                mintent.putExtra("id_pembeli",mTransaksiList.get(position).getIdPembeli());
                mintent.putExtra("total harga",mTransaksiList.get(position).getTotalharga());
                v.getContext().startActivity(mintent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mTransaksiList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewIdTransaksi, mTextViewIdBaju, mTextViewIdPembeli;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewIdTransaksi = (TextView) itemView.findViewById(R.id.tvIdTransaksi);
            mTextViewIdBaju = (TextView) itemView.findViewById(R.id.tvIdBaju);
            mTextViewIdPembeli = (TextView) itemView.findViewById(R.id.tvIdPembeli);

        }
    }

}
