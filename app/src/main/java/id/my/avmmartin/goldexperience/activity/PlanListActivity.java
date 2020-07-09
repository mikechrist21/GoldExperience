package id.my.avmmartin.goldexperience.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import id.my.avmmartin.goldexperience.GoldExperience;
import id.my.avmmartin.goldexperience.R;
import id.my.avmmartin.goldexperience.activity.adapter.PlanListAdapter;
import id.my.avmmartin.goldexperience.activity.adapter.PlanListListener;
import id.my.avmmartin.goldexperience.activity.dialog.DeletePlanDialog;
import id.my.avmmartin.goldexperience.data.model.Plan;

public class PlanListActivity extends AppCompatActivity implements DeletePlanDialog.Listener {
    private GoldExperience mainApp;

    private RecyclerView rvPlanData;

    private PlanListAdapter adapter;

    // event activity

    public void planListAdapterBtnDeleteOnClick(Plan plan) {
        DeletePlanDialog dialog = new DeletePlanDialog();
        dialog.bindData(plan);
        dialog.show(getSupportFragmentManager(), "");
    }

    @Override
    public void btnSubmitOnClick(DeletePlanDialog dialog) {
        mainApp.getDataManager().deletePlanById(dialog.getPlan().getId());
        adapter.notifyDataSetChanged();
    }

    // overridden method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_list);
    }

    @Override
    protected void onStart() {
        super.onStart();

        initComponents();
        loadData();
        setEvents();
        getSupportActionBar().setTitle(R.string.title_plan_list);
    }

    private void initComponents() {
        mainApp = (GoldExperience) getApplication();

        rvPlanData = findViewById(R.id.planlist_rv_plandata);

        adapter = new PlanListAdapter(PlanListActivity.this);
    }

    private void loadData() {
        rvPlanData.setLayoutManager(new LinearLayoutManager(PlanListActivity.this));
        rvPlanData.setAdapter(adapter);
    }

    private void setEvents() {
        adapter.setItemListener(new PlanListListener() {
            @Override
            public void btnDeleteOnClick(Plan plan) {
                planListAdapterBtnDeleteOnClick(plan);
            }
        });
    }
}
