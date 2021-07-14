package com.sriram.task4;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Recycleradapter extends RecyclerView.Adapter<Recycleradapter.ViewHolder> {

    public Context context;
    public ArrayList<String> modelArrayList;
    String url;
    public Recycleradapter(Context context, ArrayList<String> ModelarrayList) {
        this.context = context;
        this.modelArrayList = ModelarrayList;
    }
    @NonNull
    @Override
    public Recycleradapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Recycleradapter.ViewHolder holder, int position) {
        String models = modelArrayList.get(position);

        holder.breed.setText(models);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi JsonPlaceHolderApi = retrofit.create(jsonPlaceHolderApi.class);
        Call<Images> call = JsonPlaceHolderApi.getUrl(models);
        call.enqueue(new Callback<Images>() {
            @Override
            public void onResponse(Call<Images> call, Response<Images> response) {
                if (!response.isSuccessful()) {
                    Log.i("this","Code: "+ response.code());
                    return;
                }
                Images images = response.body();
                url = images.getUrl();
                Picasso.with(context).load(url).into(holder.imageView);

            }

            @Override
            public void onFailure(Call<Images> call, Throwable t) {
                Log.i("this","Error : " + t.toString());
            }
        });


    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView breed;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            breed = itemView.findViewById(R.id.breed);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

}
