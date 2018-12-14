package com.example.fadilayona.griyabusanawanita;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.fadilayona.griyabusanawanita.Adapter.MyAdapter;
import com.example.fadilayona.griyabusanawanita.Model.GetTransaksi;
import com.example.fadilayona.griyabusanawanita.Model.Transaksi;
import com.example.fadilayona.griyabusanawanita.Rest.ApiClient;
import com.example.fadilayona.griyabusanawanita.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MainActivity extends OpsiMenu {
    Button btGet;
    ApiInterface mApiInterface;

    private RecyclerView mRecyclerView;
    private  RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mlayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btGet = (Button) findViewById(R.id.btGet);

        mRecyclerView =  (RecyclerView) findViewById(R.id.recyclerView);
        mlayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mlayoutManager);

        mApiInterface  = ApiClient.getClient().create(ApiInterface.class);

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<GetTransaksi> transaksiCall = mApiInterface.getTransaksi();
                transaksiCall.enqueue(new Callback<GetTransaksi>() {
                    @Override
                    public void onResponse(Call<GetTransaksi> call, Response<GetTransaksi> response) {
                        List<Transaksi> transaksiList = response.body().getListDataTransaksi();
                        Log.d("Retrofit Get", "Jumlah data transaksi: " + String.valueOf(transaksiList.size()));

                        mAdapter = new MyAdapter(transaksiList);
                        mRecyclerView.setAdapter(mAdapter);
                    }



                    @Override
                    public void onFailure(Call<GetTransaksi> call, Throwable t) {
                        // Log error
                        Log.e("Retrofit Get", t.toString());
                    }
                });
            }
        });
    }
}
