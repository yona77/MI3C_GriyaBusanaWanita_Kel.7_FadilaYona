package com.example.fadilayona.griyabusanawanita;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fadilayona.griyabusanawanita.Model.PostPutDelTransaksi;
import com.example.fadilayona.griyabusanawanita.Rest.ApiClient;
import com.example.fadilayona.griyabusanawanita.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarDetail extends AppCompatActivity {
    EditText edtIdTransaksi, edtIdBaju, edtIdPembeli;
    Button btInsert, btUpdate, btDelete, btBack;
    TextView tvMessage;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_detail);

        edtIdTransaksi = (EditText) findViewById(R.id.edtIdTransaksi);
        edtIdBaju = (EditText) findViewById(R.id.edtIdBaju);
        edtIdPembeli = (EditText) findViewById(R.id.edtIdPembeli);
        tvMessage = (TextView) findViewById(R.id.tvMessage2);

        btInsert = (Button) findViewById(R.id.btInsert2);
        btUpdate = (Button) findViewById(R.id.btUpdate2);
        btDelete = (Button) findViewById(R.id.btDelete2);
        btBack = (Button) findViewById(R.id.btBack);

        Intent mIntent = getIntent();
        edtIdTransaksi.setText(mIntent.getStringExtra("id_transaksi"));
        edtIdBaju.setText(mIntent.getStringExtra("id_baju"));
        edtIdPembeli.setText(mIntent.getStringExtra("id_pembeli"));

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDelTransaksi> updateTransaksiCall = mApiInterface.putTransaksi(
                        edtIdTransaksi.getText().toString(),
                        edtIdBaju.getText().toString(),
                        edtIdPembeli.getText().toString());

                updateTransaksiCall.enqueue(new Callback<PostPutDelTransaksi>() {
                    @Override
                    public void onResponse(Call<PostPutDelTransaksi> call, Response<PostPutDelTransaksi> response) {
                        tvMessage.setText(" Retrofit Update: " +
                                "\n " + " Status Update : " +response.body().getStatus() +
                                "\n " + " Message Update : "+ response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<PostPutDelTransaksi> call, Throwable t) {
                        tvMessage.setText("Retrofit Update: \n Status Update :"+ t.getMessage());
                    }
                });
            }
        });

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDelTransaksi> postTransaksiCall = mApiInterface.postTransaksi(
                        edtIdTransaksi.getText().toString(),
                        edtIdBaju.getText().toString(),
                        edtIdPembeli.getText().toString());

                postTransaksiCall.enqueue(new Callback<PostPutDelTransaksi>() {
                    @Override
                    public void onResponse(Call<PostPutDelTransaksi> call, Response<PostPutDelTransaksi> response) {
                        tvMessage.setText(" Retrofit Insert: " +
                                "\n " + " Status Insert : " +
                                response.body().getStatus() +
                                "\n " + " Message Insert : "+ response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<PostPutDelTransaksi> call, Throwable t) {
                        tvMessage.setText("Retrofit Insert: \n Status Insert :"+ t.getMessage());
                    }
                });
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtIdTransaksi.getText().toString().trim().isEmpty()){

                    Call<PostPutDelTransaksi> deleteTransaksi = mApiInterface.deleteTransaksi(edtIdTransaksi.getText().toString());
                    deleteTransaksi.enqueue(new Callback<PostPutDelTransaksi>() {
                        @Override
                        public void onResponse(Call<PostPutDelTransaksi> call, Response<PostPutDelTransaksi> response) {
                            tvMessage.setText(" Retrofit Delete: " +
                                    "\n " + " Status Delete : " +response.body().getStatus() +
                                    "\n " + " Message Delete : "+ response.body().getMessage());
                        }

                        @Override
                        public void onFailure(Call<PostPutDelTransaksi> call, Throwable t) {
                            tvMessage.setText("Retrofit Delete: \n Status Delete :"+ t.getMessage());
                        }
                    });
                }else{
                    tvMessage.setText("id_transaksi harus diisi");
                }
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mIntent);
            }
        });
    }
}
