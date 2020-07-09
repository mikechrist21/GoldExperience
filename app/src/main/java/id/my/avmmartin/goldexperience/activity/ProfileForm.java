package id.my.avmmartin.goldexperience.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.Calendar;

import id.my.avmmartin.goldexperience.GoldExperience;
import id.my.avmmartin.goldexperience.R;
import id.my.avmmartin.goldexperience.data.model.User;
import id.my.avmmartin.goldexperience.exception.EmptyEntryException;
import id.my.avmmartin.goldexperience.exception.InvalidEntryException;
import id.my.avmmartin.goldexperience.utils.CalendarUtils;
import id.my.avmmartin.goldexperience.utils.ValidationUtils;

abstract class ProfileForm extends AppCompatActivity {
    protected GoldExperience mainApp;

    private EditText etEmail;
    private EditText etPassword;
    private EditText etFullName;
    private EditText etBirthday;
    private EditText etPhone;
    private Spinner spUserType;
    private RadioGroup rdSex;

    private Calendar calendar;

    protected User getUserFromEntry() throws EmptyEntryException, InvalidEntryException {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String fullName = etFullName.getText().toString();
        Calendar birthday = calendar;
        String phone = etPhone.getText().toString();
        boolean userTypeVIP = spUserType.getSelectedItem().toString().contains("VIP");
        boolean sexMale = rdSex.getCheckedRadioButtonId() == R.id.t_profile_rd_sex_male;

        if (email.isEmpty()) {
            throw new EmptyEntryException(R.string.warning_email_filled);
        } else if (!ValidationUtils.isValidEmail(email)) {
            throw new InvalidEntryException(R.string.warning_email_invalid);
        }

        if (password.isEmpty()) {
            throw new EmptyEntryException(R.string.warning_password_filled);
        } else if (!ValidationUtils.isValidPassword(password)) {
            throw new InvalidEntryException(R.string.warning_password_invalid);
        }

        if (fullName.isEmpty()) {
            throw new EmptyEntryException(R.string.warning_full_name_filled);
        }

        if (etBirthday.getText().toString().isEmpty()) {
            throw new EmptyEntryException(R.string.warning_birthday_filled);
        }

        if (phone.isEmpty()) {
            throw new EmptyEntryException(R.string.warning_phone_number_filled);
        } else if (!ValidationUtils.isValidPhoneNumber(phone)) {
            throw new InvalidEntryException(R.string.warning_phone_number_invalid);
        }

        if (rdSex.getCheckedRadioButtonId() == -1) {
            throw new EmptyEntryException(R.string.warning_gender_filled);
        }

        return new User(email, password, fullName, birthday, phone, userTypeVIP, sexMale);
    }

    protected void loadData(User user) {
        etEmail.setText(user.getEmail());
        etPassword.setText("");
        etFullName.setText(user.getFullName());
        etBirthday.setText(CalendarUtils.toDateFormat(user.getBirthday()));
        etPhone.setText(user.getPhone());
        spUserType.setSelection(user.isUserTypeVIP() ? 1 : 0);
        ((RadioButton) rdSex.getChildAt(user.isSexMale() ? 1 : 2)).setChecked(true);

        calendar.setTimeInMillis(user.getBirthday().getTimeInMillis());
    }

    // event activity

    private void etBirthdayOnClick() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
            ProfileForm.this,
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DATE, day);
                    etBirthday.setText(CalendarUtils.toDateFormat(calendar));
                }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DATE)
        );
        datePickerDialog.show();
    }

    // overridden method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        initComponents();
        setEvents();
    }

    @CallSuper
    protected void initComponents() {
        mainApp = (GoldExperience) getApplication();

        etEmail = findViewById(R.id.t_profile_et_email);
        etPassword = findViewById(R.id.t_profile_et_password);
        etFullName = findViewById(R.id.t_profile_et_fullname);
        etBirthday = findViewById(R.id.t_profile_et_birthday);
        etPhone = findViewById(R.id.t_profile_et_phone);
        spUserType = findViewById(R.id.t_profile_sp_usertype);
        rdSex = findViewById(R.id.t_profile_rd_sex);

        calendar = Calendar.getInstance();
    }

    @CallSuper
    protected void setEvents() {
        etBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etBirthdayOnClick();
            }
        });
    }
}
