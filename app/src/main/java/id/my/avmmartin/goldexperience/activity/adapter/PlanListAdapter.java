package id.my.avmmartin.goldexperience.activity.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.my.avmmartin.goldexperience.GoldExperience;
import id.my.avmmartin.goldexperience.R;
import id.my.avmmartin.goldexperience.data.model.Plan;

public class PlanListAdapter extends RecyclerView.Adapter<PlanListViewHolder> {
    private PlanListListener listener;

    public void setItemListener(PlanListListener listener) {
        this.listener = listener;
    }

    // overridden method

    @NonNull
    @Override
    public PlanListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.adapter_plan_list, parent, false);

        return new PlanListViewHolder(v, activity, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanListViewHolder holder, int position) {
        Plan plan = mainApp.getDataManager().getPlanByPosition(position);

        holder.bindData(plan);
        holder.loadData();
    }

    @Override
    public int getItemCount() {
        return mainApp.getDataManager().planSize();
    }

    // constructor

    private GoldExperience mainApp;
    private Activity activity;

    public PlanListAdapter(Activity activity) {
        this.mainApp = (GoldExperience) activity.getApplication();
        this.activity = activity;
    }
}
