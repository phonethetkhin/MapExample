package com.example.mapexample.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mapexample.Model.CoordinateModel;
import com.example.mapexample.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {
TextView tvTitle;
TextInputEditText tietName,tietLang,tietLong;
Button btnSave;
FirebaseDatabase database;
DatabaseReference myRef;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_add, container, false);

        tvTitle=v.findViewById(R.id.tvTitle);
        tietName=v.findViewById(R.id.tietName);
        tietLang=v.findViewById(R.id.tietLatitude);
        tietLong=v.findViewById(R.id.tietLongitude);

        btnSave=v.findViewById(R.id.btnSave);

        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("Coordinates");
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CoordinateModel cModel=new CoordinateModel(tietName.getText().toString(),tietLang.getText().toString(),tietLong.getText().toString());
                myRef.child(cModel.getTitle()).setValue(cModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(), "Success", Toast.LENGTH_LONG).show();
Clear();

                    }
                });
            }
        });








        return v;
    }
    private void Clear()
    {
       tietName.setText(null);
       tietLang.setText(null);
       tietLong.setText(null);
       tietName.requestFocus();
    }

}
