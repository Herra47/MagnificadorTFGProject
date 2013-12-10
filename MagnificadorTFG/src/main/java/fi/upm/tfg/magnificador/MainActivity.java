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

        switch (color){
            case BLACKWHITE:
                contrastBlackWhite();
                break;
            case WHITEBLACK:
                contrastWhiteBlack();
                break;
            case BLACKYELLOW:
                contrastBlackYellow();
                break;
            case YELLOWBLACK:
                contrastYellowBlack();
                break;
            case BLUEWHITE:
                contrastBlueWhite();
                break;
            case WHITEBLUE:
                contrastWhiteBlue();
                break;
            case BLUEYELLOW:
                contrastBlueYellow();
                break;
            case YELLOWBLUE:
                contrastYellowBlue();
                break;
            case REDWHITE:
                contrastRedWhite();
                break;
            case WHITERED:
                contrastWhiteRed();
                break;
            case REDYELLOW:
                contrastRedYellow();
                break;
            case YELLOWRED:
                contrastYellowRed();
                break;
        }

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

        ColorStateList text = getResources().getColorStateList(R.color.text_black_white);
        int white = getResources().getColor(R.color.White);
        int background = R.drawable.button_black_white;

        buttonMode.setTextColor(text);
        buttonColors.setTextColor(text);
        buttonCamera.setTextColor(text);
        buttonSettings.setTextColor(text);
        buttonMode.setBackgroundResource(background);
        buttonColors.setBackgroundResource(background);
        buttonCamera.setBackgroundResource(background);
        buttonSettings.setBackgroundResource(background);

        findViewById(R.id.mainLayout).setBackgroundColor(white);

        //color = Colors.BLACKWHITE;
        //Menu1ButtonDrawerActivity.setColor(color);
        //SettingsActivity.setColor(color);
    }

    private void contrastWhiteBlack (){

        buttonMode = (Button) findViewById(R.id.modeButton);
        buttonColors = (Button) findViewById(R.id.colorsButton);
        buttonCamera = (Button) findViewById(R.id.cameraButton);
        buttonSettings = (Button) findViewById(R.id.settingsButton);

        ColorStateList textBW = getResources().getColorStateList(R.color.text_white_black);
        int black = getResources().getColor(R.color.Black);
        int background = R.drawable.button_white_black;

        buttonMode.setTextColor(textBW);
        buttonColors.setTextColor(textBW);
        buttonCamera.setTextColor(textBW);
        buttonSettings.setTextColor(textBW);
        buttonMode.setBackgroundResource(background);
        buttonColors.setBackgroundResource(background);
        buttonCamera.setBackgroundResource(background);
        buttonSettings.setBackgroundResource(background);

        findViewById(R.id.mainLayout).setBackgroundColor(black);

        //color = Colors.WHITEBLACK;
        //Menu1ButtonDrawerActivity.setColor(color);
        //SettingsActivity.setColor(color);
    }

    private void contrastBlackYellow (){

        buttonMode = (Button) findViewById(R.id.modeButton);
        buttonColors = (Button) findViewById(R.id.colorsButton);
        buttonCamera = (Button) findViewById(R.id.cameraButton);
        buttonSettings = (Button) findViewById(R.id.settingsButton);

        ColorStateList textBW = getResources().getColorStateList(R.color.text_black_yellow);
        int color = getResources().getColor(R.color.Yellow);
        int background = R.drawable.button_black_yellow;

        buttonMode.setTextColor(textBW);
        buttonColors.setTextColor(textBW);
        buttonCamera.setTextColor(textBW);
        buttonSettings.setTextColor(textBW);
        buttonMode.setBackgroundResource(background);
        buttonColors.setBackgroundResource(background);
        buttonCamera.setBackgroundResource(background);
        buttonSettings.setBackgroundResource(background);

        findViewById(R.id.mainLayout).setBackgroundColor(color);

        /*color = Colors.BLACKWHITE;
        Menu1ButtonDrawerActivity.setColor(color);
        SettingsActivity.setColor(color);*/
    }

    private void contrastYellowBlack (){

        buttonMode = (Button) findViewById(R.id.modeButton);
        buttonColors = (Button) findViewById(R.id.colorsButton);
        buttonCamera = (Button) findViewById(R.id.cameraButton);
        buttonSettings = (Button) findViewById(R.id.settingsButton);

        ColorStateList textBW = getResources().getColorStateList(R.color.text_yellow_black);
        int black = getResources().getColor(R.color.Black);
        int white = getResources().getColor(R.color.White);
        int background = R.drawable.button_yellow_black;

        buttonMode.setTextColor(textBW);
        buttonColors.setTextColor(textBW);
        buttonCamera.setTextColor(textBW);
        buttonSettings.setTextColor(textBW);
        buttonMode.setBackgroundResource(background);
        buttonColors.setBackgroundResource(background);
        buttonCamera.setBackgroundResource(background);
        buttonSettings.setBackgroundResource(background);

        findViewById(R.id.mainLayout).setBackgroundColor(black);

        /*color = Colors.BLACKWHITE;
        Menu1ButtonDrawerActivity.setColor(color);
        SettingsActivity.setColor(color);*/
    }

    private void contrastBlueWhite (){

        buttonMode = (Button) findViewById(R.id.modeButton);
        buttonColors = (Button) findViewById(R.id.colorsButton);
        buttonCamera = (Button) findViewById(R.id.cameraButton);
        buttonSettings = (Button) findViewById(R.id.settingsButton);

        ColorStateList textBW = getResources().getColorStateList(R.color.text_blue_white);
        int color = getResources().getColor(R.color.White);
        int background = R.drawable.button_blue_white;

        buttonMode.setTextColor(textBW);
        buttonColors.setTextColor(textBW);
        buttonCamera.setTextColor(textBW);
        buttonSettings.setTextColor(textBW);
        buttonMode.setBackgroundResource(background);
        buttonColors.setBackgroundResource(background);
        buttonCamera.setBackgroundResource(background);
        buttonSettings.setBackgroundResource(background);

        findViewById(R.id.mainLayout).setBackgroundColor(color);

        /*color = Colors.BLACKWHITE;
        Menu1ButtonDrawerActivity.setColor(color);
        SettingsActivity.setColor(color);*/
    }

    private void contrastWhiteBlue (){

        buttonMode = (Button) findViewById(R.id.modeButton);
        buttonColors = (Button) findViewById(R.id.colorsButton);
        buttonCamera = (Button) findViewById(R.id.cameraButton);
        buttonSettings = (Button) findViewById(R.id.settingsButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_white_blue);
        int color = getResources().getColor(R.color.Blue);
        int background = R.drawable.button_white_blue;

        buttonMode.setTextColor(text);
        buttonColors.setTextColor(text);
        buttonCamera.setTextColor(text);
        buttonSettings.setTextColor(text);
        buttonMode.setBackgroundResource(background);
        buttonColors.setBackgroundResource(background);
        buttonCamera.setBackgroundResource(background);
        buttonSettings.setBackgroundResource(background);

        findViewById(R.id.mainLayout).setBackgroundColor(color);
    }

    private void contrastBlueYellow (){

        buttonMode = (Button) findViewById(R.id.modeButton);
        buttonColors = (Button) findViewById(R.id.colorsButton);
        buttonCamera = (Button) findViewById(R.id.cameraButton);
        buttonSettings = (Button) findViewById(R.id.settingsButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_blue_yellow);
        int color = getResources().getColor(R.color.Yellow);
        int background = R.drawable.button_blue_yellow;

        buttonMode.setTextColor(text);
        buttonColors.setTextColor(text);
        buttonCamera.setTextColor(text);
        buttonSettings.setTextColor(text);
        buttonMode.setBackgroundResource(background);
        buttonColors.setBackgroundResource(background);
        buttonCamera.setBackgroundResource(background);
        buttonSettings.setBackgroundResource(background);

        findViewById(R.id.mainLayout).setBackgroundColor(color);
    }

    private void contrastYellowBlue (){

        buttonMode = (Button) findViewById(R.id.modeButton);
        buttonColors = (Button) findViewById(R.id.colorsButton);
        buttonCamera = (Button) findViewById(R.id.cameraButton);
        buttonSettings = (Button) findViewById(R.id.settingsButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_yellow_blue);
        int color = getResources().getColor(R.color.Blue);
        int background = R.drawable.button_yellow_blue;

        buttonMode.setTextColor(text);
        buttonColors.setTextColor(text);
        buttonCamera.setTextColor(text);
        buttonSettings.setTextColor(text);
        buttonMode.setBackgroundResource(background);
        buttonColors.setBackgroundResource(background);
        buttonCamera.setBackgroundResource(background);
        buttonSettings.setBackgroundResource(background);

        findViewById(R.id.mainLayout).setBackgroundColor(color);
    }

    private void contrastRedWhite (){

        buttonMode = (Button) findViewById(R.id.modeButton);
        buttonColors = (Button) findViewById(R.id.colorsButton);
        buttonCamera = (Button) findViewById(R.id.cameraButton);
        buttonSettings = (Button) findViewById(R.id.settingsButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_red_white);
        int color = getResources().getColor(R.color.White);
        int background = R.drawable.button_red_white;

        buttonMode.setTextColor(text);
        buttonColors.setTextColor(text);
        buttonCamera.setTextColor(text);
        buttonSettings.setTextColor(text);
        buttonMode.setBackgroundResource(background);
        buttonColors.setBackgroundResource(background);
        buttonCamera.setBackgroundResource(background);
        buttonSettings.setBackgroundResource(background);

        findViewById(R.id.mainLayout).setBackgroundColor(color);
    }

    private void contrastWhiteRed (){

        buttonMode = (Button) findViewById(R.id.modeButton);
        buttonColors = (Button) findViewById(R.id.colorsButton);
        buttonCamera = (Button) findViewById(R.id.cameraButton);
        buttonSettings = (Button) findViewById(R.id.settingsButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_white_red);
        int color = getResources().getColor(R.color.Red);
        int background = R.drawable.button_white_red;

        buttonMode.setTextColor(text);
        buttonColors.setTextColor(text);
        buttonCamera.setTextColor(text);
        buttonSettings.setTextColor(text);
        buttonMode.setBackgroundResource(background);
        buttonColors.setBackgroundResource(background);
        buttonCamera.setBackgroundResource(background);
        buttonSettings.setBackgroundResource(background);

        findViewById(R.id.mainLayout).setBackgroundColor(color);
    }

    private void contrastRedYellow (){

        buttonMode = (Button) findViewById(R.id.modeButton);
        buttonColors = (Button) findViewById(R.id.colorsButton);
        buttonCamera = (Button) findViewById(R.id.cameraButton);
        buttonSettings = (Button) findViewById(R.id.settingsButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_red_yellow);
        int color = getResources().getColor(R.color.Yellow);
        int background = R.drawable.button_red_yellow;

        buttonMode.setTextColor(text);
        buttonColors.setTextColor(text);
        buttonCamera.setTextColor(text);
        buttonSettings.setTextColor(text);
        buttonMode.setBackgroundResource(background);
        buttonColors.setBackgroundResource(background);
        buttonCamera.setBackgroundResource(background);
        buttonSettings.setBackgroundResource(background);

        findViewById(R.id.mainLayout).setBackgroundColor(color);
    }

    private void contrastYellowRed() {

        buttonMode = (Button) findViewById(R.id.modeButton);
        buttonColors = (Button) findViewById(R.id.colorsButton);
        buttonCamera = (Button) findViewById(R.id.cameraButton);
        buttonSettings = (Button) findViewById(R.id.settingsButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_yellow_red);
        int color = getResources().getColor(R.color.Red);
        int background = R.drawable.button_yellow_red;

        buttonMode.setTextColor(text);
        buttonColors.setTextColor(text);
        buttonCamera.setTextColor(text);
        buttonSettings.setTextColor(text);
        buttonMode.setBackgroundResource(background);
        buttonColors.setBackgroundResource(background);
        buttonCamera.setBackgroundResource(background);
        buttonSettings.setBackgroundResource(background);

        findViewById(R.id.mainLayout).setBackgroundColor(color);
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