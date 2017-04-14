package net.simplifiedcoding.retrofitexample.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import net.simplifiedcoding.retrofitexample.R;
import net.simplifiedcoding.retrofitexample.activities.HomeActivity;
import net.simplifiedcoding.retrofitexample.api.APIService;
import net.simplifiedcoding.retrofitexample.api.APIUrl;
import net.simplifiedcoding.retrofitexample.helper.SharedPrefManager;
import net.simplifiedcoding.retrofitexample.helper.UserAdapter;
import net.simplifiedcoding.retrofitexample.models.Result;
import net.simplifiedcoding.retrofitexample.models.User;
import net.simplifiedcoding.retrofitexample.models.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Belal on 14/04/17.
 */

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private Button buttonUpdate;
    private EditText editTextName, editTextEmail, editTextPassword;
    private RadioGroup radioGender;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Profile");

        buttonUpdate = (Button) view.findViewById(R.id.buttonUpdate);

        editTextName = (EditText) view.findViewById(R.id.editTextName);
        editTextEmail = (EditText) view.findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) view.findViewById(R.id.editTextPassword);

        radioGender = (RadioGroup) view.findViewById(R.id.radioGender);

        buttonUpdate.setOnClickListener(this);

        User user = SharedPrefManager.getInstance(getActivity()).getUser();

        editTextName.setText(user.getName());
        editTextEmail.setText(user.getEmail());
        editTextPassword.setText("0000");

        if (user.getGender().equalsIgnoreCase("male")) {
            radioGender.check(R.id.radioMale);
        } else {
            radioGender.check(R.id.radioFemale);
        }

    }

    private void updateUser() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        final RadioButton radioSex = (RadioButton) getActivity().findViewById(radioGender.getCheckedRadioButtonId());

        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String gender = radioSex.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        User user = new User(SharedPrefManager.getInstance(getActivity()).getUser().getId(), name, email, password, gender);

        Call<Result> call = service.updateUser(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getGender()
        );

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                if (!response.body().getError()) {
                    SharedPrefManager.getInstance(getActivity()).userLogin(response.body().getUser());
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == buttonUpdate) {
            updateUser();
        }
    }
}
