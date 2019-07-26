package com.project.itc9.taskmanageraplication;

import android.os.Bundle;
import android.view.View;
import java.util.Objects;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.EditText;
import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.google.android.gms.tasks.OnCompleteListener;
import android.support.annotation.NonNull;
/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment implements View.OnClickListener {
    private EditText mEmailText;
    private EditText mPasswordText;
    private Button mSigninButton;
    private Button mRegistButton;
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public SignInFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        initElements(view);
        setOnClickListeners();
        return view;
    }

    @Override
    public void onClick(View view) {
        int btn_id = view.getId();
        switch (btn_id) {
            case R.id.registration_btn:
                openRegistrationPage();
                break;
            case R.id.signin_btn:
                signIn(mEmailText.getText().toString(), mPasswordText.getText().toString());
        }
    }

    private void initElements(View view) {
        mEmailText = view.findViewById(R.id.signin_email);
        mPasswordText = view.findViewById(R.id.signin_password);
        mSigninButton = view.findViewById(R.id.signin_btn);
        mRegistButton = view.findViewById(R.id.registration_btn);
    }

    private void setOnClickListeners() {
        mSigninButton.setOnClickListener(this);
        mRegistButton.setOnClickListener(this);
    }

    private void openRegistrationPage() {
        RegistrationFragment registrationFragment = new RegistrationFragment();
        FragmentManager manager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.m_container, registrationFragment);
        transaction.addToBackStack("SIGNIN_FRAGMENT");
        transaction.commit();
    }

    public void signIn(String email, String password) {
        if (!validate()) {
            onLoginFailed();
            return;
        }
        mSigninButton.setEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(getActivity(), ProgressDialog.THEME_HOLO_DARK);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Connecting...");
        progressDialog.show();
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onLoginSuccess();
                        progressDialog.dismiss();
                    }
                }, 3000);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Intent intent = new Intent(getActivity(), ListOfTasksActivity.class);
                    startActivity(intent);
                } else {
                    onLoginFailed();
                }
            }
        });
    }

    public void onLoginSuccess() {
        mSigninButton.setEnabled(true);
    }

    public void onLoginFailed() {
        Toast.makeText(getActivity(), "Signin failed", Toast.LENGTH_LONG).show();
        mSigninButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;
        String email = mEmailText.getText().toString();
        String password = mPasswordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailText.setError("enter a valid email address");
            valid = false;
        } else {
            mEmailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 6 || password.length() > 15) {
            mPasswordText.setError("between 6 and 15 alphanumeric characters");
            valid = false;
        } else {
            mPasswordText.setError(null);
        }
        return valid;
    }
}