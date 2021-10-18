package com.devendrasaini.test.views;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.devendrasaini.test.R;
import com.devendrasaini.test.databinding.ActivityMainBinding;
import com.devendrasaini.test.model.PhotoModel;
import com.devendrasaini.test.service.PhotoApiClient;
import com.devendrasaini.test.service.PhotoApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private PhotoApiService mApiService;]
    private PhotoListAdapter mListAdapter;

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.textTitle.setText("Gallery");

        //bind recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mainBinding.recyclerView.setLayoutManager(layoutManager);

        getApiCall();
    }

    public void getApiCall() {
        mApiService = PhotoApiClient.getApiInstance().create(PhotoApiService.class);

        Call<List<PhotoModel>> listCall = mApiService.getPhotos();
        listCall.enqueue(new Callback<List<PhotoModel>>() {
            @Override
            public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
                mListAdapter = new PhotoListAdapter(getApplicationContext(), response.body());
                
            }

            @Override
            public void onFailure(Call<List<PhotoModel>> call, Throwable t) {

            }
        });
    }
}