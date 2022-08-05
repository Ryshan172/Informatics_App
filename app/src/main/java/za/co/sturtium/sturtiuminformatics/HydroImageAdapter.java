package za.co.sturtium.sturtiuminformatics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class HydroImageAdapter extends RecyclerView.Adapter<HydroImageAdapter.HydroImageViewHolder> {

    private Context mContext;
    private List<WasteUpload> mWasteUploads;

    public HydroImageAdapter(Context context, List<WasteUpload> wasteUploads){
        mContext = context;
        mWasteUploads = wasteUploads;
    }

    @NonNull
    @Override
    public HydroImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.hydrodata_item, parent, false);
        return new HydroImageViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull HydroImageViewHolder holder, int position) {
        WasteUpload wasteuploadCurrent = mWasteUploads.get(position);
        holder.textViewCityName.setText(wasteuploadCurrent.getCityName());
        holder.textViewIssueDescription.setText(wasteuploadCurrent.getIssueDescription());
        holder.textViewGeolocation.setText(wasteuploadCurrent.getGeolocation());
        Picasso.with(mContext)
                .load(wasteuploadCurrent.getWasteImageUrl())
                .fit()
                .centerCrop()
                .into(holder.displaywasteimage);




    }

    @Override
    public int getItemCount() {
        return mWasteUploads.size();
    }

    public class HydroImageViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewCityName;
        public TextView textViewIssueDescription;
        public TextView textViewGeolocation;
        public ImageView displaywasteimage;

        public HydroImageViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewCityName = itemView.findViewById(R.id.text_view_wastecityname);
            textViewIssueDescription = itemView.findViewById(R.id.text_view_issuedescription);
            textViewGeolocation = itemView.findViewById(R.id.text_view_wastelocation);
            displaywasteimage = itemView.findViewById(R.id.image_view_wasteuploads);

        }
    }

}
