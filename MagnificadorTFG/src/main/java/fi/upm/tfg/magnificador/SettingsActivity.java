package fi.upm.tfg.magnificador;

import android.app.Activity;
import android.content.Intent;
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
    private int keyCode;
    private KeyEvent event;
    private static boolean menu4Options;
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
            case YELLOWBLUE:
                contrastYellowBlue();
                rg.check(R.id.radioButtonBY);
                break;
            case YELLOWRED:
                contrastYellowRed();
                rg.check(R.id.radioButtonRY);
                break;
        }

        if (MainActivity.isMainActivity()){
            rg2.check(R.id.radioButtonFour);
        }
        else{
            rg2.check(R.id.radioButtonOne);
        }

        threshText.setText(Integer.toString(thresh));

        final SeekBar seekbar = (SeekBar) findViewById(R.id.seekBar);
        seekbar.setMax(255);

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

        //rg.clearCheck();
        //rg.check(R.id.radioButtonBW);
        //final int selectedId = rg.getCheckedRadioButtonId();

        saveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                int selectedColor = rg.getCheckedRadioButtonId();
                int selectedMenu = rg2.getCheckedRadioButtonId();

                switch (selectedColor){
                    case R.id.radioButtonBW:
                        MainActivity.setColor(Colors.BLACKWHITE);
                        Menu1ButtonDrawerActivity.setColor(Colors.BLACKWHITE);
                        SettingsActivity.setColor(Colors.BLACKWHITE);
                        break;
                    case R.id.radioButtonBY:
                        MainActivity.setColor(Colors.YELLOWBLUE);
                        Menu1ButtonDrawerActivity.setColor(Colors.YELLOWBLUE);
                        SettingsActivity.setColor(Colors.YELLOWBLUE);
                        break;
                    case R.id.radioButtonRY:
                        MainActivity.setColor(Colors.YELLOWRED);
                        Menu1ButtonDrawerActivity.setColor(Colors.YELLOWRED);
                        SettingsActivity.setColor(Colors.YELLOWRED);
                        break;
                }
                switch (selectedMenu){
                    case R.id.radioButtonOne:
                        MainActivity.setMainActivity(false);

                        startActivity(new Intent(SettingsActivity.this, Menu1ButtonDrawerActivity.class));
                        finish();
                        break;
                    case R.id.radioButtonFour:
                        MainActivity.setMainActivity(true);

                        startActivity(new Intent(SettingsActivity.this, MainActivity.class));
                        finish();
                        break;
                }
            }
        });
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

        settingsLayout.setBackgroundColor(red);
        settingsText.setTextColor(yellow);
        saveButton.setTextColor(yellow);
        menu.setTextColor(yellow);
        radioButtonOne.setTextColor(yellow);
        radioButtonFour.setTextColor(yellow);
        radioButtonBW.setBackgroundColor(white);

        color = Colors.YELLOWRED;

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

        settingsLayout.setBackgroundColor(blue);
        settingsText.setTextColor(yellow);
        saveButton.setTextColor(yellow);
        menu.setTextColor(yellow);
        radioButtonOne.setTextColor(yellow);
        radioButtonFour.setTextColor(yellow);
        radioButtonBW.setBackgroundColor(white);

        color = Colors.YELLOWBLUE;

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

        settingsLayout.setBackgroundColor(white);
        settingsText.setTextColor(black);
        saveButton.setTextColor(black);
        menu.setTextColor(black);
        radioButtonOne.setTextColor(black);
        radioButtonFour.setTextColor(black);
        radioButtonBW.setBackgroundColor(white);

        color = Colors.BLACKWHITE;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
    switch(keyCode){
        case KeyEvent.KEYCODE_BACK:
            if (menu4Options){
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
            }
            else {
                startActivity(new Intent(SettingsActivity.this,Menu1ButtonDrawerActivity.class));
            }
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

    public static void setMenu4Options(boolean menu4Options) {
        SettingsActivity.menu4Options = menu4Options;
    }

    public static void setColor(Colors color) {
        SettingsActivity.color = color;
    }
}
