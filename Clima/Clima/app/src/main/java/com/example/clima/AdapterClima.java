package com.example.clima;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterClima extends RecyclerView.Adapter<AdapterClima.ViewHolder> {

    private Context mContext;
    private ArrayList<Clima> mClimaList ;

public AdapterClima (Context context, ArrayList<Clima> climaList) {
    mContext = context;
    mClimaList = climaList;
}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(mContext).inflate(R.layout.clima_item,parent,false);
    return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    Clima currentItem = mClimaList.get(position);
    String climaUrl = currentItem.getClimaUrl();
    String forecastDia = currentItem.get_mForecastDia();
    String forecastDescripcion = currentItem.getForecastDescripcion();
    String forecastMinMax = currentItem.getForecastMinMax();

    holder.mForecastDia.setText(forecastDia);
    holder.mForecastDescripcion.setText(forecastDescripcion);
    holder.mForecastMinMax.setText(forecastMinMax);
    Picasso.with(mContext).load(climaUrl).fit().centerInside().into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
       return mClimaList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mForecastDia;
        public TextView mForecastDescripcion;
        public TextView mForecastMinMax;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            mImageView = itemView.findViewById(R.id.image_view);
            mForecastDia = itemView.findViewById(R.id.txt_forecastDia);
            mForecastDescripcion = itemView.findViewById(R.id.txt_forecastDescripcion);
            mForecastMinMax = itemView.findViewById(R.id.txt_forecastMinMax);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    mClimaList.remove(position);
                    notifyItemRemoved(position);

                }
            });

        }

    }

}
