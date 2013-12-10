package fi.upm.tfg.magnificador;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import fi.upm.tfg.enums.Colors;

public class SettingsActivity extends Activity {

    private static Colors color = Colors.BLACKWHITE;
    private int thresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button saveButton = (Button)findViewById(R.id.saveButton);

        final RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup);
        final RadioGroup rg2 = (RadioGroup)findViewById(R.id.radioGroup2);
        final TextView threshText = (TextView)findViewById(R.id.textViewNumber);

        switch (color){
            case BLACKWHITE:
                contrastBlackWhite();
                rg.check(R.id.radioButtonBW);
                break;
            case WHITEBLACK:
                contrastWhiteBlack();
                rg.check(R.id.radioButtonWB);
                break;
            case BLACKYELLOW:
                contrastBlackYellow();
                rg.check(R.id.radioButtonBlackY);
                break;
            case YELLOWBLACK:
                contrastYellowBlack();
                rg.check(R.id.radioButtonYBlack);
                break;
            case BLUEWHITE:
                contrastBlueWhite();
                rg.check(R.id.radioButtonBlueW);
                break;
            case WHITEBLUE:
                contrastWhiteBlue();
                rg.check(R.id.radioButtonWBlue);
                break;
            case BLUEYELLOW:
                contrastBlueYellow();
                rg.check(R.id.radioButtonBlueY);
                break;
            case YELLOWBLUE:
                contrastYellowBlue();
                rg.check(R.id.radioButtonYBlue);
                break;
            case REDWHITE:
                contrastRedWhite();
                rg.check(R.id.radioButtonRW);
                break;
            case WHITERED:
                contrastWhiteRed();
                rg.check(R.id.radioButtonWR);
                break;
            case REDYELLOW:
                contrastRedYellow();
                rg.check(R.id.radioButtonRY);
                break;
            case YELLOWRED:
                contrastYellowRed();
                rg.check(R.id.radioButtonYR);
                break;
        }

        if (MainActivity.isMenu1Button()){
            rg2.check(R.id.radioButtonOne);
        }
        else{
            rg2.check(R.id.radioButtonFour);
        }

        thresh = MagnificadorActivity.getTHRESH();
        threshText.setText(Integer.toString(thresh));

        final SeekBar seekbar = (SeekBar) findViewById(R.id.seekBar);
        seekbar.setMax(255);
        seekbar.setProgress(MagnificadorActivity.getTHRESH());

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){

                thresh = progress;
                threshText.setText(Integer.toString(thresh));
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                MagnificadorActivity.setTHRESH(thresh);

                int selectedColor = rg.getCheckedRadioButtonId();
                int selectedMenu = rg2.getCheckedRadioButtonId();

                switch (selectedColor){
                    case R.id.radioButtonBW:
                        color = Colors.BLACKWHITE;
                        break;
                    case R.id.radioButtonWB:
                        color = Colors.WHITEBLACK;
                        break;
                    case R.id.radioButtonBlackY:
                        color = Colors.BLACKYELLOW;
                        break;
                    case R.id.radioButtonYBlack:
                        color = Colors.YELLOWBLACK;
                        break;
                    case R.id.radioButtonBlueW:
                        color = Colors.BLUEWHITE;
                        break;
                    case R.id.radioButtonWBlue:
                        color = Colors.WHITEBLUE;
                        break;
                    case R.id.radioButtonBlueY:
                        color = Colors.BLUEYELLOW;
                        break;
                    case R.id.radioButtonYBlue:
                        color = Colors.YELLOWBLUE;
                        break;
                    case R.id.radioButtonRW:
                        color = Colors.REDWHITE;
                        break;
                    case R.id.radioButtonWR:
                        color = Colors.WHITERED;
                        break;
                    case R.id.radioButtonRY:
                        color = Colors.REDYELLOW;
                        break;
                    case R.id.radioButtonYR:
                        color = Colors.YELLOWRED;
                        break;
                }

                MainActivity.setColor(color);
                Menu1ButtonDrawerActivity.setColor(color);
                ModesActivity.setColor(color);
                ColorsActivity.setColor(color);
                CameraSettingsActivity.setColor(color);
                BrightBackActivity.setColor(color);
                DarkBackActivity.setColor(color);

                switch (selectedMenu){
                    case R.id.radioButtonFour:
                        MainActivity.setMenu1Button(false);
                        ModesActivity.setMenu1Button(false);
                        ColorsActivity.setMenu1Button(false);

                        break;
                    case R.id.radioButtonOne:
                        MainActivity.setMenu1Button(true);
                        ModesActivity.setMenu1Button(true);
                        ColorsActivity.setMenu1Button(true);
                        break;
                }
                startActivity(new Intent(SettingsActivity.this, MagnificadorActivity.class));
                finish();
            }
        });
    }

    private void contrastBlackWhite() {

        int black = getResources().getColor(R.color.Black);
        int white = getResources().getColor(R.color.White);

        RelativeLayout settingsLayout = (RelativeLayout) findViewById(R.id.settingsLayout);
        TextView settingsText = (TextView) findViewById(R.id.textView);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        TextView menu = (TextView) findViewById(R.id.textView2);
        RadioButton radioButtonOne = (RadioButton) findViewById(R.id.radioButtonOne);
        RadioButton radioButtonFour = (RadioButton) findViewById(R.id.radioButtonFour);
        RadioButton radioButtonBW = (RadioButton) findViewById(R.id.radioButtonBW);
        TextView brillo = (TextView) findViewById(R.id.textViewThresh);
        TextView numero = (TextView) findViewById(R.id.textViewNumber);

        brillo.setTextColor(black);
        numero.setTextColor(black);
        settingsLayout.setBackgroundColor(white);
        settingsText.setTextColor(black);
        menu.setTextColor(black);
        radioButtonOne.setTextColor(black);
        radioButtonFour.setTextColor(black);
        radioButtonBW.setBackgroundColor(white);

        ColorStateList text = getResources().getColorStateList(R.color.text_black_white);
        int background = R.drawable.button_black_white;

        saveButton.setTextColor(text);
        saveButton.setBackgroundResource(background);
    }

    private void contrastWhiteBlack() {

        int black = getResources().getColor(R.color.Black);
        int white = getResources().getColor(R.color.White);

        RelativeLayout settingsLayout = (RelativeLayout) findViewById(R.id.settingsLayout);
        TextView settingsText = (TextView) findViewById(R.id.textView);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        TextView menu = (TextView) findViewById(R.id.textView2);
        RadioButton radioButtonOne = (RadioButton) findViewById(R.id.radioButtonOne);
        RadioButton radioButtonFour = (RadioButton) findViewById(R.id.radioButtonFour);
        RadioButton radioButtonBW = (RadioButton) findViewById(R.id.radioButtonBW);
        TextView brillo = (TextView) findViewById(R.id.textViewThresh);
        TextView numero = (TextView) findViewById(R.id.textViewNumber);

        brillo.setTextColor(white);
        numero.setTextColor(white);
        settingsLayout.setBackgroundColor(black);
        settingsText.setTextColor(white);
        menu.setTextColor(white);
        radioButtonOne.setTextColor(white);
        radioButtonFour.setTextColor(white);
        radioButtonBW.setBackgroundColor(white);


        ColorStateList text = getResources().getColorStateList(R.color.text_white_black);
        int background = R.drawable.button_white_black;

        saveButton.setTextColor(text);
        saveButton.setBackgroundResource(background);
    }

    private void contrastBlackYellow() {

        int black = getResources().getColor(R.color.Black);
        int yellow = getResources().getColor(R.color.Yellow);
        int white = getResources().getColor(R.color.White);

        RelativeLayout settingsLayout = (RelativeLayout) findViewById(R.id.settingsLayout);
        TextView settingsText = (TextView) findViewById(R.id.textView);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        TextView menu = (TextView) findViewById(R.id.textView2);
        RadioButton radioButtonOne = (RadioButton) findViewById(R.id.radioButtonOne);
        RadioButton radioButtonFour = (RadioButton) findViewById(R.id.radioButtonFour);
        RadioButton radioButtonBW = (RadioButton) findViewById(R.id.radioButtonBW);
        TextView brillo = (TextView) findViewById(R.id.textViewThresh);
        TextView numero = (TextView) findViewById(R.id.textViewNumber);

        brillo.setTextColor(black);
        numero.setTextColor(black);
        settingsLayout.setBackgroundColor(yellow);
        settingsText.setTextColor(black);
        menu.setTextColor(black);
        radioButtonOne.setTextColor(black);
        radioButtonFour.setTextColor(black);
        radioButtonBW.setBackgroundColor(white);

        ColorStateList text = getResources().getColorStateList(R.color.text_black_yellow);
        int background = R.drawable.button_black_yellow;

        saveButton.setTextColor(text);
        saveButton.setBackgroundResource(background);
    }

    private void contrastYellowBlack() {

        int black = getResources().getColor(R.color.Black);
        int yellow = getResources().getColor(R.color.Yellow);
        int white = getResources().getColor(R.color.White);

        RelativeLayout settingsLayout = (RelativeLayout) findViewById(R.id.settingsLayout);
        TextView settingsText = (TextView) findViewById(R.id.textView);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        TextView menu = (TextView) findViewById(R.id.textView2);
        RadioButton radioButtonOne = (RadioButton) findViewById(R.id.radioButtonOne);
        RadioButton radioButtonFour = (RadioButton) findViewById(R.id.radioButtonFour);
        RadioButton radioButtonBW = (RadioButton) findViewById(R.id.radioButtonBW);
        TextView brillo = (TextView) findViewById(R.id.textViewThresh);
        TextView numero = (TextView) findViewById(R.id.textViewNumber);

        brillo.setTextColor(yellow);
        numero.setTextColor(yellow);
        settingsLayout.setBackgroundColor(black);
        settingsText.setTextColor(yellow);
        saveButton.setTextColor(yellow);
        menu.setTextColor(yellow);
        radioButtonOne.setTextColor(yellow);
        radioButtonFour.setTextColor(yellow);
        radioButtonBW.setBackgroundColor(white);

        ColorStateList text = getResources().getColorStateList(R.color.text_yellow_black);
        int background = R.drawable.button_yellow_black;

        saveButton.setTextColor(text);
        saveButton.setBackgroundResource(background);
    }

    private void contrastBlueWhite() {

        int blue = getResources().getColor(R.color.Blue);
        int white = getResources().getColor(R.color.White);

        RelativeLayout settingsLayout = (RelativeLayout) findViewById(R.id.settingsLayout);
        TextView settingsText = (TextView) findViewById(R.id.textView);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        TextView menu = (TextView) findViewById(R.id.textView2);
        RadioButton radioButtonOne = (RadioButton) findViewById(R.id.radioButtonOne);
        RadioButton radioButtonFour = (RadioButton) findViewById(R.id.radioButtonFour);
        RadioButton radioButtonBW = (RadioButton) findViewById(R.id.radioButtonBW);
        TextView brillo = (TextView) findViewById(R.id.textViewThresh);
        TextView numero = (TextView) findViewById(R.id.textViewNumber);

        brillo.setTextColor(blue);
        numero.setTextColor(blue);
        settingsLayout.setBackgroundColor(white);
        settingsText.setTextColor(blue);
        menu.setTextColor(blue);
        radioButtonOne.setTextColor(blue);
        radioButtonFour.setTextColor(blue);
        radioButtonBW.setBackgroundColor(white);

        ColorStateList text = getResources().getColorStateList(R.color.text_blue_white);
        int background = R.drawable.button_blue_white;

        saveButton.setTextColor(text);
        saveButton.setBackgroundResource(background);
    }

    private void contrastWhiteBlue() {

        int blue = getResources().getColor(R.color.Blue);
        int white = getResources().getColor(R.color.White);

        RelativeLayout settingsLayout = (RelativeLayout) findViewById(R.id.settingsLayout);
        TextView settingsText = (TextView) findViewById(R.id.textView);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        TextView menu = (TextView) findViewById(R.id.textView2);
        RadioButton radioButtonOne = (RadioButton) findViewById(R.id.radioButtonOne);
        RadioButton radioButtonFour = (RadioButton) findViewById(R.id.radioButtonFour);
        RadioButton radioButtonBW = (RadioButton) findViewById(R.id.radioButtonBW);
        TextView brillo = (TextView) findViewById(R.id.textViewThresh);
        TextView numero = (TextView) findViewById(R.id.textViewNumber);

        brillo.setTextColor(white);
        numero.setTextColor(white);
        settingsLayout.setBackgroundColor(blue);
        settingsText.setTextColor(white);
        menu.setTextColor(white);
        radioButtonOne.setTextColor(white);
        radioButtonFour.setTextColor(white);
        radioButtonBW.setBackgroundColor(white);

        ColorStateList text = getResources().getColorStateList(R.color.text_white_blue);
        int background = R.drawable.button_white_blue;

        saveButton.setTextColor(text);
        saveButton.setBackgroundResource(background);
    }

    private void contrastBlueYellow() {

        int blue = getResources().getColor(R.color.Blue);
        int yellow = getResources().getColor(R.color.Yellow);
        int white = getResources().getColor(R.color.White);

        RelativeLayout settingsLayout = (RelativeLayout) findViewById(R.id.settingsLayout);
        TextView settingsText = (TextView) findViewById(R.id.textView);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        TextView menu = (TextView) findViewById(R.id.textView2);
        RadioButton radioButtonOne = (RadioButton) findViewById(R.id.radioButtonOne);
        RadioButton radioButtonFour = (RadioButton) findViewById(R.id.radioButtonFour);
        RadioButton radioButtonBW = (RadioButton) findViewById(R.id.radioButtonBW);
        TextView brillo = (TextView) findViewById(R.id.textViewThresh);
        TextView numero = (TextView) findViewById(R.id.textViewNumber);

        brillo.setTextColor(blue);
        numero.setTextColor(blue);
        settingsLayout.setBackgroundColor(yellow);
        settingsText.setTextColor(blue);
        menu.setTextColor(blue);
        radioButtonOne.setTextColor(blue);
        radioButtonFour.setTextColor(blue);
        radioButtonBW.setBackgroundColor(white);

        ColorStateList text = getResources().getColorStateList(R.color.text_blue_yellow);
        int background = R.drawable.button_blue_yellow;

        saveButton.setTextColor(text);
        saveButton.setBackgroundResource(background);
    }

    private void contrastYellowBlue() {

        int blue = getResources().getColor(R.color.Blue);
        int yellow = getResources().getColor(R.color.Yellow);
        int white = getResources().getColor(R.color.White);

        RelativeLayout settingsLayout = (RelativeLayout) findViewById(R.id.settingsLayout);
        TextView settingsText = (TextView) findViewById(R.id.textView);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        TextView menu = (TextView) findViewById(R.id.textView2);
        RadioButton radioButtonOne = (RadioButton) findViewById(R.id.radioButtonOne);
        RadioButton radioButtonFour = (RadioButton) findViewById(R.id.radioButtonFour);
        RadioButton radioButtonBW = (RadioButton) findViewById(R.id.radioButtonBW);
        TextView brillo = (TextView) findViewById(R.id.textViewThresh);
        TextView numero = (TextView) findViewById(R.id.textViewNumber);

        brillo.setTextColor(yellow);
        numero.setTextColor(yellow);
        settingsLayout.setBackgroundColor(blue);
        settingsText.setTextColor(yellow);
        menu.setTextColor(yellow);
        radioButtonOne.setTextColor(yellow);
        radioButtonFour.setTextColor(yellow);
        radioButtonBW.setBackgroundColor(white);

        ColorStateList text = getResources().getColorStateList(R.color.text_yellow_blue);
        int background = R.drawable.button_yellow_blue;

        saveButton.setTextColor(text);
        saveButton.setBackgroundResource(background);
    }

    private void contrastRedWhite() {

        int red = getResources().getColor(R.color.Red);
        int white = getResources().getColor(R.color.White);

        RelativeLayout settingsLayout = (RelativeLayout) findViewById(R.id.settingsLayout);
        TextView settingsText = (TextView) findViewById(R.id.textView);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        TextView menu = (TextView) findViewById(R.id.textView2);
        RadioButton radioButtonOne = (RadioButton) findViewById(R.id.radioButtonOne);
        RadioButton radioButtonFour = (RadioButton) findViewById(R.id.radioButtonFour);
        RadioButton radioButtonBW = (RadioButton) findViewById(R.id.radioButtonBW);
        TextView brillo = (TextView) findViewById(R.id.textViewThresh);
        TextView numero = (TextView) findViewById(R.id.textViewNumber);

        brillo.setTextColor(red);
        numero.setTextColor(red);
        settingsLayout.setBackgroundColor(white);
        settingsText.setTextColor(red);
        menu.setTextColor(red);
        radioButtonOne.setTextColor(red);
        radioButtonFour.setTextColor(red);
        radioButtonBW.setBackgroundColor(white);

        ColorStateList text = getResources().getColorStateList(R.color.text_red_white);
        int background = R.drawable.button_red_white;

        saveButton.setTextColor(text);
        saveButton.setBackgroundResource(background);
    }

    private void contrastWhiteRed() {

        int red = getResources().getColor(R.color.Red);
        int white = getResources().getColor(R.color.White);

        RelativeLayout settingsLayout = (RelativeLayout) findViewById(R.id.settingsLayout);
        TextView settingsText = (TextView) findViewById(R.id.textView);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        TextView menu = (TextView) findViewById(R.id.textView2);
        RadioButton radioButtonOne = (RadioButton) findViewById(R.id.radioButtonOne);
        RadioButton radioButtonFour = (RadioButton) findViewById(R.id.radioButtonFour);
        RadioButton radioButtonBW = (RadioButton) findViewById(R.id.radioButtonBW);
        TextView brillo = (TextView) findViewById(R.id.textViewThresh);
        TextView numero = (TextView) findViewById(R.id.textViewNumber);

        brillo.setTextColor(white);
        numero.setTextColor(white);
        settingsLayout.setBackgroundColor(red);
        settingsText.setTextColor(white);
        menu.setTextColor(white);
        radioButtonOne.setTextColor(white);
        radioButtonFour.setTextColor(white);
        radioButtonBW.setBackgroundColor(white);

        ColorStateList text = getResources().getColorStateList(R.color.text_white_red);
        int background = R.drawable.button_white_red;

        saveButton.setTextColor(text);
        saveButton.setBackgroundResource(background);
    }

    private void contrastRedYellow() {

        int yellow = getResources().getColor(R.color.Yellow);
        int red = getResources().getColor(R.color.Red);
        int white = getResources().getColor(R.color.White);

        RelativeLayout settingsLayout = (RelativeLayout) findViewById(R.id.settingsLayout);
        TextView settingsText = (TextView) findViewById(R.id.textView);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        TextView menu = (TextView) findViewById(R.id.textView2);
        RadioButton radioButtonOne = (RadioButton) findViewById(R.id.radioButtonOne);
        RadioButton radioButtonFour = (RadioButton) findViewById(R.id.radioButtonFour);
        RadioButton radioButtonBW = (RadioButton) findViewById(R.id.radioButtonBW);
        TextView brillo = (TextView) findViewById(R.id.textViewThresh);
        TextView numero = (TextView) findViewById(R.id.textViewNumber);

        brillo.setTextColor(red);
        numero.setTextColor(red);
        settingsLayout.setBackgroundColor(yellow);
        settingsText.setTextColor(red);
        menu.setTextColor(red);
        radioButtonOne.setTextColor(red);
        radioButtonFour.setTextColor(red);
        radioButtonBW.setBackgroundColor(white);

        ColorStateList text = getResources().getColorStateList(R.color.text_red_yellow);
        int background = R.drawable.button_red_yellow;

        saveButton.setTextColor(text);
        saveButton.setBackgroundResource(background);
    }

    private void contrastYellowRed() {

        int yellow = getResources().getColor(R.color.Yellow);
        int red = getResources().getColor(R.color.Red);
        int white = getResources().getColor(R.color.White);

        RelativeLayout settingsLayout = (RelativeLayout) findViewById(R.id.settingsLayout);
        TextView settingsText = (TextView) findViewById(R.id.textView);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        TextView menu = (TextView) findViewById(R.id.textView2);
        RadioButton radioButtonOne = (RadioButton) findViewById(R.id.radioButtonOne);
        RadioButton radioButtonFour = (RadioButton) findViewById(R.id.radioButtonFour);
        RadioButton radioButtonBW = (RadioButton) findViewById(R.id.radioButtonBW);
        TextView brillo = (TextView) findViewById(R.id.textViewThresh);
        TextView numero = (TextView) findViewById(R.id.textViewNumber);

        brillo.setTextColor(yellow);
        numero.setTextColor(yellow);
        settingsLayout.setBackgroundColor(red);
        settingsText.setTextColor(yellow);
        menu.setTextColor(yellow);
        radioButtonOne.setTextColor(yellow);
        radioButtonFour.setTextColor(yellow);
        radioButtonBW.setBackgroundColor(white);

        ColorStateList text = getResources().getColorStateList(R.color.text_yellow_red);
        int background = R.drawable.button_yellow_red;

        saveButton.setTextColor(text);
        saveButton.setBackgroundResource(background);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
    switch(keyCode){
        case KeyEvent.KEYCODE_BACK:
            startActivity(new Intent(SettingsActivity.this, MainActivity.class));
            finish();
        }
        return super.onKeyUp(keyCode, event);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    public static void setColor(Colors color) {
        SettingsActivity.color = color;
    }
}
