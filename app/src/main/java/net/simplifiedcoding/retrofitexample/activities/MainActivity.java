package net.simplifiedcoding.retrofitexample.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.simplifiedcoding.retrofitexample.R;
import net.simplifiedcoding.retrofitexample.api.APIService;
import net.simplifiedcoding.retrofitexample.helper.SharedPrefManager;
import net.simplifiedcoding.retrofitexample.models.Result;
import net.simplifiedcoding.retrofitexample.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignIn, buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if user is already logged in openeing the profile activity
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, HomeActivity.class));
        }

        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);

        buttonSignIn.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == buttonSignIn) {

            startActivity(new Intent(this, SignInActivity.class));

        } else if (view == buttonSignUp) {

            startActivity(new Intent(this, SignUpActivity.class));

        }
    }
}
