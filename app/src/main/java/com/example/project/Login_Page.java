package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.se.omapi.Session;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.Context.MODE_PRIVATE;

public class Login_Page extends Fragment {
    EditText e1, e2;
    Button b1;
    FirebaseAuth auth;
    TextView t1;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
//    SessionManagement session;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login__page, container, false);
        e1 = v.findViewById(R.id.editText);
        e2 = v.findViewById(R.id.editText2);
        b1 = v.findViewById(R.id.button);
        auth = FirebaseAuth.getInstance();
        t1 = v.findViewById(R.id.textView);
//        session = new SessionManagement(getContext());

      pref = getActivity().getSharedPreferences("data", MODE_PRIVATE);

         editor = pref.edit();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String user = e1.getText().toString();
                final String pass = e2.getText().toString();


                if (user.isEmpty() && pass.isEmpty())
                {
                    e1.setError("Enter Username");
                    e2.setError("Enter Password");
                }
                else if (user.isEmpty())
                {
                    e1.setError("Enter Username");
                }
                else if (pass.isEmpty())
                {
                    e2.setError("Enter Password");
                }
                else
                {    auth.signInWithEmailAndPassword(user, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {


                            Intent intent= new Intent(getActivity(),HomePage.class);
                            startActivity(intent);

                            Toast.makeText(getActivity(), "Welcome ", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    e1.setText("");
                    e2.setText("");
                }
            }
        });

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
             //   ft.addToBackStack("");
                ft.replace(R.id.main_page, new Sign_Up());
                ft.commit();

            }
        });

        return v;
    }
}
