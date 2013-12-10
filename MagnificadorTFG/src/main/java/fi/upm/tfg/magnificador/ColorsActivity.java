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

public class ColorsActivity extends Activity {

    private static Colors color = Colors.BLACKWHITE;
    private static boolean menu1Button = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        if(menu1Button){
            startActivity(new Intent(ColorsActivity.this,Colors1ButtonActivity.class));
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

        final Button brightColorsButton = (Button) findViewById(R.id.brighColorsButton);
        final Button darkColorsButton = (Button) findViewById(R.id.darkColorsButton);

        brightColorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ColorsActivity.this,BrightBackActivity.class);
                startActivity(intent);
                finish();
            }
        });

        darkColorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ColorsActivity.this,DarkBackActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.colors, menu);
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
                startActivity(new Intent(ColorsActivity.this, MainActivity.class));
                finish();
        }
        return super.onKeyUp(keyCode, event);
    }

    private void contrastBlackWhite (){

        Button bright = (Button) findViewById(R.id.brighColorsButton);
        Button dark = (Button) findViewById(R.id.darkColorsButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_black_white);
        int color = getResources().getColor(R.color.White);
        int background = R.drawable.button_black_white;

        bright.setTextColor(text);
        dark.setTextColor(text);
        bright.setBackgroundResource(background);
        dark.setBackgroundResource(background);

        findViewById(R.id.colorsLayout).setBackgroundColor(color);
    }

    private void contrastWhiteBlack (){

        Button bright = (Button) findViewById(R.id.brighColorsButton);
        Button dark = (Button) findViewById(R.id.darkColorsButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_white_black);
        int color = getResources().getColor(R.color.Black);
        int background = R.drawable.button_white_black;

        bright.setTextColor(text);
        dark.setTextColor(text);
        bright.setBackgroundResource(background);
        dark.setBackgroundResource(background);

        findViewById(R.id.colorsLayout).setBackgroundColor(color);
    }

    private void contrastBlackYellow (){

        Button bright = (Button) findViewById(R.id.brighColorsButton);
        Button dark = (Button) findViewById(R.id.darkColorsButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_black_yellow);
        int color = getResources().getColor(R.color.Yellow);
        int background = R.drawable.button_black_yellow;

        bright.setTextColor(text);
        dark.setTextColor(text);
        bright.setBackgroundResource(background);
        dark.setBackgroundResource(background);

        findViewById(R.id.colorsLayout).setBackgroundColor(color);
    }

    private void contrastYellowBlack (){

        Button bright = (Button) findViewById(R.id.brighColorsButton);
        Button dark = (Button) findViewById(R.id.darkColorsButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_yellow_black);
        int color = getResources().getColor(R.color.Black);
        int background = R.drawable.button_yellow_black;

        bright.setTextColor(text);
        dark.setTextColor(text);
        bright.setBackgroundResource(background);
        dark.setBackgroundResource(background);

        findViewById(R.id.colorsLayout).setBackgroundColor(color);
    }

    private void contrastBlueWhite (){

        Button bright = (Button) findViewById(R.id.brighColorsButton);
        Button dark = (Button) findViewById(R.id.darkColorsButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_blue_white);
        int color = getResources().getColor(R.color.White);
        int background = R.drawable.button_blue_white;

        bright.setTextColor(text);
        dark.setTextColor(text);
        bright.setBackgroundResource(background);
        dark.setBackgroundResource(background);

        findViewById(R.id.colorsLayout).setBackgroundColor(color);
    }

    private void contrastWhiteBlue (){

        Button bright = (Button) findViewById(R.id.brighColorsButton);
        Button dark = (Button) findViewById(R.id.darkColorsButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_white_blue);
        int color = getResources().getColor(R.color.Blue);
        int background = R.drawable.button_white_blue;

        bright.setTextColor(text);
        dark.setTextColor(text);
        bright.setBackgroundResource(background);
        dark.setBackgroundResource(background);

        findViewById(R.id.colorsLayout).setBackgroundColor(color);
    }

    private void contrastBlueYellow (){

        Button bright = (Button) findViewById(R.id.brighColorsButton);
        Button dark = (Button) findViewById(R.id.darkColorsButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_blue_yellow);
        int color = getResources().getColor(R.color.Yellow);
        int background = R.drawable.button_blue_yellow;

        bright.setTextColor(text);
        dark.setTextColor(text);
        bright.setBackgroundResource(background);
        dark.setBackgroundResource(background);

        findViewById(R.id.colorsLayout).setBackgroundColor(color);
    }

    private void contrastYellowBlue (){

        Button bright = (Button) findViewById(R.id.brighColorsButton);
        Button dark = (Button) findViewById(R.id.darkColorsButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_yellow_blue);
        int color = getResources().getColor(R.color.Blue);
        int background = R.drawable.button_yellow_blue;

        bright.setTextColor(text);
        dark.setTextColor(text);
        bright.setBackgroundResource(background);
        dark.setBackgroundResource(background);

        findViewById(R.id.colorsLayout).setBackgroundColor(color);
    }

    private void contrastRedWhite (){

        Button bright = (Button) findViewById(R.id.brighColorsButton);
        Button dark = (Button) findViewById(R.id.darkColorsButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_red_white);
        int color = getResources().getColor(R.color.White);
        int background = R.drawable.button_red_white;

        bright.setTextColor(text);
        dark.setTextColor(text);
        bright.setBackgroundResource(background);
        dark.setBackgroundResource(background);

        findViewById(R.id.colorsLayout).setBackgroundColor(color);
    }

    private void contrastWhiteRed (){

        Button bright = (Button) findViewById(R.id.brighColorsButton);
        Button dark = (Button) findViewById(R.id.darkColorsButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_white_red);
        int color = getResources().getColor(R.color.Red);
        int background = R.drawable.button_white_red;

        bright.setTextColor(text);
        dark.setTextColor(text);
        bright.setBackgroundResource(background);
        dark.setBackgroundResource(background);

        findViewById(R.id.colorsLayout).setBackgroundColor(color);
    }

    private void contrastRedYellow (){

        Button bright = (Button) findViewById(R.id.brighColorsButton);
        Button dark = (Button) findViewById(R.id.darkColorsButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_red_yellow);
        int color = getResources().getColor(R.color.Yellow);
        int background = R.drawable.button_red_yellow;

        bright.setTextColor(text);
        dark.setTextColor(text);
        bright.setBackgroundResource(background);
        dark.setBackgroundResource(background);

        findViewById(R.id.colorsLayout).setBackgroundColor(color);
    }

    private void contrastYellowRed() {

        Button bright = (Button) findViewById(R.id.brighColorsButton);
        Button dark = (Button) findViewById(R.id.darkColorsButton);

        ColorStateList text = getResources().getColorStateList(R.color.text_yellow_red);
        int color = getResources().getColor(R.color.Red);
        int background = R.drawable.button_yellow_red;

        bright.setTextColor(text);
        dark.setTextColor(text);
        bright.setBackgroundResource(background);
        dark.setBackgroundResource(background);

        findViewById(R.id.colorsLayout).setBackgroundColor(color);
    }

    public static boolean isMenu1Button() {
        return menu1Button;
    }

    public static void setMenu1Button(boolean menu1Button) {
        ColorsActivity.menu1Button = menu1Button;
    }

    public static Colors getColor() {
        return color;
    }

    public static void setColor(Colors color) {
        ColorsActivity.color = color;
    }
}
