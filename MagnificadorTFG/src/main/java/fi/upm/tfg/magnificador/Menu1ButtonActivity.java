package fi.upm.tfg.magnificador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import fi.upm.tfg.enums.Colors;
import fi.upm.tfg.enums.Options;

public class Menu1ButtonActivity extends Activity {

    private Options option = Options.CAMERA;
    private TextView txt;
    private static boolean blackWhite = true;
    private static Colors color = Colors.BLACKWHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1button);

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

        txt = (TextView) findViewById(R.id.menu1Text);

        final Button mainButton = (Button) findViewById(R.id.optionButton);
        final ImageButton buttonLeft = (ImageButton) findViewById(R.id.imageButtonLeft);
        final ImageButton buttonRight = (ImageButton) findViewById(R.id.imageButtonRight);


        final RelativeLayout myLayout = (RelativeLayout) findViewById(R.id.menuButton);


        /*myLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            ///Metodo encargado de detectar el evento y reconocerlo
            public boolean onTouch(View arg0, MotionEvent event) {

                onTouchEvent(event);
                return true;
            }
        });*/

       buttonRight.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {

               changeOptionsRight();
            }
        });

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeOptionsLeft();
            }
        });

        mainButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                switch (option){
                    case CAMERA:
                        //Creamos el Intent
                        Intent intent = new Intent(Menu1ButtonActivity.this, MagnificadorActivity.class);
                        //Comenzamos la nueva actividad
                        startActivity(intent);
                        break;

                    case CONTRAST:
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

                        break;

                    case SETTINGS:
                        SettingsActivity.setMenu4Options(false);
                        //Creamos el Intent
                        intent = new Intent(Menu1ButtonActivity.this, SettingsActivity.class);
                        //Comenzamos la nueva actividad
                        startActivity(intent);
                        finish();
                        break;

                    case MENU:
                        MainActivity.setMainActivity(true);
                        //Creamos el Intent
                        intent = new Intent(Menu1ButtonActivity.this, MainActivity.class);
                        //Comenzamos la nueva actividad
                        startActivity(intent);
                        finish();
                        break;
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu1_button, menu);
        return true;
    }


    private void changeOptionsRight (){

        Button mainButton = (Button) findViewById(R.id.optionButton);

        switch (option){
            case CAMERA:
                mainButton.setText(getResources().getText(R.string.button_contrast));
                option = Options.CONTRAST;
                break;
            case CONTRAST:
                mainButton.setText(getResources().getText(R.string.button_settings));
                option = Options.SETTINGS;
                break;
            case SETTINGS:
                mainButton.setText(getResources().getText(R.string.button_menu4));
                option = Options.MENU;
                break;
            case MENU:
                mainButton.setText(getResources().getText(R.string.button_camera));
                option = Options.CAMERA;
                break;
        }
    }

    private void changeOptionsLeft (){

        Button mainButton = (Button) findViewById(R.id.optionButton);

        switch (option){
            case CAMERA:
                mainButton.setText(getResources().getText(R.string.button_menu4));
                option = Options.MENU;
                break;
            case CONTRAST:
                mainButton.setText(getResources().getText(R.string.button_camera));
                option = Options.CAMERA;
                break;
            case SETTINGS:
                mainButton.setText(getResources().getText(R.string.button_contrast));
                option = Options.CONTRAST;
                break;
            case MENU:
                mainButton.setText(getResources().getText(R.string.button_settings));
                option = Options.SETTINGS;
                break;
        }
    }

    private void contrastBlackWhite(){

        Button mainButton = (Button) findViewById(R.id.optionButton);
        ImageButton buttonLeft = (ImageButton) findViewById(R.id.imageButtonLeft);
        ImageButton buttonRight = (ImageButton) findViewById(R.id.imageButtonRight);
        View view1 = (View) findViewById(R.id.View01);
        View view2 = (View) findViewById(R.id.View02);

        int black = getResources().getColor(R.color.Black);
        int white = getResources().getColor(R.color.White);
        mainButton.setBackgroundColor(white);
        mainButton.setTextColor(black);
        buttonLeft.setBackgroundColor(white);
        buttonRight.setBackgroundColor(white);
        buttonLeft.setImageDrawable(getResources().getDrawable(R.drawable.navigation_previous_item));
        buttonRight.setImageDrawable(getResources().getDrawable(R.drawable.navigation_next_item));
        view1.setBackgroundColor(black);
        view2.setBackgroundColor(black);

        color = Colors.BLACKWHITE;
        MainActivity.setColor(color);
    }

    private void contrastYellowBlue(){

        Button mainButton = (Button) findViewById(R.id.optionButton);
        ImageButton buttonLeft = (ImageButton) findViewById(R.id.imageButtonLeft);
        ImageButton buttonRight = (ImageButton) findViewById(R.id.imageButtonRight);
        View view1 = (View) findViewById(R.id.View01);
        View view2 = (View) findViewById(R.id.View02);

        int yellow = getResources().getColor(R.color.Yellow);
        int blue = getResources().getColor(R.color.Blue);
        mainButton.setBackgroundColor(blue);
        mainButton.setTextColor(yellow);
        buttonLeft.setBackgroundColor(blue);
        buttonRight.setBackgroundColor(blue);
        buttonLeft.setImageDrawable(getResources().getDrawable(R.drawable.navigation_previous_item_yellow));
        buttonRight.setImageDrawable(getResources().getDrawable(R.drawable.navigation_next_item_yellow));
        view1.setBackgroundColor(yellow);
        view2.setBackgroundColor(yellow);

        color = Colors.YELLOWBLUE;
        MainActivity.setColor(color);
    }

    private void contrastYellowRed(){

        Button mainButton = (Button) findViewById(R.id.optionButton);
        ImageButton buttonLeft = (ImageButton) findViewById(R.id.imageButtonLeft);
        ImageButton buttonRight = (ImageButton) findViewById(R.id.imageButtonRight);
        View view1 = (View) findViewById(R.id.View01);
        View view2 = (View) findViewById(R.id.View02);

        int yellow = getResources().getColor(R.color.Yellow);
        int red = getResources().getColor(R.color.Red);
        mainButton.setBackgroundColor(red);
        mainButton.setTextColor(yellow);
        buttonLeft.setBackgroundColor(red);
        buttonRight.setBackgroundColor(red);
        buttonLeft.setImageDrawable(getResources().getDrawable(R.drawable.navigation_previous_item_yellow));
        buttonRight.setImageDrawable(getResources().getDrawable(R.drawable.navigation_next_item_yellow));
        view1.setBackgroundColor(yellow);
        view2.setBackgroundColor(yellow);

        color = Colors.YELLOWRED;
        MainActivity.setColor(color);
    }

    public boolean getBlackWhite (){
        return blackWhite;
    }

    public static void setBlackWhite (boolean blackWhite1){
        blackWhite = blackWhite1;
    }

    public Colors getColor() {
        return color;
    }

    public static void setColor(Colors color1) {
        color = color1;
    }
}
