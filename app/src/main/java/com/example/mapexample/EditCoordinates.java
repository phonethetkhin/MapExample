package com.example.mapexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mapexample.Model.CoordinateModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditCoordinates extends AppCompatActivity {
    TextView tvTitle;
    TextInputEditText tietName,tietLatitude,tietLongitude;
    Button btnSave;
    FirebaseDatabase database;
    DatabaseReference ref;
    CoordinateModel cmodel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_coordinates);
        tvTitle=findViewById(R.id.tvTitle);
        tietName=findViewById(R.id.tietName);
        tietLatitude=findViewById(R.id.tietLatitude);
        tietLongitude=findViewById(R.id.tietLongitude);
        btnSave=findViewById(R.id.btnSave);
      cmodel =getIntent().getParcelableExtra("OldData");
        tietName.setText(cmodel.getTitle());
        tietLatitude.setText(cmodel.getLatitude());
        tietLongitude.setText(cmodel.getLongitude());

        database=FirebaseDatabase.getInstance();
        ref=database.getReference("Coordinates");
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateCoordinates(tietName.getText().toString(),tietLatitude.getText().toString(),tietLongitude.getText().toString());

            }
        });


    }
    private void UpdateCoordinates(String Name, String Lat, String Long)
    {
        CoordinateModel childmodel=new CoordinateModel(Name,Lat,Long);
        ref.child(cmodel.getTitle()).setValue(childmodel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                task.addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditCoordinates.this, "Edited Successfully", Toast.LENGTH_LONG).show();
                        Clear();
                        finish();

                    }
                });

            }
        });


    }
    private void Clear()
    {
        tietName.setText(null);
        tietLatitude.setText(null);
        tietLongitude.setText(null);
        tietName.requestFocus();
    }
}
