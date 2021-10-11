package com.devendrasaini.test;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    PhotoApiService mApiService;
    RecyclerView mRecyclerView;
    PhotoListAdapter mListAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uiInitialization();
        getApiCall();
    }

    public void uiInitialization() {
        mRecyclerView = findViewById(R.id.recyclerView);
    }

    public void getApiCall() {
        mApiService = PhotoApiClient.getApiInstance().create(PhotoApiService.class);
        Call<List<PhotoModel>> call = mApiService.getPhotos();
        progressDialog.show();

        call.enqueue(new Callback<List<PhotoModel>>() {
            @Override
            public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
                mListAdapter = new PhotoListAdapter(getApplicationContext(), response.body());
                mRecyclerView.setAdapter(mListAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                        LinearLayoutManager.VERTICAL, false));
                progressDialog.hide();
            }

            @Override
            public void onFailure(Call<List<PhotoModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}