package com.example.aditya123666.go_api;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.aditya123666.go_api.adapter.PesanAdapter;
import com.example.aditya123666.go_api.api.BaseApiService;
import com.example.aditya123666.go_api.api.UtilsApi;
import com.example.aditya123666.go_api.model.Pesan;
import com.example.aditya123666.go_api.model.ResponsePesan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Pesan> listMenu;
    private RecyclerView mRecyclerView;
    private PesanAdapter mAdapter;
    BaseApiService apiService;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listMenu = new ArrayList<>();
        mAdapter = new PesanAdapter(getApplicationContext(), listMenu);
        mRecyclerView = findViewById(R.id.rv_list_menu);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        apiService = UtilsApi.getAPIService();



        //backButton();
        final String key = "123";

        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);
        Call<ResponsePesan> call = apiService.getAllPesanan(key);
        call.enqueue(new Callback<ResponsePesan>() {
            @Override
            public void onResponse(Call<ResponsePesan> call, Response<ResponsePesan> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    listMenu = response.body().getData();

                    mRecyclerView.setAdapter(new PesanAdapter(getApplicationContext(), listMenu));
                    mAdapter.notifyDataSetChanged();
                }
                else {
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), "Failed fletch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePesan> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });


    }

}

