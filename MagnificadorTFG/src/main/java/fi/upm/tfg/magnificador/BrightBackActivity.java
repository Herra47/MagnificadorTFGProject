package fi.upm.tfg.magnificador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import fi.upm.tfg.enums.CameraColors;

public class BrightBackActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bright_back);

        final Button whiteBlackButton = (Button) findViewById(R.id.whiteBlackButton);
        final Button whiteBlueButton = (Button) findViewById(R.id.whiteBlueButton);
        final Button whiteRedButton = (Button) findViewById(R.id.whiteRedButton);
        final Button yellowBlackButton = (Button) findViewById(R.id.yellowBlackButton);
        final Button yellowBlueButton = (Button) findViewById(R.id.yellowBlueButton);
        final Button yellowRedButton = (Button) findViewById(R.id.yellowRedButton);

        whiteBlackButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setHIGH_CONTRAST_COLOR(CameraColors.WHITEANDBLACK);
                //Creamos el Intent
                Intent intent = new Intent(BrightBackActivity.this,MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });

        whiteBlueButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setHIGH_CONTRAST_COLOR(CameraColors.WHITEANDBLUE);
                //Creamos el Intent
                Intent intent = new Intent(BrightBackActivity.this, MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });

        whiteRedButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setHIGH_CONTRAST_COLOR(CameraColors.WHITEANDRED);
                Intent intent = new Intent(BrightBackActivity.this, MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });

        yellowBlackButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setHIGH_CONTRAST_COLOR(CameraColors.YELLOWANDBLACK);
                //Creamos el Intent
                Intent intent = new Intent(BrightBackActivity.this, MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
            }
        });

        yellowBlueButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setHIGH_CONTRAST_COLOR(CameraColors.YELLOWANDBLUE);
                //finish();
                //Creamos el Intent
                Intent intent = new Intent(BrightBackActivity.this, MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
            }
        });

        yellowRedButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setHIGH_CONTRAST_COLOR(CameraColors.YELLOWANDRED);
                //finish();
                //Creamos el Intent
                Intent intent = new Intent(BrightBackActivity.this, MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fondo_claro, menu);
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
