package id.my.avmmartin.goldexperience.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import id.my.avmmartin.goldexperience.R;
import id.my.avmmartin.goldexperience.data.model.User;
import id.my.avmmartin.goldexperience.exception.EmptyEntryException;
import id.my.avmmartin.goldexperience.exception.InvalidEntryException;
import id.my.avmmartin.goldexperience.utils.Constants;

public class ProfileActivity extends ProfileForm {
    private Button btnUpdate;
    private Button btnLogout;

    private User user;

    // event activity

    private void btnUpdateOnClick() {
        try {
            mainApp.getDataManager().updateUser(user.getId(), super.getUserFromEntry());

            Toast.makeText(ProfileActivity.this, R.string.success_update, Toast.LENGTH_SHORT).show();
            finish();

        } catch (EmptyEntryException e) {
            Toast.makeText(ProfileActivity.this, getString(e.getErrorId()), Toast.LENGTH_SHORT).show();
        } catch (InvalidEntryException e) {
            Toast.makeText(ProfileActivity.this, getString(e.getErrorId()), Toast.LENGTH_SHORT).show();
        }
    }

    private void btnLogoutOnClick() {
        mainApp.getDataManager().logout();

        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(Constants.INTENT_EMAIL, user.getEmail());
        startActivity(intent);
    }

    // overridden method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    @Override
    protected void onStart() {
        super.onStart();

        getSupportActionBar().setTitle(R.string.title_profile);
        loadData(user);
    }

    @Override
    protected void initComponents() {
        super.initComponents();

        btnUpdate = findViewById(R.id.profile_btn_update);
        btnLogout = findViewById(R.id.profile_btn_logout);

        user = mainApp.getDataManager().getAppUser();
    }

    @Override
    protected void setEvents() {
        super.setEvents();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnUpdateOnClick();
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLogoutOnClick();
            }
        });
    }
}
