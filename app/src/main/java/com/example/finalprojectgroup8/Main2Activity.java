package com.example.finalprojectgroup8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;



public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

      NavigationView navigationView= findViewById(R.id.nav_view);
      navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences preferences = this.getSharedPreferences("com.example.finalprojectgroup8",Context.MODE_PRIVATE);

        Boolean userbool = preferences.getBoolean("asNanny",true);
        String status;
        if(userbool == true)
            status="Nanny";
        else
            status="Employer";

       String usernamesession=preferences.getString("username",null);
        String useremailsession = preferences.getString("email",null);
        View headerView = navigationView.getHeaderView(0);
        ImageView headimage = headerView.findViewById(R.id.photo);
        TextView headname = headerView.findViewById(R.id.hedname);
        TextView heademail = headerView.findViewById(R.id.hedemail);
        int pic=R.drawable.logo;
        headimage.setImageResource(pic);
        headname.setText(usernamesession+"   "+status);
        heademail.setText(useremailsession);
      HomeFragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment, "Home");
        fragmentTransaction.commit();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();
        if (id == R.id.home) {
            HomeFragment fragment = new HomeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment, "Home");
            fragmentTransaction.commit();
        }
        if (id == R.id.updt) {



            UpdateFragment fragment = new UpdateFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment, "z");
            fragmentTransaction.commit();

        }
        if (id == R.id.vp) {
            ViewFragment fragment = new ViewFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment, "View Profile");
            fragmentTransaction.commit();
        }
        else if (id == R.id.review) {
            ReviewFragment fragment = new ReviewFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment, "My Reviews");
            fragmentTransaction.commit();
        }
        else if (id == R.id.bstnanny) {
            BnannyFragment fragment = new BnannyFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment, "Best Nanny");
            fragmentTransaction.commit();
        } else if (id == R.id.bstemp) {
            BempFragment fragment = new BempFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment, "Best Employee");
            fragmentTransaction.commit();
        } else if (id == R.id.lgout) {
            LogoutFragment fragment = new LogoutFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment, "Logout fragment");
            fragmentTransaction.commit();
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }

    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }

    }


}
