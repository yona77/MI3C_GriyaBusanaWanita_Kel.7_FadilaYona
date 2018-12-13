package com.example.fadilayona.griyabusanawanita.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fadilayona.griyabusanawanita.LayarEditPembeli;
import com.example.fadilayona.griyabusanawanita.Model.Pembeli;
import com.example.fadilayona.griyabusanawanita.R;

import java.text.BreakIterator;
import java.util.List;

public class PembeliAdapter extends RecyclerView.Adapter<PembeliAdapter.PembeliViewHolder> {
    List<Pembeli> listPembeli;

    public PembeliAdapter(List<Pembeli> listPembeli) {
        this.listPembeli= listPembeli;
    }

    @Override
    public PembeliAdapter.PembeliViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pembeli, parent, false);
        PembeliAdapter.UserViewHolder mHolder = new PembeliAdapter().UserViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(PembeliAdapter.UserViewHolder holder, final int position) {

        holder.tvIdPembeli.setText(listPembeli.get(position).getIdPembeli());
        holder.tvUsername.setText(listPembeli.get(position).getUsername());
        holder.tvPassword.setText(listPembeli.get(position).getPassword());
        holder.tvNama.setText(listPembeli.get(position).getNama());
        holder.tvAlamat.setText(listPembeli.get(position).getAlamat());
        holder.tvTelp.setText(listPembeli.get(position).getTelp());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LayarEditPembeli.class);
                intent.putExtra("idPembeli", listPembeli.get(position).getIdPembeli());
                intent.putExtra("username", listPembeli.get(position).getUsername());
                intent.putExtra("password", listPembeli.get(position).getPassword());
                intent.putExtra("nama", listPembeli.get(position).getNama());
                intent.putExtra("alamat", listPembeli.get(position).getAlamat());
                intent.putExtra("telp", listPembeli.get(position).getTelp());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPembeli.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdPembeli, tvUsername, tvPassword, tvNama, tvAlamat, tvTelp;

        public UserViewHolder(View itemView) {
            super(itemView);
            tvIdPembeli = (TextView) itemView.findViewById(R.id.tvIdPembeli);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUsername);
            tvPassword = (TextView) itemView.findViewById(R.id.tvPassword);
            tvNama = (TextView) itemView.findViewById(R.id.tvNama);
            tvAlamat = (TextView) itemView.findViewById(R.id.tvAlamat);
            tvTelp = (TextView) itemView.findViewById(R.id.tvTelp);
        }
    }
}
