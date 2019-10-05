package com.example.mapexample.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mapexample.Adapters.ItemListAdapter;
import com.example.mapexample.Model.CoordinateModel;
import com.example.mapexample.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageFragment extends Fragment {
RecyclerView rvMain;
FirebaseDatabase db;
DatabaseReference Ref;
    CoordinateModel cmodel;
    List<CoordinateModel> coordinateModelList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_manage, container, false);
        rvMain=v.findViewById(R.id.rvMain);
        db=FirebaseDatabase.getInstance();
        Ref=db.getReference("Coordinates");
        Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                coordinateModelList.clear();

                    for (DataSnapshot item : dataSnapshot.getChildren()) {
                        cmodel = item.getValue(CoordinateModel.class);
                        coordinateModelList.add(cmodel);

                    }



                rvMain.setLayoutManager(new GridLayoutManager(getContext(),2, LinearLayoutManager.VERTICAL,false));
                rvMain.setHasFixedSize(true);
                rvMain.setAdapter(new ItemListAdapter(coordinateModelList));
            }

                @Override
                public void onCancelled (@NonNull DatabaseError databaseError){

                }

        });







        return v;
    }


}
