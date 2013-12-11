package fi.upm.tfg.magnificador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import fi.upm.tfg.enums.CameraColors;
import fi.upm.tfg.enums.Colors;

public class BrightBackActivity extends Activity {

    private static Colors color = Colors.BLACKWHITE;
    private static boolean menu1Button = false;
    private static boolean menuHC = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bright_back);


        switch (color){
            case BLACKWHITE:
            case BLUEWHITE:
            case REDWHITE:
                findViewById(R.id.fondoClaroLayout).setBackgroundColor(getResources().getColor(R.color.White));
                break;
            case BLACKYELLOW:
            case BLUEYELLOW:
            case REDYELLOW:
                findViewById(R.id.fondoClaroLayout).setBackgroundColor(getResources().getColor(R.color.Yellow));
                break;
            case WHITEBLACK:
            case YELLOWBLACK:
                findViewById(R.id.fondoClaroLayout).setBackgroundColor(getResources().getColor(R.color.Black));
                break;
            case WHITEBLUE:
            case YELLOWBLUE:
                findViewById(R.id.fondoClaroLayout).setBackgroundColor(getResources().getColor(R.color.Blue));
                break;
            case WHITERED:
            case YELLOWRED:
                findViewById(R.id.fondoClaroLayout).setBackgroundColor(getResources().getColor(R.color.Red));
                break;
        }


        final Button whiteBlackButton = (Button) findViewById(R.id.whiteBlackButton);
        final Button whiteBlueButton = (Button) findViewById(R.id.whiteBlueButton);
        final Button whiteRedButton = (Button) findViewById(R.id.whiteRedButton);
        final Button yellowBlackButton = (Button) findViewById(R.id.yellowBlackButton);
        final Button yellowBlueButton = (Button) findViewById(R.id.yellowBlueButton);
        final Button yellowRedButton = (Button) findViewById(R.id.yellowRedButton);

        whiteBlackButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.HIGHCONTRAST);
                MagnificadorActivity.setHIGH_CONTRAST_COLOR(CameraColors.WHITEANDBLACK);

                if(isMenuHC()){

                    color = Colors.BLACKWHITE;

                    MainActivity.setColor(color);
                    Menu1ButtonDrawerActivity.setColor(color);
                    ModesActivity.setColor(color);
                    ColorsActivity.setColor(color);
                    CameraSettingsActivity.setColor(color);
                    BrightBackActivity.setColor(color);
                    DarkBackActivity.setColor(color);
                    Modes1ButtonActivity.setColor(color);
                    Colors1ButtonActivity.setColor(color);
                    SettingsActivity.setColor(color);
                }
                //Creamos el Intent
                Intent intent = new Intent(BrightBackActivity.this,MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });

        whiteBlueButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.HIGHCONTRAST);
                MagnificadorActivity.setHIGH_CONTRAST_COLOR(CameraColors.WHITEANDBLUE);

                if(isMenuHC()){

                    color = Colors.BLUEWHITE;

                    MainActivity.setColor(color);
                    Menu1ButtonDrawerActivity.setColor(color);
                    ModesActivity.setColor(color);
                    ColorsActivity.setColor(color);
                    CameraSettingsActivity.setColor(color);
                    BrightBackActivity.setColor(color);
                    DarkBackActivity.setColor(color);
                    Modes1ButtonActivity.setColor(color);
                    Colors1ButtonActivity.setColor(color);
                    SettingsActivity.setColor(color);
                }
                //Creamos el Intent
                Intent intent = new Intent(BrightBackActivity.this, MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });

        whiteRedButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.HIGHCONTRAST);
                MagnificadorActivity.setHIGH_CONTRAST_COLOR(CameraColors.WHITEANDRED);

                if(isMenuHC()){

                    color = Colors.REDWHITE;

                    MainActivity.setColor(color);
                    Menu1ButtonDrawerActivity.setColor(color);
                    ModesActivity.setColor(color);
                    ColorsActivity.setColor(color);
                    CameraSettingsActivity.setColor(color);
                    BrightBackActivity.setColor(color);
                    DarkBackActivity.setColor(color);
                    Modes1ButtonActivity.setColor(color);
                    Colors1ButtonActivity.setColor(color);
                    SettingsActivity.setColor(color);
                }
                Intent intent = new Intent(BrightBackActivity.this, MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });

        yellowBlackButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.HIGHCONTRAST);
                MagnificadorActivity.setHIGH_CONTRAST_COLOR(CameraColors.YELLOWANDBLACK);

                if(isMenuHC()){

                    color = Colors.BLACKYELLOW;

                    MainActivity.setColor(color);
                    Menu1ButtonDrawerActivity.setColor(color);
                    ModesActivity.setColor(color);
                    ColorsActivity.setColor(color);
                    CameraSettingsActivity.setColor(color);
                    BrightBackActivity.setColor(color);
                    DarkBackActivity.setColor(color);
                    Modes1ButtonActivity.setColor(color);
                    Colors1ButtonActivity.setColor(color);
                    SettingsActivity.setColor(color);
                }
                //Creamos el Intent
                Intent intent = new Intent(BrightBackActivity.this, MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });

        yellowBlueButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.HIGHCONTRAST);
                MagnificadorActivity.setHIGH_CONTRAST_COLOR(CameraColors.YELLOWANDBLUE);

                if(isMenuHC()){

                    color = Colors.BLUEYELLOW;

                    MainActivity.setColor(color);
                    Menu1ButtonDrawerActivity.setColor(color);
                    ModesActivity.setColor(color);
                    ColorsActivity.setColor(color);
                    CameraSettingsActivity.setColor(color);
                    BrightBackActivity.setColor(color);
                    DarkBackActivity.setColor(color);
                    Modes1ButtonActivity.setColor(color);
                    Colors1ButtonActivity.setColor(color);
                    SettingsActivity.setColor(color);
                }
                //Creamos el Intent
                Intent intent = new Intent(BrightBackActivity.this, MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });

        yellowRedButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.HIGHCONTRAST);
                MagnificadorActivity.setHIGH_CONTRAST_COLOR(CameraColors.YELLOWANDRED);

                if(isMenuHC()){

                    color = Colors.REDYELLOW;

                    MainActivity.setColor(color);
                    Menu1ButtonDrawerActivity.setColor(color);
                    ModesActivity.setColor(color);
                    ColorsActivity.setColor(color);
                    CameraSettingsActivity.setColor(color);
                    BrightBackActivity.setColor(color);
                    DarkBackActivity.setColor(color);
                    Modes1ButtonActivity.setColor(color);
                    Colors1ButtonActivity.setColor(color);
                    SettingsActivity.setColor(color);
                }
                //Creamos el Intent
                Intent intent = new Intent(BrightBackActivity.this, MagnificadorActivity.class);

                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
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

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                startActivity(new Intent(BrightBackActivity.this, ColorsActivity.class));
                finish();
        }
        return super.onKeyUp(keyCode, event);
    }

    public static Colors getColor() {
        return color;
    }

    public static void setColor(Colors color) {
        BrightBackActivity.color = color;
    }

    public static boolean isMenuHC() {
        return menuHC;
    }

    public static void setMenuHC(boolean menuHC) {
        BrightBackActivity.menuHC = menuHC;
    }
}
