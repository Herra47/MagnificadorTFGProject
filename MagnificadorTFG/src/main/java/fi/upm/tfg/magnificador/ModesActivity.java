package fi.upm.tfg.magnificador;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;

import fi.upm.tfg.enums.CameraColors;

public class ModesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modes);

        final Button buttonRgb = (Button) findViewById(R.id.rgbButton);
        final Button buttonGray = (Button) findViewById(R.id.grayButton);
        final Button buttonInvert = (Button) findViewById(R.id.invertButton);
        final Button buttonHighContrast = (Button) findViewById(R.id.highContrastButton);

        buttonRgb.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.RGB);
                //Creamos el Intent
                Intent intent = new Intent(ModesActivity.this,MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });

        buttonGray.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.GRAY);
                //Creamos el Intent
                Intent intent = new Intent(ModesActivity.this,MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });

        buttonInvert.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.INVERT);
                Intent intent = new Intent(ModesActivity.this,MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });

        buttonHighContrast.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.HIGHCONTRAST);
                //Creamos el Intent
                Intent intent = new Intent(ModesActivity.this,MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.modes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                startActivity(new Intent(ModesActivity.this, MainActivity.class));
                finish();
        }
        return super.onKeyUp(keyCode, event);
    }

}
