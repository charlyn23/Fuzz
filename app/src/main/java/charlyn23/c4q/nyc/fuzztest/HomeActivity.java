package charlyn23.c4q.nyc.fuzztest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebViewFragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ListView navDrawer;
    private String[] fragmentTitles;
    private Fragment fragment;
    private ActionBarDrawerToggle drawerToggle;

    final DataGetter dataGetter = new DataGetter();
    WebViewFragment webViewFragment = new WebViewFragment();
    FullContentFragment fullContentFragment = new FullContentFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new Fragment())
                    .commit();
        }

        fragmentTitles = getResources().getStringArray(R.array.fragmentTitles);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navDrawer = (ListView) findViewById(R.id.navDrawer);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, fragmentTitles);
        navDrawer.setAdapter(adapter);
        navDrawer.setOnItemClickListener(new DrawerItemClickListener());


    }

    //Create click listener for list
    public class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    public void selectItem(int position) {
        if (position == 0) {
            fragment = new HomeFragment();
        } else if (position == 1) {
            fragment = new FullContentFragment();
        } else if (position == 2) {
            fragment = new TextFragment();
        } else if (position == 3) {
            fragment = new PictureFragment();
        }


        //Fragment Manager to handle the fragments that will appear in the fragment conainter in
        //Main's layout. Remember, there's one activity and its frag-container will host
        //all of the fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

        //Once an item is selected in drawerlist, close the drawer
        navDrawer.setItemChecked(position, true);
        drawerLayout.closeDrawer(navDrawer);


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void swapViews() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void addShowHideListener(FullContentAdapter.ViewHolder holder, final Fragment fragment) {

        final View.OnClickListener textListener = null;
        new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(android.R.animator.fade_in,
                        android.R.animator.fade_out);
                if (webViewFragment.isHidden()) {
                    ft.show(webViewFragment);
                } else {
                    ft.hide(webViewFragment);
                }
                ft.commit();
                FullContentAdapter.holder.date.setOnClickListener(textListener);
                FullContentAdapter.holder.data.setOnClickListener(textListener);
                FullContentAdapter.holder.id.setOnClickListener(textListener);
                Toast.makeText(getApplicationContext(), "listener works", Toast.LENGTH_SHORT).show();
            }


        };

    }
}

