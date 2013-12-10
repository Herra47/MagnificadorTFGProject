package fi.upm.tfg.magnificador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import fi.upm.tfg.enums.CameraColors;
import fi.upm.tfg.enums.Colors;

public class DarkBackActivity extends Activity {

    private static Colors color = Colors.BLACKWHITE;
    private static boolean menu1Button = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dark_back);

        switch (color){
            case BLACKWHITE:
                findViewById(R.id.fondoOscuroLayout).setBackgroundColor(getResources().getColor(R.color.White));
                break;
            case WHITEBLACK:
                findViewById(R.id.fondoOscuroLayout).setBackgroundColor(getResources().getColor(R.color.Black));
                break;
            case BLACKYELLOW:
                findViewById(R.id.fondoOscuroLayout).setBackgroundColor(getResources().getColor(R.color.Yellow));
                break;
            case YELLOWBLACK:
                findViewById(R.id.fondoOscuroLayout).setBackgroundColor(getResources().getColor(R.color.Black));
                break;
            case BLUEWHITE:
                findViewById(R.id.fondoOscuroLayout).setBackgroundColor(getResources().getColor(R.color.White));
                break;
            case WHITEBLUE:
                findViewById(R.id.fondoOscuroLayout).setBackgroundColor(getResources().getColor(R.color.Blue));
                break;
            case BLUEYELLOW:
                findViewById(R.id.fondoOscuroLayout).setBackgroundColor(getResources().getColor(R.color.Yellow));
                break;
            case YELLOWBLUE:
                findViewById(R.id.fondoOscuroLayout).setBackgroundColor(getResources().getColor(R.color.Blue));
                break;
            case REDWHITE:
                findViewById(R.id.fondoOscuroLayout).setBackgroundColor(getResources().getColor(R.color.White));
                break;
            case WHITERED:
                findViewById(R.id.fondoOscuroLayout).setBackgroundColor(getResources().getColor(R.color.Red));
                break;
            case REDYELLOW:
                findViewById(R.id.fondoOscuroLayout).setBackgroundColor(getResources().getColor(R.color.Yellow));
                break;
            case YELLOWRED:
                findViewById(R.id.fondoOscuroLayout).setBackgroundColor(getResources().getColor(R.color.Red));
                break;
        }

        final Button blackWhiteButton = (Button) findViewById(R.id.blackWhiteButton);
        final Button blackYellowButton = (Button) findViewById(R.id.blackYellowButton);
        final Button blueWhiteButton = (Button) findViewById(R.id.blueWhiteButton);
        final Button blueYellowButton = (Button) findViewById(R.id.blueYellowButton);
        final Button redWhiteButton = (Button) findViewById(R.id.redWhiteButton);
        final Button redYellowButton = (Button) findViewById(R.id.redYellowButton);

        blackWhiteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.HIGHCONTRAST);
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
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.HIGHCONTRAST);
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
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.HIGHCONTRAST);
                MagnificadorActivity.setHIGH_CONTRAST_COLOR(CameraColors.BLUEANDWHITE);
                Intent intent = new Intent(DarkBackActivity.this, MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });

        blueYellowButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.HIGHCONTRAST);
                MagnificadorActivity.setHIGH_CONTRAST_COLOR(CameraColors.BLUEANDYELLOW);
                //Creamos el Intent
                Intent intent = new Intent(DarkBackActivity.this, MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });

        redWhiteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.HIGHCONTRAST);
                MagnificadorActivity.setHIGH_CONTRAST_COLOR(CameraColors.REDANDWHITE);
                //Creamos el Intent
                Intent intent = new Intent(DarkBackActivity.this, MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });

        redYellowButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.HIGHCONTRAST);
                MagnificadorActivity.setHIGH_CONTRAST_COLOR(CameraColors.REDANDYELLOW);
                //Creamos el Intent
                Intent intent = new Intent(DarkBackActivity.this, MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
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

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                startActivity(new Intent(DarkBackActivity.this, ColorsActivity.class));
                finish();
        }
        return super.onKeyUp(keyCode, event);
    }

    public static Colors getColor() {
        return color;
    }

    public static void setColor(Colors color) {
        DarkBackActivity.color = color;
    }
}
