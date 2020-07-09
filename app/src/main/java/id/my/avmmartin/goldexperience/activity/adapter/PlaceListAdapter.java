package id.my.avmmartin.goldexperience.activity.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Vector;

import id.my.avmmartin.goldexperience.GoldExperience;
import id.my.avmmartin.goldexperience.R;
import id.my.avmmartin.goldexperience.data.model.Place;

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListViewHolder> {
    private PlaceListListener listener;

    public void setListener(PlaceListListener listener) {
        this.listener = listener;
    }

    public void setResources(List<Place> resources) {
        this.resources = resources;
        notifyDataSetChanged();
    }

    // overridden method

    @NonNull
    @Override
    public PlaceListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.adapter_place_list, parent, false);

        return new PlaceListViewHolder(v, activity, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceListViewHolder holder, int position) {
        Place place = resources.get(position);

        holder.bindData(place);
        holder.loadData();
    }

    @Override
    public int getItemCount() {
        return resources.size();
    }

    // constructor

    private GoldExperience mainApp;
    private Activity activity;
    private List<Place> resources;

    public PlaceListAdapter(Activity activity) {
        this.mainApp = (GoldExperience) activity.getApplication();
        this.activity = activity;
        this.resources = new Vector<>();
    }
}
