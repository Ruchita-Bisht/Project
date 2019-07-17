package com.example.project;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_Up extends Fragment
{
    EditText e3,e4,e5,e6;
    Button b2;
    DatabaseReference dbr;
    FirebaseAuth auth;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View v=inflater.inflate(R.layout.fragment_sign__up, container, false);

         e3 = v.findViewById(R.id.editText3);
         e4 = v.findViewById(R.id.editText4);
         e5 = v.findViewById(R.id.editText5);
         e6 = v.findViewById(R.id.editText6);
         b2 = v.findViewById(R.id.button2);
         dbr= FirebaseDatabase.getInstance().getReference("User_Information");
         auth= FirebaseAuth.getInstance();
        // sharedPreferences= getActivity().getSharedPreferences("data",Context.MODE_PRIVATE);
         //editor = sharedPreferences.edit();


        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final String username = e3.getText().toString();
                final String userphone = e4.getText().toString();
                final String useremail = e5.getText().toString();
                final String usernewpass = e6.getText().toString();

                if (username.isEmpty() && userphone.isEmpty() && useremail.isEmpty() && usernewpass.isEmpty())
                {
                    e3.setError("");
                    e4.setError("");
                    e5.setError("");
                    e6.setError("");
                }
                else if(username.isEmpty())
                {
                    e3.setError("");
                }
               else if(userphone.isEmpty())
                {
                    e4.setError("");
                }
               else if(useremail.isEmpty())
                {
                    e5.setError("");
                }
               else if (usernewpass.isEmpty())
                {
                    e6.setError("");
                }
                else
                {
                    UserInformation info= new UserInformation();
                    info.setName(username);
                    info.setPhone(userphone);
                    info.setEmail(useremail);
                    info.setNewpass(usernewpass);


                    String ChildId =dbr.push().getKey();
                    dbr.child(ChildId).setValue(info);

                    auth.createUserWithEmailAndPassword(useremail,usernewpass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult)
                        {
                            
                            Intent intent = new Intent(getActivity(),HomePage.class);
                            startActivity(intent);
                            Toast.makeText(getActivity(), "Your Data Is Saved", Toast.LENGTH_SHORT).show();



                        }
                    }).addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });



                      e3.setText("");
                      e4.setText("");
                      e5.setText("");
                      e6.setText("");




                }

            }
        });

        return v;

    }

}
