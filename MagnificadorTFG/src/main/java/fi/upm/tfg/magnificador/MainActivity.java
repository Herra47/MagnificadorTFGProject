package fi.upm.tfg.magnificador;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import fi.upm.tfg.enums.Colors;

public class MainActivity extends Activity {

    private Button button1Option;
    private Button buttonSettings;
    private Button buttonContrast;
    private Button buttonCamera;

    private static Colors color = Colors.BLACKWHITE;

    private static boolean mainActivity = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!mainActivity){
            startActivity(new Intent(MainActivity.this,Menu1ButtonDrawerActivity.class));
            finish();
        }

        switch (color){
            case BLACKWHITE:
                contrastBlackWhite();
                break;
            case YELLOWBLUE:
                contrastYellowBlue();
                break;
            case YELLOWRED:
                contrastYellowRed();
                break;
        }

        final Button button1Option = (Button) findViewById(R.id.menuButton);
        final Button buttonSettings = (Button) findViewById(R.id.settingsButton);
        final Button buttonContrast = (Button) findViewById(R.id.contrastButton);
        final Button buttonCamera = (Button) findViewById(R.id.cameraButton);

        button1Option.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setMainActivity(false);
                //Creamos el Intent
                Intent intent = new Intent(MainActivity.this,Menu1ButtonDrawerActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                SettingsActivity.setMenu4Options(true);
                //Creamos el Intent
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
                finish();
            }
        });

        buttonContrast.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                changeContrast();
            }
        });

        buttonCamera.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //finish();
                //Creamos el Intent
                Intent intent = new Intent(MainActivity.this, MagnificadorActivity.class);
                //Comenzamos la nueva actividad
                startActivity(intent);
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


    private void changeContrast (){

        switch (color){
            case BLACKWHITE:
                contrastYellowBlue();
                break;
            case YELLOWBLUE:
                contrastYellowRed();
                break;
            case YELLOWRED:
                contrastBlackWhite();
                break;
        }
    }

    private void contrastBlackWhite (){

        button1Option = (Button) findViewById(R.id.menuButton);
        buttonSettings = (Button) findViewById(R.id.settingsButton);
        buttonContrast = (Button) findViewById(R.id.contrastButton);
        buttonCamera = (Button) findViewById(R.id.cameraButton);

        ColorStateList textBW = getResources().getColorStateList(R.color.text_bw);
        int black = getResources().getColor(R.color.Black);
        int white = getResources().getColor(R.color.White);
        int background = R.drawable.button_bw;

        buttonContrast.setTextColor(black);
        buttonContrast.setBackgroundResource(R.drawable.borde);

        buttonSettings.setTextColor(textBW);
        button1Option.setTextColor(textBW);
        buttonCamera.setTextColor(textBW);
        buttonSettings.setBackgroundResource(background);
        button1Option.setBackgroundResource(background);
        buttonCamera.setBackgroundResource(background);

        findViewById(R.id.mainLayout).setBackgroundColor(white);

        color = Colors.BLACKWHITE;
        Menu1ButtonDrawerActivity.setColor(color);
        SettingsActivity.setColor(color);
    }

    private void contrastYellowBlue (){

        button1Option = (Button) findViewById(R.id.menuButton);
        buttonSettings = (Button) findViewById(R.id.settingsButton);
        buttonContrast = (Button) findViewById(R.id.contrastButton);
        buttonCamera = (Button) findViewById(R.id.cameraButton);

        int yellow = getResources().getColor(R.color.Yellow);
        int blue = getResources().getColor(R.color.Blue);
        ColorStateList textBY = getResources().getColorStateList(R.color.text_yb);
        int background = R.drawable.button_yb;

        buttonContrast.setTextColor(yellow);
        buttonContrast.setBackgroundResource(R.drawable.bordeyb);

        buttonSettings.setTextColor(textBY);
        buttonSettings.setBackgroundResource(background);
        button1Option.setTextColor(textBY);
        button1Option.setBackgroundResource(background);
        buttonCamera.setTextColor(textBY);
        buttonCamera.setBackgroundResource(background);

        findViewById(R.id.mainLayout).setBackgroundColor(blue);

        color = Colors.YELLOWBLUE;
        Menu1ButtonDrawerActivity.setColor(color);
        SettingsActivity.setColor(color);
    }

    private void contrastYellowRed() {

        button1Option = (Button) findViewById(R.id.menuButton);
        buttonSettings = (Button) findViewById(R.id.settingsButton);
        buttonContrast = (Button) findViewById(R.id.contrastButton);
        buttonCamera = (Button) findViewById(R.id.cameraButton);

        int yellow = getResources().getColor(R.color.Yellow);
        int red = getResources().getColor(R.color.Red);
        ColorStateList textRY = getResources().getColorStateList(R.color.text_yr);
        int background = R.drawable.button_yr;

        buttonContrast.setTextColor(yellow);
        buttonContrast.setBackgroundResource(R.drawable.bordeyr);

        buttonSettings.setTextColor(textRY);
        buttonSettings.setBackgroundResource(background);
        button1Option.setTextColor(textRY);
        button1Option.setBackgroundResource(background);
        buttonCamera.setTextColor(textRY);
        buttonCamera.setBackgroundResource(background);

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

    public static boolean isMainActivity() {
        return mainActivity;
    }

    public static void setMainActivity(boolean mainActivity) {
        MainActivity.mainActivity = mainActivity;
    }
}