package com.example.mapexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mapexample.Adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tlMain;
    ViewPager vpMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tlMain=findViewById(R.id.tlMain);
        vpMain=findViewById(R.id.vpMain);
        vpMain.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
vpMain.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlMain));
getSupportActionBar().setTitle("Add Coordinates");
tlMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
vpMain.setCurrentItem(tab.getPosition());
String title="";
switch (tab.getPosition())
{
    case 0:
        title="Add Coordinates";
        break;
    case 1:
        title="Manage Coordinates";


}
getSupportActionBar().setTitle(title);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.map:
                Intent i=new Intent(MainActivity.this,Search.class);
                startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
