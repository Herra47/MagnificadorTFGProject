package fi.upm.tfg.magnificador;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;

import fi.upm.tfg.enums.CameraColors;

public class DarkBackActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dark_back);

        final Button blackWhiteButton = (Button) findViewById(R.id.blackWhiteButton);
        final Button blackYellowButton = (Button) findViewById(R.id.blackYellowButton);
        final Button blueWhiteButton = (Button) findViewById(R.id.blueWhiteButton);
        final Button blueYellowButton = (Button) findViewById(R.id.blueYellowButton);
        final Button redWhiteButton = (Button) findViewById(R.id.redWhiteButton);
        final Button redYellowButton = (Button) findViewById(R.id.redYellowButton);

        blackWhiteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setHIGH_CONTRAST_COLOR(CameraColors.BLACKANDWHITE);
                //Creamos el Intent
                Intent intent = new Intent(DarkBackActivity.this,MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });

        blackYellowButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setHIGH_CONTRAST_COLOR(CameraColors.BLACKANDYELLOW);
                //Creamos el Intent
                Intent intent = new Intent(DarkBackActivity.this, MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });

        blueWhiteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setHIGH_CONTRAST_COLOR(CameraColors.BLUEANDWHITE);
                Intent intent = new Intent(DarkBackActivity.this, MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });

        blueYellowButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setHIGH_CONTRAST_COLOR(CameraColors.BLUEANDYELLOW);
                //Creamos el Intent
                Intent intent = new Intent(DarkBackActivity.this, MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
            }
        });

        redWhiteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setHIGH_CONTRAST_COLOR(CameraColors.REDANDWHITE);
                //Creamos el Intent
                Intent intent = new Intent(DarkBackActivity.this, MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
            }
        });

        redYellowButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setHIGH_CONTRAST_COLOR(CameraColors.REDANDYELLOW);
                //Creamos el Intent
                Intent intent = new Intent(DarkBackActivity.this, MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dark_back, menu);
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
}
