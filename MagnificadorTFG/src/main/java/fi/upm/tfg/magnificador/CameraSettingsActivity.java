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

import fi.upm.tfg.enums.Colors;

public class CameraSettingsActivity extends Activity {

    private static Colors color = Colors.BLACKWHITE;
    private static boolean menu1Button = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_settings);

        if(menu1Button){
            startActivity(new Intent(CameraSettingsActivity.this,CameraSettings1ButtonActivity.class));
            finish();
        }

        ColorStateList text = getResources().getColorStateList(R.color.text_black_white);
        int backColor = getResources().getColor(R.color.White);
        int background = R.drawable.button_black_white;

        switch (color){
            case BLACKWHITE:
                text = getResources().getColorStateList(R.color.text_black_white);
                backColor = getResources().getColor(R.color.White);
                background = R.drawable.button_black_white;
                break;
            case WHITEBLACK:
                text = getResources().getColorStateList(R.color.text_white_black);
                backColor = getResources().getColor(R.color.Black);
                background = R.drawable.button_white_black;
                break;
            case BLACKYELLOW:
                text = getResources().getColorStateList(R.color.text_black_yellow);
                backColor = getResources().getColor(R.color.Yellow);
                background = R.drawable.button_black_yellow;
                break;
            case YELLOWBLACK:
                text = getResources().getColorStateList(R.color.text_yellow_black);
                backColor = getResources().getColor(R.color.Black);
                background = R.drawable.button_yellow_black;
                break;
            case BLUEWHITE:
                text = getResources().getColorStateList(R.color.text_blue_white);
                backColor = getResources().getColor(R.color.White);
                background = R.drawable.button_blue_white;
                break;
            case WHITEBLUE:
                text = getResources().getColorStateList(R.color.text_white_blue);
                backColor = getResources().getColor(R.color.Blue);
                background = R.drawable.button_white_blue;
                break;
            case BLUEYELLOW:
                text = getResources().getColorStateList(R.color.text_blue_yellow);
                backColor = getResources().getColor(R.color.Yellow);
                background = R.drawable.button_blue_yellow;
                break;
            case YELLOWBLUE:
                text = getResources().getColorStateList(R.color.text_yellow_blue);
                backColor = getResources().getColor(R.color.Blue);
                background = R.drawable.button_yellow_blue;
                break;
            case REDWHITE:
                text = getResources().getColorStateList(R.color.text_red_white);
                backColor = getResources().getColor(R.color.White);
                background = R.drawable.button_red_white;
                break;
            case WHITERED:
                text = getResources().getColorStateList(R.color.text_white_red);
                backColor = getResources().getColor(R.color.Red);
                background = R.drawable.button_white_red;
                break;
            case REDYELLOW:
                text = getResources().getColorStateList(R.color.text_red_yellow);
                backColor = getResources().getColor(R.color.Yellow);
                background = R.drawable.button_red_yellow;
                break;
            case YELLOWRED:
                text = getResources().getColorStateList(R.color.text_yellow_red);
                backColor = getResources().getColor(R.color.Red);
                background = R.drawable.button_yellow_red;
                break;
        }
        contrast(text,backColor,background);

        final Button flashButton = (Button) findViewById(R.id.flashButton);
        final Button macroButton = (Button) findViewById(R.id.macroButton);
        final Button stabButton = (Button) findViewById(R.id.stabButton);

        if(MagnificadorActivity.getFlashed()){
            flashButton.setText("Apagar Flash");
        }
        else{
            flashButton.setText("Encender Flash");
        }

        if(MagnificadorActivity.isMACRO()){
            macroButton.setText("Apagar Macro");
        }
        else{
            macroButton.setText("Encender Macro");
        }

        if(MagnificadorActivity.isSTAB()){
            stabButton.setText("Apagar Estabilizador");
        }
        else{
            stabButton.setText("Encender Estabilizador");
        }

        flashButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(MagnificadorActivity.getFlashed()){
                    MagnificadorActivity.setFlashed(false);
                    Intent intent = new Intent(CameraSettingsActivity.this, MagnificadorActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    MagnificadorActivity.setFlashed(true);
                    Intent intent = new Intent(CameraSettingsActivity.this, MagnificadorActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        macroButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(MagnificadorActivity.isMACRO()){
                    MagnificadorActivity.setMACRO(false);
                    Intent intent = new Intent(CameraSettingsActivity.this, MagnificadorActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    MagnificadorActivity.setMACRO(true);
                    Intent intent = new Intent(CameraSettingsActivity.this, MagnificadorActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        stabButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(MagnificadorActivity.isSTAB()){
                    MagnificadorActivity.setSTAB(false);
                    Intent intent = new Intent(CameraSettingsActivity.this, MagnificadorActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    MagnificadorActivity.setSTAB(true);
                    Intent intent = new Intent(CameraSettingsActivity.this, MagnificadorActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.camera_settings, menu);
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
                startActivity(new Intent(CameraSettingsActivity.this, MainActivity.class));
                finish();
        }
        return super.onKeyUp(keyCode, event);
    }

    private void contrast(ColorStateList text, int color, int background){

        Button flash = (Button) findViewById(R.id.flashButton);
        Button macro = (Button) findViewById(R.id.macroButton);
        Button stab = (Button) findViewById(R.id.stabButton);

        flash.setTextColor(text);
        macro.setTextColor(text);
        stab.setTextColor(text);
        flash.setBackgroundResource(background);
        macro.setBackgroundResource(background);
        stab.setBackgroundResource(background);

        findViewById(R.id.mainLayout).setBackgroundColor(color);
    }

    public static Colors getColor() {
        return color;
    }

    public static void setColor(Colors color) {
        CameraSettingsActivity.color = color;
    }

    public static boolean isMenu1Button() {
        return menu1Button;
    }

    public static void setMenu1Button(boolean menu1Button) {
        CameraSettingsActivity.menu1Button = menu1Button;
    }
}
