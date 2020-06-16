package al.tareas.tarearecyclerview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterSearchResult extends RecyclerView.Adapter<AdapterSearchResult.ViewHolder> {

    private Context mContext;
    private ArrayList<SearchResult> mSearchResultsList;

    public AdapterSearchResult (Context context, ArrayList<SearchResult> searchResultsList) {
        this.mContext = context;
        this.mSearchResultsList = searchResultsList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_title;
        public TextView txt_description;
        public ImageView mImageView;
        public TextView txt_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_title = itemView.findViewById(R.id.txt_title);
            txt_description = itemView.findViewById(R.id.txt_description);
            mImageView = itemView.findViewById(R.id.logo);
            txt_date = itemView.findViewById(R.id.txt_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    String url = mSearchResultsList.get(position).get_url();

                    Intent browserIntent  = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    mContext.startActivity(browserIntent);
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.result_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchResult currentItem = mSearchResultsList.get(position);
        String title = currentItem.get_title();
        String desc = currentItem.get_description();
        String urlImg = currentItem.get_urlToImage();
        String date = currentItem.get_publishedDate();

        holder.txt_title.setText(title);
        holder.txt_description.setText(desc);
        holder.txt_date.setText(date);

        Picasso.with(mContext).load(urlImg).fit().centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mSearchResultsList.size();
    }
}
