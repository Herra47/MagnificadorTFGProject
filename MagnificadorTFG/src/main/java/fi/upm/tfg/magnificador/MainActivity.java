package fi.upm.tfg.magnificador;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import fi.upm.tfg.enums.Colors;

public class MainActivity extends Activity {

    private Button buttonMode;
    private Button buttonColors;
    private Button buttonCamera;
    private Button buttonSettings;

    private static Colors color = Colors.BLACKWHITE;
    private static boolean menu1Button = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(menu1Button){
            startActivity(new Intent(MainActivity.this,Menu1ButtonDrawerActivity.class));
            finish();
        }

        /*switch (color){
            case BLACKWHITE:
                contrastBlackWhite();
                break;
            case YELLOWBLUE:
                contrastYellowBlue();
                break;
            case YELLOWRED:
                contrastYellowRed();
                break;
        }*/

        final Button buttonMode = (Button) findViewById(R.id.modeButton);
        final Button buttonColors = (Button) findViewById(R.id.colorsButton);
        final Button buttonCamera = (Button) findViewById(R.id.cameraButton);
        final Button buttonSettings = (Button) findViewById(R.id.settingsButton);

        buttonMode.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent = new Intent(MainActivity.this,ModesActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });

        buttonColors.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent = new Intent(MainActivity.this, ColorsActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });

        buttonCamera.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CameraSettingsActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(MainActivity.this, MagnificadorActivity.class);
            //Comenzamos la nueva actividad
            startActivity(intent);
            finish();
        }
        return true;
    }

    private void contrastBlackWhite (){

        buttonMode = (Button) findViewById(R.id.modeButton);
        buttonColors = (Button) findViewById(R.id.colorsButton);
        buttonCamera = (Button) findViewById(R.id.cameraButton);
        buttonSettings = (Button) findViewById(R.id.settingsButton);

        ColorStateList textBW = getResources().getColorStateList(R.color.text_bw);
        int black = getResources().getColor(R.color.Black);
        int white = getResources().getColor(R.color.White);
        int background = R.drawable.button_bw;

        buttonMode.setTextColor(textBW);
        buttonColors.setTextColor(textBW);
        buttonCamera.setTextColor(textBW);
        buttonSettings.setTextColor(textBW);
        buttonMode.setBackgroundResource(background);
        buttonColors.setBackgroundResource(background);
        buttonCamera.setBackgroundResource(background);
        buttonSettings.setBackgroundResource(background);

        findViewById(R.id.mainLayout).setBackgroundColor(white);

        color = Colors.BLACKWHITE;
        Menu1ButtonDrawerActivity.setColor(color);
        SettingsActivity.setColor(color);
    }

    private void contrastYellowBlue (){

        buttonMode = (Button) findViewById(R.id.modeButton);
        buttonColors = (Button) findViewById(R.id.colorsButton);
        buttonCamera = (Button) findViewById(R.id.cameraButton);
        buttonSettings = (Button) findViewById(R.id.settingsButton);

        int yellow = getResources().getColor(R.color.Yellow);
        int blue = getResources().getColor(R.color.Blue);
        ColorStateList textBY = getResources().getColorStateList(R.color.text_yb);
        int background = R.drawable.button_yb;

        buttonMode.setTextColor(textBY);
        buttonColors.setTextColor(textBY);
        buttonCamera.setTextColor(textBY);
        buttonSettings.setTextColor(textBY);
        buttonMode.setBackgroundResource(background);
        buttonColors.setBackgroundResource(background);
        buttonCamera.setBackgroundResource(background);
        buttonSettings.setBackgroundResource(background);

        findViewById(R.id.mainLayout).setBackgroundColor(blue);

        color = Colors.YELLOWBLUE;
        Menu1ButtonDrawerActivity.setColor(color);
        SettingsActivity.setColor(color);
    }

    private void contrastYellowRed() {

        buttonMode = (Button) findViewById(R.id.modeButton);
        buttonColors = (Button) findViewById(R.id.colorsButton);
        buttonCamera = (Button) findViewById(R.id.cameraButton);
        buttonSettings = (Button) findViewById(R.id.settingsButton);

        int yellow = getResources().getColor(R.color.Yellow);
        int red = getResources().getColor(R.color.Red);
        ColorStateList textRY = getResources().getColorStateList(R.color.text_yr);
        int background = R.drawable.button_yr;

        buttonMode.setTextColor(textRY);
        buttonColors.setTextColor(textRY);
        buttonCamera.setTextColor(textRY);
        buttonSettings.setTextColor(textRY);
        buttonMode.setBackgroundResource(background);
        buttonColors.setBackgroundResource(background);
        buttonCamera.setBackgroundResource(background);
        buttonSettings.setBackgroundResource(background);

        findViewById(R.id.mainLayout).setBackgroundColor(red);

        color = Colors.YELLOWRED;
        Menu1ButtonDrawerActivity.setColor(color);
        SettingsActivity.setColor(color);
    }

    public Colors getColor() {
        return color;
    }

    public static void setColor(Colors color1) {
        color = color1;
    }

    public static boolean isMenu1Button() {
        return menu1Button;
    }

    public static void setMenu1Button(boolean menu1Button) {
        MainActivity.menu1Button = menu1Button;
    }
}