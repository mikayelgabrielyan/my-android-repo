package com.project.itc9.taskmanageraplication;

import android.os.Bundle;
import android.view.View;
import java.util.Objects;
import android.widget.Toast;
import android.widget.Button;
import android.view.ViewGroup;
import android.widget.EditText;
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
public class RegistrationFragment extends Fragment {
    private EditText mEmailText;
    private EditText mPasswordText;
    private Button mRegistButton;
    private FirebaseAuth mAuth;

    public RegistrationFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        initElements(view);
        mAuth = FirebaseAuth.getInstance();
        mRegistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registration(mEmailText.getText().toString(), mPasswordText.getText().toString());
            }
        });
        return view;
    }

    private void registration(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Registration Successed",Toast.LENGTH_LONG).show();
                            openSignInPage();
                        } else {
                            Toast.makeText(getActivity(), "Registration Filed",Toast.LENGTH_LONG).show();
                        }
                        }
                });
    }

    private void initElements(View view) {
        mEmailText = view.findViewById(R.id.regist_email);
        mPasswordText = view.findViewById(R.id.regist_password);
        mRegistButton = view.findViewById(R.id.regist_btn);
    }

    private void openSignInPage() {
        SignInFragment signInFragment = new SignInFragment();
        FragmentManager manager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.m_container, signInFragment);
        transaction.addToBackStack("SIGNIN_FRAGMENT");
        transaction.commit();
    }

}
