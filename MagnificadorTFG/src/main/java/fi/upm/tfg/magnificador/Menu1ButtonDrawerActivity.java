package fi.upm.tfg.magnificador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.Menu;

import fi.upm.tfg.enums.Colors;
import fi.upm.tfg.transformers.ZoomOutPageTransformer;

public class Menu1ButtonDrawerActivity extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
     * will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private static SectionsPagerAdapter mSectionsPagerAdapter;

    private static Colors color = Colors.BLACKWHITE;
    private static boolean menu1Button = false;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private static ViewPager mViewPager;

    public static void setColor(Colors color) {
        Menu1ButtonDrawerActivity.color = color;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_drawer);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mSectionsPagerAdapter.addFragment(DummySectionFragment.newInstance(R.string.button_modes, 0, color));
        mSectionsPagerAdapter.addFragment(DummySectionFragment.newInstance(R.string.button_colors, 1, color));
        mSectionsPagerAdapter.addFragment(DummySectionFragment.newInstance(R.string.button_camera, 2, color));
        mSectionsPagerAdapter.addFragment(DummySectionFragment.newInstance(R.string.button_settings, 3, color));

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());

    }

    public static ViewPager getViewPager(){
        return mViewPager;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu1_button_drawer, menu);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(Menu1ButtonDrawerActivity.this, MagnificadorActivity.class);
            //Comenzamos la nueva actividad
            startActivity(intent);
            finish();
        }
        return true;
    }
}
