package com.jp3dr0.estoquelaboratorio.crud.read;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jp3dr0.estoquelaboratorio.crud.create.CreateReagenteActivity;
import com.jp3dr0.estoquelaboratorio.crud.create.CreateVidrariaActivity;
import com.jp3dr0.estoquelaboratorio.R;
import com.jp3dr0.estoquelaboratorio.repository.Api;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public TabLayout tabLayout;
    public Toolbar toolbar;
    public Toolbar toolbar3;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    public static String currentFragment = "Vidraria";

    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabs);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Tela Principal");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        configurarNavigationDrawer(navigationView, drawer);

        toolbar3 = (Toolbar) findViewById(R.id.toolbar3);
        toolbar3.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return true;
            }
        });

        toolbar3.findViewById(R.id.iv_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Iniciar camera", Toast.LENGTH_SHORT).show();
            }
        });

        configurarViewPager();
    }

    public void configurarViewPager(){
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // Here's your instance
                //Fragment fragment =(Fragment)mSectionsPagerAdapter.getRegisteredFragment(position);
                currentFragment = currentFragment.equals("Vidraria") ? "Reagente" : "Vidraria";
                Toast.makeText(MainActivity.this, "swipe - current fragment: " + currentFragment, Toast.LENGTH_SHORT).show();
                /*
                switch (position){
                    case 0:
                        currentFragment = "Vidraria";
                        Toast.makeText(MainActivity.this, "currentFragment - MainActivity: " + currentFragment, Toast.LENGTH_SHORT).show();
                    case 1:
                        currentFragment = "Reagente";
                        Toast.makeText(MainActivity.this, "currentFragment - MainActivity: " + currentFragment, Toast.LENGTH_SHORT).show();
                }
                */
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void onClickAdicionarItem(View v){
        Toast.makeText(MainActivity.this, "Adicionar item", Toast.LENGTH_SHORT).show();
        int position = mViewPager.getCurrentItem();

        if (currentFragment.equals("Vidraria")){
            Intent it = new Intent(getApplicationContext(), CreateVidrariaActivity.class);
            startActivity(it);
        }
        else if (currentFragment.equals("Reagente")){
            Intent it = new Intent(getApplicationContext(), CreateReagenteActivity.class);
            startActivity(it);
        }
    }

    private void configurarNavigationDrawer(NavigationView navigationView, DrawerLayout drawer){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart()", Toast.LENGTH_SHORT).show();

        int position = mViewPager.getCurrentItem();
        //Fragment fragment =(Fragment)mSectionsPagerAdapter.getRegisteredFragment(position);

        if (currentFragment.equals("Vidraria")){
            VidrariaFragment instanceVidrariaFragment = (VidrariaFragment) mSectionsPagerAdapter.getRegisteredFragment(position);
            instanceVidrariaFragment.carregarItens();
        }
        else if (currentFragment.equals("Reagente")){
            ReagenteFragment instanceReagenteFragment = (ReagenteFragment) mSectionsPagerAdapter.getRegisteredFragment(position);
            instanceReagenteFragment.carregarItens();
        }

        //MyclassFragment instanceFragment= (MyclassFragment)getSupportFragmentManager().findFragmentById(R.id.idFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                //finish();
                break;
            case R.id.action_search:
                //finish();
                break;
            case R.id.action_change_recycler:
                int position = mViewPager.getCurrentItem();
                //Fragment fragment =(Fragment)mSectionsPagerAdapter.getRegisteredFragment(position);

                if (currentFragment.equals("Vidraria")){
                    VidrariaFragment instanceVidrariaFragment = (VidrariaFragment) mSectionsPagerAdapter.getRegisteredFragment(position);
                    instanceVidrariaFragment.trocarView();
                    if (instanceVidrariaFragment.isTrocarView()) {
                        item.setIcon(com.jp3dr0.estoquelaboratorio.R.drawable.ic_view_quilt_black_24dp);
                    }
                    else {
                        item.setIcon(com.jp3dr0.estoquelaboratorio.R.drawable.ic_view_stream_black_24dp);
                    }
                }
                else if (currentFragment.equals("Reagente")){
                    ReagenteFragment instanceReagenteFragment = (ReagenteFragment) mSectionsPagerAdapter.getRegisteredFragment(position);
                    instanceReagenteFragment.trocarView();
                    if (instanceReagenteFragment.isTrocarView()) {
                        item.setIcon(com.jp3dr0.estoquelaboratorio.R.drawable.ic_view_quilt_black_24dp);
                    }
                    else {
                        item.setIcon(com.jp3dr0.estoquelaboratorio.R.drawable.ic_view_stream_black_24dp);
                    }
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SparseArray<Fragment> registeredFragments = new SparseArray<>();

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            registeredFragments.put(position, fragment);
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            registeredFragments.remove(position);
            super.destroyItem(container, position, object);
        }

        public Fragment getRegisteredFragment(int position) {
            return registeredFragments.get(position);
        }

        public SectionsPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            String escolha = new String();
            String trocarview;
            Fragment frag = null;

            switch (position){
                case 0:
                    frag = new VidrariaFragment();
                    break;
                case 1:
                    frag = new ReagenteFragment();
            }
            /*
            if (position == 0)
                escolha = "vidraria";
            else
                escolha = "reagente";
                //return new StaticFragment();
                //return PlaceholderFragment.newInstance(position + 1);

            RootFragment frag = new RootFragment();
            Bundle bundleObject = new Bundle();
            bundleObject.putString("escolha", escolha);
            frag.setArguments(bundleObject);
            */
            return frag;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "um";
                case 1:
                    return "dois";
            }
            return null;
        }
    }
}

