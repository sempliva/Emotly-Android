package com.sempliva.emotly.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sempliva.emotly.R;
import com.sempliva.emotly.rest.data.JWT;
import com.sempliva.emotly.rest.ServerAPI;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Callback<JWT> {

    private EditText tvUserID;
    private EditText tvPassword;
    private Button loginButton;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        tvUserID = (EditText) findViewById(R.id.tv_userid);
        tvPassword = (EditText) findViewById(R.id.tv_password);
        loginButton = (Button) findViewById(R.id.bt_login);

        loginButton.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        //close keyboard
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        //make API call
        if((tvUserID.getText().length()==0)||(tvPassword.getText().length()==0)){
            Toast.makeText(this, getResources().getString(R.string.TOAST_INSERT_DATA), Toast.LENGTH_SHORT).show();
        }
        ServerAPI.getInstance().login(tvUserID.getText().toString(),
                tvPassword.getText().toString(), this);
    }

    @Override
    public void onResponse(Call<JWT> call, Response<JWT> response) {
        if(response.body() != null){
            Toast.makeText(this, getResources().getString(R.string.TOAST_LOGIN_SUCCESS), Toast.LENGTH_SHORT).show();
            preferences.edit().putString(getString(R.string.PREFERENCE_JWT), response.body().toString()).commit();
            //TODO: go to next activity
        } else{
            //TODO: parse json
            Toast.makeText(this, getString(R.string.TOAST_LOGIN_ERROR)+response.errorBody().toString(), Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onFailure(Call<JWT> call, Throwable t) {
        Toast.makeText(this, getString(R.string.TOAST_NETWORK_ERROR), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
