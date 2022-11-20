package com.ardudrone.arnaldo.ctrldrone;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ardudrone.arnaldo.ctrldrone.database.dao.impl.tbConfiguracaoDao;
import com.ardudrone.arnaldo.ctrldrone.database.helpers.Configuracao;
import com.ardudrone.arnaldo.ctrldrone.drone.DroneListener;
import com.ardudrone.arnaldo.ctrldrone.fragments.ConfiguracaoFragment;
import com.ardudrone.arnaldo.ctrldrone.fragments.ControllerFragment;

public class CtrlActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;
    private Configuracao configuracao;
    private DroneListener droneListener;
    private DroneApp droneApp;
    	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctrl);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        droneApp = (DroneApp) this.getApplication();
        droneListener = DroneListener.GetInstance(this);
        configuracao = new Configuracao(droneApp);
        tbConfiguracaoDao.ConfiguracaoInitialize(droneApp);

        configuracao.Initialize();
        Configuracao.SetInstance(configuracao);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (position) {
            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, ControllerFragment.newInstance(position + 1))
                        .commit();
                break;
            case 1:
                droneListener.Conectar();
                droneListener.Start();
                
                fragmentManager.beginTransaction()
                        .replace(R.id.container, ControllerFragment.newInstance(position + 1))
                        .commit();
                break;
            case 2:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, ConfiguracaoFragment.newInstance(position + 1))
                        .commit();
                break;
            case 3:
            	droneListener.SendMsg("command=gyroscope_calibrate;");
                fragmentManager.beginTransaction()
                        .replace(R.id.container, ControllerFragment.newInstance(position + 1))
                        .commit();
                break;
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.ctrl, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
