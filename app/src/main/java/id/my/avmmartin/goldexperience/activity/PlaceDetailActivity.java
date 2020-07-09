package id.my.avmmartin.goldexperience.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;

import id.my.avmmartin.goldexperience.GoldExperience;
import id.my.avmmartin.goldexperience.R;
import id.my.avmmartin.goldexperience.data.model.Place;
import id.my.avmmartin.goldexperience.activity.dialog.AddPlanDialog;
import id.my.avmmartin.goldexperience.data.model.Plan;
import id.my.avmmartin.goldexperience.utils.Constants;

public class PlaceDetailActivity extends AppCompatActivity implements AddPlanDialog.Listener {
    private GoldExperience mainApp;

    private TextView tvName;
    private RatingBar rbRating;
    private TextView tvDescription;
    private SupportMapFragment fragmentGMaps;
    private Button btnAddPlace;

    private Place place;

    // event activity

    private void btnAddPlanOnClick() {
        AddPlanDialog dialog = new AddPlanDialog();
        dialog.show(getSupportFragmentManager(), "");
    }

    @Override
    public void btnSubmitOnClick(AddPlanDialog dialog) {
        String name = dialog.getEtName().getText().toString();
        Calendar date = dialog.getCalendar();
        Calendar time = dialog.getCalendar();
        String note = dialog.getEtNote().getText().toString();

        int userId = mainApp.getDataManager().getAppUserId();
        Plan plan = new Plan(place.getId(), userId, name, date, time, note);
        mainApp.getDataManager().insertNewPlan(plan);
    }

    // overridden method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
    }

    @Override
    protected void onStart() {
        super.onStart();

        initComponents();
        loadData();
        setEvents();
    }

    private void initComponents() {
        mainApp = (GoldExperience) getApplication();

        tvName = findViewById(R.id.placedetail_tv_name);
        rbRating = findViewById(R.id.placedetail_rb_rating);
        tvDescription = findViewById(R.id.placedetail_tv_description);
        fragmentGMaps = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.placedetail_fragment_gmaps);
        btnAddPlace = findViewById(R.id.placedetail_btn_addplace);

        int placeId = getIntent().getIntExtra(Constants.INTENT_PLACE_ID, -1);
        place = mainApp.getDataManager().getPlace(placeId);
    }

    private void loadData() {
        tvName.setText(place.getName());
        rbRating.setRating((float) place.getRating());
        tvDescription.setText(place.getDesc());
        fragmentGMaps.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                loadGoogleMapsData(googleMap);
            }
        });
    }

    private void loadGoogleMapsData(GoogleMap googleMap) {
        LatLng latLng = new LatLng(place.getLatitude(), place.getLongitude());
        CameraPosition cameraPosition = new CameraPosition.Builder()
            .target(latLng)
            .zoom((float) Constants.GMAPS_ZOOM_LEVEL)
            .build();

        googleMap.addMarker(new MarkerOptions().position(latLng));
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void setEvents() {
        btnAddPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAddPlanOnClick();
            }
        });
    }
}
