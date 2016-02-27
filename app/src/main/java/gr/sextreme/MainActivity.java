package gr.sextreme;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import gr.sextreme.ekdoseis.DownloadPdfActivity;
import gr.sextreme.images.Image;
import gr.sextreme.organosi.OrganosiActivity;
import gr.sextreme.rss.NeaActivity;
import gr.sextreme.rss.NomosxediaActivity;
import gr.sextreme.video.LiveVideoActivity;
import gr.sextreme.video.LiveVideoDioActivity;
import gr.sextreme.vouli.EpitropesFragment;
import gr.sextreme.vouli.KtirioFragment;
import gr.sextreme.vouli.ProedreioFragment;
import gr.sextreme.vouli.SyntagmaFragment;
import gr.sextreme.vouli.ThesmosFragment;
import gr.sextreme.web.SindesmoiActivity;
import gr.sextreme.web.entos.DiktioActivity;
import gr.sextreme.youtube.IntentsTvActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        AppRate.app_launched(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_vouli) {
            Intent i = new Intent(MainActivity.this, MainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_organosi) {
            Intent i = new Intent(MainActivity.this, OrganosiActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_vouleutes) {
            Intent i = new Intent(MainActivity.this, OrganosiActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_sindesmoi) {
            Intent i = new Intent(MainActivity.this, SindesmoiActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_ekdoseis) {
            Intent i = new Intent(MainActivity.this, DownloadPdfActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_photo) {
            Intent i = new Intent(MainActivity.this, Image.class);
            startActivity(i);
        } else if (id == R.id.nav_kanali) {
            Intent i = new Intent(MainActivity.this, IntentsTvActivity.class);
            startActivity(i);
        }
        else if (id == R.id.nav_live_ena) {
            Intent i = new Intent(MainActivity.this, LiveVideoActivity.class);
            startActivity(i);
        }
        else if (id == R.id.nav_live_dio) {
            Intent i = new Intent(MainActivity.this, LiveVideoDioActivity.class);
            startActivity(i);
        }else if (id == R.id.nav_nea) {
            Intent i = new Intent(MainActivity.this, NeaActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_nomosxedia) {
            Intent i = new Intent(MainActivity.this, NomosxediaActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_diktio) {
            Intent i = new Intent(MainActivity.this, DiktioActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_epikoinonia) {
            Intent i = new Intent(MainActivity.this, EpikoinoniaActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_peri) {
            Intent i = new Intent(MainActivity.this, PeriActivity.class);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new ThesmosFragment(), getString(R.string.thesmos));
        adapter.addFrag(new SyntagmaFragment(), getString(R.string.syntagma));
        adapter.addFrag(new KtirioFragment(), getString(R.string.ktirio));
        adapter.addFrag(new ProedreioFragment(), getString(R.string.proedreio));
        adapter.addFrag(new EpitropesFragment(), getString(R.string.epitropes));
        viewPager.setAdapter(adapter);
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}