package com.ba.presentation.beginning_android.demo;


import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;


import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import com.ba.presentation.beginning_android.demo.intents.IntentFragment;
import com.ba.presentation.beginning_android.demo.providers.ProviderFragment;
import com.ba.presentation.beginning_android.demo.receiver.ReceiverFragment;


public class MainActivity extends ActionBarActivity {

    private static final int DEFAULT_MENU_POSITION = 0;
    private DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private ListView mDrawerList;

    public static class DrawerMenuItem {

        public final String mName;
        public final Class<? extends Fragment> mFragmentClass;

        public DrawerMenuItem(String name, Class<? extends Fragment> fragmentClass) {
            mName = name;
            mFragmentClass = fragmentClass;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        if (savedInstanceState == null) {

        }

        // drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        ) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        final MainActivity.DrawerMenuItem[] menuItems = new MainActivity.DrawerMenuItem[] {
                new MainActivity.DrawerMenuItem(getString(R.string.mdrawer_item_intent), IntentFragment.class),
                new MainActivity.DrawerMenuItem(getString(R.string.mdrawer_item_receiver), ReceiverFragment.class),
                new MainActivity.DrawerMenuItem(getString(R.string.mdrawer_item_provider), ProviderFragment.class)
        };
        MenuAdapter adapter = new MenuAdapter(this, menuItems);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    MainActivity.this.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, menuItems[position].mFragmentClass.newInstance())
                            .commit();
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                mDrawerLayout.closeDrawers();
            }
        });
        mDrawerList.setAdapter(adapter);

        // show first fragment
        mDrawerList.performItemClick(
                mDrawerList.getAdapter().getView(DEFAULT_MENU_POSITION, null, null),
                DEFAULT_MENU_POSITION,
                mDrawerList.getAdapter().getItemId(DEFAULT_MENU_POSITION));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_1) {
            return true;
        } else if (id == R.id.action_2) {
            return true;
        } else if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
