package com.example.fadilayona.griyabusanawanita.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fadilayona.griyabusanawanita.LayarEditBaju;
import com.example.fadilayona.griyabusanawanita.Model.Baju;
import com.example.fadilayona.griyabusanawanita.R;

import java.text.BreakIterator;
import java.util.List;

public class BajuAdapter extends RecyclerView.Adapter<BajuAdapter.BajuViewHolder>{
    private TextView tvNamabaju;
    List<Baju> listBaju;

    public BajuAdapter(List<Baju> listBaju) {
        this.listBaju = listBaju;
    }

    @Override
    public BajuAdapter.BajuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_baju, parent, false);
        BajuViewHolder mHolder = new BajuViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(BajuAdapter.BajuViewHolder holder, final int position) {

//        holder.mPhotoURL.setImageResource(listBaju.get(position).getPhotoUrl());
        holder.tvIdBaju.setText(listBaju.get(position).getIdBaju());
        holder.tvNamabaju.setText(listBaju.get(position).getNamabaju());
        holder.tvKategori.setText(listBaju.get(position).getKategori());
        holder.tvHarga.setText (String.valueOf(listBaju.get(position).getHarga()));
        if (listBaju.get(position).getPhotoUrl() != null ){
//            Picasso.with(holder.itemView.getContext()).load(ApiClient.BASE_URL+listLaptop.get(position).getPhotoUrl())
//                    .into(holder.mPhotoURL);
            Glide.with(holder.itemView.getContext()).load(listBaju.get
                    (position).getPhotoUrl())
                    .into(holder.mPhotoURL);
        } else {
//          Picasso.with(holder.itemView.getContext()).load(R.drawable.biru).into(holder
// .mPhotoURL);
            Glide.with(holder.itemView.getContext()).load(R.drawable.Blouse).into(holder
                    .mPhotoURL);


        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LayarEditBaju.class);
                intent.putExtra("id_baju", listBaju.get(position).getIdBaju());
                intent.putExtra("nama baju", listBaju.get(position).getNamabaju());
                intent.putExtra("kategori", listBaju.get(position).getKategori());
                intent.putExtra("harga", listBaju.get(position).getHarga());
                intent.putExtra("photo_url", listBaju.get(position).getPhotoUrl());
                view.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listBaju.size();
    }

    public class BajuViewHolder extends RecyclerView.ViewHolder {
        ImageView mPhotoURL;
        TextView tvIdBaju, tvMerk, tvTipe, tvRam, tvProcessor, tvWarna, tvHarga;
        public BreakIterator tvKategori;
        public TextView tvNamabaju;

        public BajuViewHolder(View itemView) {
            super(itemView);
            mPhotoURL = (ImageView) itemView.findViewById(R.id.imgBaju);
            tvIdBaju = (TextView) itemView.findViewById(R.id.tvIdBaju);
            tvNamabaju = (TextView) itemView.findViewById(R.id.tvNamabaju);
            TextView tvKategori = (TextView) itemView.findViewById(R.id.tvKategoriContent);
            tvHarga = (TextView) itemView.findViewById(R.id.tvHargaContent);
        }
    }

}
