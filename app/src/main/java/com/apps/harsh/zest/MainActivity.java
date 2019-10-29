package com.apps.harsh.zest;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.apps.harsh.zest.fragment.HomeFragment;
import com.apps.harsh.zest.fragment.ProfileFragment;
import com.apps.harsh.zest.fragment.SearchFragment;
import com.apps.harsh.zest.fragment.SportsFragment;
import com.apps.harsh.zest.fragment.OffersFragment;
import com.apps.harsh.zest.fragment.WebFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import de.cketti.mailto.EmailIntentBuilder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener, SportsFragment.OnFragmentInteractionListener, SearchFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener, OffersFragment.OnFragmentInteractionListener {

    private static final long RIPPLE_DURATION = 250;
    FrameLayout root;
    View contentHamburger;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private static final String TAG = "Register";
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = HomeFragment.newInstance();
                    getSupportActionBar().setTitle("ZEST 19");
                    break;
                case R.id.navigation_events:
                    fragment = SportsFragment.newInstance();
                    getSupportActionBar().setTitle("Score Board");
                    break;
                case R.id.navigation_sports:
                    fragment = OffersFragment.newInstance();
                    getSupportActionBar().setTitle("Offers");
                    break;
                case R.id.navigation_offers:
                    fragment = SearchFragment.newInstance();
                    getSupportActionBar().setTitle("Schedule");
                    break;
                case R.id.navigation_leaderboard:
                    fragment = ProfileFragment.newInstance();
                    getSupportActionBar().setTitle("Leader Board");
                    break;
            }
            if (fragment != null) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, fragment);
                fragmentTransaction.commit();
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, HomeFragment.newInstance());
        fragmentTransaction.commit();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FirebaseMessaging.getInstance().subscribeToTopic("all")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = getString(R.string.msg_subscribed);
                        if (!task.isSuccessful()) {
                            msg = getString(R.string.msg_subscribe_failed);
                        }
                        Log.d(TAG, msg);
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.contact) {
            Intent emailIntent = EmailIntentBuilder.from(this)
                    .to("web.zest19@gmail.com")
                    .build();
            startActivity(Intent.createChooser(emailIntent, "Choose an application :"));
        }
        else if (id == R.id.rate) {
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "Application not found!", Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.about) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        }
        else if (id == R.id.web) {
            startActivity(new Intent(MainActivity.this, WebFragment.class));
        }
        else if (id == R.id.privacy) {
            Uri uri = Uri.parse("https://harshshinde07.github.io/Zest-19-Privacy-Policy/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "Web browser not found!", Toast.LENGTH_LONG).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
