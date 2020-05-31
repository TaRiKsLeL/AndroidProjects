package com.example.weathers_list_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherHolder> {

    List<Weather> weathers = new ArrayList<>();

    @NonNull
    @Override
    public WeatherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_item, parent,false);
        return new WeatherHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherHolder holder, int position) {
        Weather w = weathers.get(position);
        holder.cityNameTextView.setText(w.getName());
        holder.temperatureTextView.setText(String.format("%.2f",w.getTemp()));
    }

    public void setWeathers(List<Weather> weathers){
        this.weathers = weathers;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }

    public Weather getWeatherAt(int position) {
        return weathers.get(position);
    }

    class WeatherHolder extends RecyclerView.ViewHolder{
        private TextView cityNameTextView;
        private TextView temperatureTextView;

        public WeatherHolder(@NonNull View itemView) {
            super(itemView);

            cityNameTextView = itemView.findViewById(R.id.cityNameTextView);
            temperatureTextView = itemView.findViewById(R.id.temperatureTextView);
        }
    }

}
