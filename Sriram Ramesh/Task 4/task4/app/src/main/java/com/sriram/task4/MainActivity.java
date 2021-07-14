package com.sriram.task4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    RecyclerView recyclerView;
    Recycleradapter recyclerAdapter;
    public ArrayList<String> modelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        modelArrayList=new ArrayList<String>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi JsonPlaceHolderApi = retrofit.create(jsonPlaceHolderApi.class);
        Call<modelClass> call = JsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<modelClass>() {
            @Override
            public void onResponse(Call<modelClass> call, Response<modelClass> response) {
                if(!response.isSuccessful()){
                    Log.e("this","ecode: "+response.code());
                    return;

                }

                modelClass posts = response.body();

                assert posts != null;
                HashMap<String, Object> yourHashMap = new Gson().fromJson(posts.getName().toString(), HashMap.class);
                for (String url: yourHashMap.keySet())
                {
                    Log.i("",""+ url);

                    modelArrayList.add(url);
                }


            }
            @Override
            public void onFailure(Call<modelClass> call, Throwable t) {
                Log.i("this",""+t.getMessage());
            }
        });

        modelArrayList.add("wolfhound");


        recyclerAdapter = new Recycleradapter(MainActivity.this,modelArrayList);
        recyclerView.setAdapter(recyclerAdapter);



    }



}