package id.my.avmmartin.goldexperience.activity.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.my.avmmartin.goldexperience.R;
import id.my.avmmartin.goldexperience.data.model.Place;

class PlaceListViewHolder extends RecyclerView.ViewHolder {
    private TextView tvName;
    private TextView tvDesc;

    private Place data;

    void bindData(Place data) {
        this.data = data;
    }

    void loadData() {
        tvName.setText(data.getName());
        tvDesc.setText(data.getDesc());
    }

    // constructor

    private Context context;
    private PlaceListListener listener;

    PlaceListViewHolder(@NonNull View itemView, Context context, PlaceListListener listener) {
        super(itemView);

        this.context = context;
        this.listener = listener;
        initComponents();
        setEvents();
    }

    private void initComponents() {
        tvName = itemView.findViewById(R.id.adapter_placelist_tv_name);
        tvDesc = itemView.findViewById(R.id.adapter_placelist_tv_desc);
    }

    private void setEvents() {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(data.getId());
            }
        });
    }
}
