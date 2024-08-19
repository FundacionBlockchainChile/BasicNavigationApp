package com.example.basicnavigationbase;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavHostFragment host = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.my_nav_host_fragment);

        // Set up Action Bar
        NavController navController = host.getNavController();

        //TODO STEP 11  - Create an AppBarConfiguration with the correct top-level destinations
        // appBarConfiguration =  new AppBarConfiguration.Builder(navController.getGraph()).build();
        // You should also remove the old appBarConfiguration setup above
        appBarConfiguration = new AppBarConfiguration.Builder(
            R.id.home_dest, R.id.settingsFragment  // Specify the top-level destinations
        ).build();
        //END STEP 11

        setupActionBar(navController, appBarConfiguration);

        setupNavigationMenu(navController);

        setupBottomNavMenu(navController);
    }

    private void setupBottomNavMenu(NavController navController) {
        //TODO STEP 9 - Use NavigationUI to set up Bottom Nav
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_view);
        NavigationUI.setupWithNavController(bottomNav, navController);
        //END STEP 9
    }

    private void setupNavigationMenu(NavController navController) {
        //TODO STEP 10 - Use NavigationUI to set up a Navigation View
        NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView != null) {
            NavigationUI.setupWithNavController(navigationView, navController);
        } else {
            // Log error or handle the case where the navigation view is not available
            // Log.e("MainActivity", "NavigationView not found!");
        }
        //END STEP 10
    }

    private void setupActionBar(NavController navController,
                                AppBarConfiguration appBarConfig) {
        //TODO STEP 12 - Have NavigationUI handle what your ActionBar displays
        // This allows NavigationUI to decide what label to show in the action bar
        // By using appBarConfig, it will also determine whether to
        // show the up arrow or drawer menu icon
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig);
        //END STEP 12
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean retValue = super.onCreateOptionsMenu(menu);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // The NavigationView already has these same navigation items, so we only add
        // navigation items to the menu here if there isn't a NavigationView
        if (navigationView == null) {
            getMenuInflater().inflate(R.menu.overflow_menu, menu);
            return true;
        }
        return retValue;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TODO STEP 8 - Have Navigation UI Handle the item selection -
        // make sure to comment or delete the old return statement above
        // Have the NavigationUI look for an action or destination matching the menu
        // item id and navigate there if found.
        // Otherwise, bubble up to the parent.
        return NavigationUI.onNavDestinationSelected(item, Navigation.findNavController(this, R.id.my_nav_host_fragment))
                || super.onOptionsItemSelected(item);
        //END STEP 8
    }
    
    //TODO STEP 12  - Have NavigationUI handle up behavior in the ActionBar
    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.my_nav_host_fragment).navigateUp()
                || super.onSupportNavigateUp();
    }
    //END STEP 12

}
