package id.my.avmmartin.goldexperience.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.my.avmmartin.goldexperience.GoldExperience;
import id.my.avmmartin.goldexperience.R;
import id.my.avmmartin.goldexperience.exception.EmptyEntryException;
import id.my.avmmartin.goldexperience.exception.InvalidCredentialsException;
import id.my.avmmartin.goldexperience.utils.Constants;

public class LoginActivity extends AppCompatActivity {
    private GoldExperience mainApp;

    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;

    // event activity

    private void btnLoginOnClick() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        try {
            if (email.isEmpty()) {
                throw new EmptyEntryException(R.string.warning_email_filled);
            }

            if (password.isEmpty()) {
                throw new EmptyEntryException(R.string.warning_password_filled);
            }

            mainApp.getDataManager().login(email, password);

            Intent intent = new Intent(LoginActivity.this, PlaceListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        } catch (InvalidCredentialsException e) {
            Toast.makeText(LoginActivity.this, getString(e.getErrorId()), Toast.LENGTH_SHORT).show();
        } catch (EmptyEntryException e) {
            Toast.makeText(LoginActivity.this, getString(e.getErrorId()), Toast.LENGTH_SHORT).show();
        }
    }

    private void btnRegisterOnClick() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    // overridden method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

        etEmail = findViewById(R.id.login_et_email);
        etPassword = findViewById(R.id.login_et_password);
        btnLogin = findViewById(R.id.login_btn_login);
        btnRegister = findViewById(R.id.login_btn_register);
    }

    private void loadData() {
        etEmail.setText(getIntent().getStringExtra(Constants.INTENT_EMAIL));
    }

    private void setEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLoginOnClick();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegisterOnClick();
            }
        });
    }
}
