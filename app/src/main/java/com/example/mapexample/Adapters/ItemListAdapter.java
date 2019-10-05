package com.example.mapexample.Adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mapexample.EditCoordinates;
import com.example.mapexample.MapsActivity;
import com.example.mapexample.Model.CoordinateModel;
import com.example.mapexample.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {
    List<CoordinateModel> coordinateModelList;

    public ItemListAdapter(List<CoordinateModel> coordinateModelList) {
        this.coordinateModelList = coordinateModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist,parent,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
holder.tvTitle.setText(coordinateModelList.get(position).getTitle());
holder.tvLatitude.setText(coordinateModelList.get(position).getLatitude()+" (Lat)");
holder.tvLongitude.setText(coordinateModelList.get(position).getLongitude()+" (Long)");
holder.btnEdit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(view.getContext(), EditCoordinates.class);
        Bundle b=new Bundle();
        b.putParcelable("OldData",coordinateModelList.get(position));
        i.putExtras(b);

        view.getContext().startActivity(i);
    }
});
holder.btnRemove.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(view.getContext());
        alertdialogbuilder.setMessage("Are you Sure You Want to Delete this Coordinate");
        alertdialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DeleteCoordinates(coordinateModelList.get(position).getTitle());
                coordinateModelList.remove(position);
                notifyDataSetChanged();
            }
        });
        alertdialogbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog=alertdialogbuilder.create();
        alertDialog.show();


    }

    private void DeleteCoordinates(String ID) {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference ref=database.getReference("Coordinates");
        ref.child(ID).removeValue();
    }
});
holder.btnShow.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(view.getContext(), MapsActivity.class);
        Bundle b=new Bundle();
        b.putParcelable("Coordinates",coordinateModelList.get(position));
        i.putExtras(b);
        view.getContext().startActivity(i);
    }
});
    }

    @Override
    public int getItemCount() {
        return coordinateModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvTitle,tvLatitude,tvLongitude;
        Button btnEdit,btnRemove,btnShow;

        public ViewHolder(@NonNull View v) {
            super(v);
tvTitle=v.findViewById(R.id.tvName);
tvLatitude=v.findViewById(R.id.tvLatitude);
tvLongitude=v.findViewById(R.id.tvLongitude);
btnEdit=v.findViewById(R.id.btnEdit);
btnRemove=v.findViewById(R.id.btnRemove);
btnShow=v.findViewById(R.id.btnShow);

        }
    }
}
