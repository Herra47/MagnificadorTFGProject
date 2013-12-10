package fi.upm.tfg.magnificador;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import fi.upm.tfg.enums.CameraColors;
import fi.upm.tfg.enums.Colors;

public class ModesActivity extends Activity {


    private static Colors color = Colors.BLACKWHITE;
    private static boolean menu1Button = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modes);

        if(menu1Button){
            startActivity(new Intent(ModesActivity.this,Modes1ButtonActivity.class));
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
                finish();
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

    private void contrastBlackWhite (){

        Button normal = (Button) findViewById(R.id.rgbButton);
        Button gray = (Button) findViewById(R.id.grayButton);
        Button invert = (Button) findViewById(R.id.invertButton);
        Button highContrast = (Button) findViewById(R.id.highContrastButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_black_white);
        int color = getResources().getColor(R.color.White);
        int background = R.drawable.button_black_white;

        normal.setTextColor(text);
        gray.setTextColor(text);
        invert.setTextColor(text);
        highContrast.setTextColor(text);
        normal.setBackgroundResource(background);
        gray.setBackgroundResource(background);
        invert.setBackgroundResource(background);
        highContrast.setBackgroundResource(background);

        findViewById(R.id.modesLayout).setBackgroundColor(color);
    }

    private void contrastWhiteBlack (){

        Button normal = (Button) findViewById(R.id.rgbButton);
        Button gray = (Button) findViewById(R.id.grayButton);
        Button invert = (Button) findViewById(R.id.invertButton);
        Button highContrast = (Button) findViewById(R.id.highContrastButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_white_black);
        int color = getResources().getColor(R.color.Black);
        int background = R.drawable.button_white_black;

        normal.setTextColor(text);
        gray.setTextColor(text);
        invert.setTextColor(text);
        highContrast.setTextColor(text);
        normal.setBackgroundResource(background);
        gray.setBackgroundResource(background);
        invert.setBackgroundResource(background);
        highContrast.setBackgroundResource(background);

        findViewById(R.id.modesLayout).setBackgroundColor(color);
    }

    private void contrastBlackYellow (){

        Button normal = (Button) findViewById(R.id.rgbButton);
        Button gray = (Button) findViewById(R.id.grayButton);
        Button invert = (Button) findViewById(R.id.invertButton);
        Button highContrast = (Button) findViewById(R.id.highContrastButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_black_yellow);
        int color = getResources().getColor(R.color.Yellow);
        int background = R.drawable.button_black_yellow;

        normal.setTextColor(text);
        gray.setTextColor(text);
        invert.setTextColor(text);
        highContrast.setTextColor(text);
        normal.setBackgroundResource(background);
        gray.setBackgroundResource(background);
        invert.setBackgroundResource(background);
        highContrast.setBackgroundResource(background);

        findViewById(R.id.modesLayout).setBackgroundColor(color);
    }

    private void contrastYellowBlack (){

        Button normal = (Button) findViewById(R.id.rgbButton);
        Button gray = (Button) findViewById(R.id.grayButton);
        Button invert = (Button) findViewById(R.id.invertButton);
        Button highContrast = (Button) findViewById(R.id.highContrastButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_yellow_black);
        int color = getResources().getColor(R.color.Black);
        int background = R.drawable.button_yellow_black;

        normal.setTextColor(text);
        gray.setTextColor(text);
        invert.setTextColor(text);
        highContrast.setTextColor(text);
        normal.setBackgroundResource(background);
        gray.setBackgroundResource(background);
        invert.setBackgroundResource(background);
        highContrast.setBackgroundResource(background);

        findViewById(R.id.modesLayout).setBackgroundColor(color);
    }

    private void contrastBlueWhite (){

        Button normal = (Button) findViewById(R.id.rgbButton);
        Button gray = (Button) findViewById(R.id.grayButton);
        Button invert = (Button) findViewById(R.id.invertButton);
        Button highContrast = (Button) findViewById(R.id.highContrastButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_blue_white);
        int color = getResources().getColor(R.color.White);
        int background = R.drawable.button_blue_white;

        normal.setTextColor(text);
        gray.setTextColor(text);
        invert.setTextColor(text);
        highContrast.setTextColor(text);
        normal.setBackgroundResource(background);
        gray.setBackgroundResource(background);
        invert.setBackgroundResource(background);
        highContrast.setBackgroundResource(background);

        findViewById(R.id.modesLayout).setBackgroundColor(color);
    }

    private void contrastWhiteBlue (){

        Button normal = (Button) findViewById(R.id.rgbButton);
        Button gray = (Button) findViewById(R.id.grayButton);
        Button invert = (Button) findViewById(R.id.invertButton);
        Button highContrast = (Button) findViewById(R.id.highContrastButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_white_blue);
        int color = getResources().getColor(R.color.Blue);
        int background = R.drawable.button_white_blue;

        normal.setTextColor(text);
        gray.setTextColor(text);
        invert.setTextColor(text);
        highContrast.setTextColor(text);
        normal.setBackgroundResource(background);
        gray.setBackgroundResource(background);
        invert.setBackgroundResource(background);
        highContrast.setBackgroundResource(background);

        findViewById(R.id.modesLayout).setBackgroundColor(color);
    }

    private void contrastBlueYellow (){

        Button normal = (Button) findViewById(R.id.rgbButton);
        Button gray = (Button) findViewById(R.id.grayButton);
        Button invert = (Button) findViewById(R.id.invertButton);
        Button highContrast = (Button) findViewById(R.id.highContrastButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_blue_yellow);
        int color = getResources().getColor(R.color.Yellow);
        int background = R.drawable.button_blue_yellow;

        normal.setTextColor(text);
        gray.setTextColor(text);
        invert.setTextColor(text);
        highContrast.setTextColor(text);
        normal.setBackgroundResource(background);
        gray.setBackgroundResource(background);
        invert.setBackgroundResource(background);
        highContrast.setBackgroundResource(background);

        findViewById(R.id.modesLayout).setBackgroundColor(color);
    }

    private void contrastYellowBlue (){

        Button normal = (Button) findViewById(R.id.rgbButton);
        Button gray = (Button) findViewById(R.id.grayButton);
        Button invert = (Button) findViewById(R.id.invertButton);
        Button highContrast = (Button) findViewById(R.id.highContrastButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_yellow_blue);
        int color = getResources().getColor(R.color.Blue);
        int background = R.drawable.button_yellow_blue;

        normal.setTextColor(text);
        gray.setTextColor(text);
        invert.setTextColor(text);
        highContrast.setTextColor(text);
        normal.setBackgroundResource(background);
        gray.setBackgroundResource(background);
        invert.setBackgroundResource(background);
        highContrast.setBackgroundResource(background);

        findViewById(R.id.modesLayout).setBackgroundColor(color);
    }

    private void contrastRedWhite (){

        Button normal = (Button) findViewById(R.id.rgbButton);
        Button gray = (Button) findViewById(R.id.grayButton);
        Button invert = (Button) findViewById(R.id.invertButton);
        Button highContrast = (Button) findViewById(R.id.highContrastButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_red_white);
        int color = getResources().getColor(R.color.White);
        int background = R.drawable.button_red_white;

        normal.setTextColor(text);
        gray.setTextColor(text);
        invert.setTextColor(text);
        highContrast.setTextColor(text);
        normal.setBackgroundResource(background);
        gray.setBackgroundResource(background);
        invert.setBackgroundResource(background);
        highContrast.setBackgroundResource(background);

        findViewById(R.id.modesLayout).setBackgroundColor(color);
    }

    private void contrastWhiteRed (){

        Button normal = (Button) findViewById(R.id.rgbButton);
        Button gray = (Button) findViewById(R.id.grayButton);
        Button invert = (Button) findViewById(R.id.invertButton);
        Button highContrast = (Button) findViewById(R.id.highContrastButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_white_red);
        int color = getResources().getColor(R.color.Red);
        int background = R.drawable.button_white_red;

        normal.setTextColor(text);
        gray.setTextColor(text);
        invert.setTextColor(text);
        highContrast.setTextColor(text);
        normal.setBackgroundResource(background);
        gray.setBackgroundResource(background);
        invert.setBackgroundResource(background);
        highContrast.setBackgroundResource(background);

        findViewById(R.id.modesLayout).setBackgroundColor(color);
    }

    private void contrastRedYellow (){

        Button normal = (Button) findViewById(R.id.rgbButton);
        Button gray = (Button) findViewById(R.id.grayButton);
        Button invert = (Button) findViewById(R.id.invertButton);
        Button highContrast = (Button) findViewById(R.id.highContrastButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_red_yellow);
        int color = getResources().getColor(R.color.Yellow);
        int background = R.drawable.button_red_yellow;

        normal.setTextColor(text);
        gray.setTextColor(text);
        invert.setTextColor(text);
        highContrast.setTextColor(text);
        normal.setBackgroundResource(background);
        gray.setBackgroundResource(background);
        invert.setBackgroundResource(background);
        highContrast.setBackgroundResource(background);

        findViewById(R.id.modesLayout).setBackgroundColor(color);
    }

    private void contrastYellowRed() {

        Button normal = (Button) findViewById(R.id.rgbButton);
        Button gray = (Button) findViewById(R.id.grayButton);
        Button invert = (Button) findViewById(R.id.invertButton);
        Button highContrast = (Button) findViewById(R.id.highContrastButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_yellow_red);
        int color = getResources().getColor(R.color.Red);
        int background = R.drawable.button_yellow_red;

        normal.setTextColor(text);
        gray.setTextColor(text);
        invert.setTextColor(text);
        highContrast.setTextColor(text);
        normal.setBackgroundResource(background);
        gray.setBackgroundResource(background);
        invert.setBackgroundResource(background);
        highContrast.setBackgroundResource(background);

        findViewById(R.id.modesLayout).setBackgroundColor(color);
    }

    public static boolean isMenu1Button() {
        return menu1Button;
    }

    public static void setMenu1Button(boolean menu1Button) {
        ModesActivity.menu1Button = menu1Button;
    }

    public static Colors getColor() {
        return color;
    }

    public static void setColor(Colors color) {
        ModesActivity.color = color;
    }
}
