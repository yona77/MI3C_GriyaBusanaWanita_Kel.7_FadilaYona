package com.example.fadilayona.griyabusanawanita;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.fadilayona.griyabusanawanita.Adapter.BajuAdapter;
import com.example.fadilayona.griyabusanawanita.Model.GetBaju;
import com.example.fadilayona.griyabusanawanita.Model.Baju;
import com.example.fadilayona.griyabusanawanita.Rest.ApiClient;
import com.example.fadilayona.griyabusanawanita.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarListBaju extends OpsiMenu {
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context mContext;
    ApiInterface mApiInterface;
    Button btAddData, btGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_list_baju);

        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        btGet = (Button) findViewById(R.id.btGet);
        btAddData = (Button) findViewById(R.id.btAddData);

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<GetBaju> mLaptopCall = mApiInterface.getBaju();
                mLaptopCall.enqueue(new Callback<GetBaju>() {
                    @Override
                    public void onResponse(Call<GetBaju> call, Response<GetBaju> response) {
                        Log.d("Get Laptop",response.body().getStatus());
                        List<Baju> listBaju = response.body().getResult();
                        mAdapter = new BajuAdapter(listBaju);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetBaju> call, Throwable t) {
                        Log.d("Get Laptop",t.getMessage());
                    }
                });
            }
        });

        btAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LayarInsertBaju.class);
                startActivity(intent);
            }
        });

    }
}
