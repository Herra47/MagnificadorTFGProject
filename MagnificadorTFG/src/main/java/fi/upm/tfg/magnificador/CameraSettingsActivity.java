package fi.upm.tfg.magnificador;

import android.app.Activity;
import android.content.Intent;
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

        final Button flashButton = (Button) findViewById(R.id.flashButton);
        final Button macroButton = (Button) findViewById(R.id.macroButton);
        final Button stabButton = (Button) findViewById(R.id.stabButton);

        if(MagnificadorActivity.getFlashed()){
            flashButton.setText("Flash ON");
        }
        else{
            flashButton.setText("Flash OFF");
        }

        if(MagnificadorActivity.isMACRO()){
            macroButton.setText("Macro ON");
        }
        else{
            macroButton.setText("Macro OFF");
        }

        if(MagnificadorActivity.isSTAB()){
            stabButton.setText("Estabilizador ON");
        }
        else{
            stabButton.setText("Estabilizador OFF");
        }

        flashButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(MagnificadorActivity.getFlashed()){
                    MagnificadorActivity.setFlashed(false);
                    flashButton.setText("Flash OFF");
                }
                else{
                    MagnificadorActivity.setFlashed(true);
                    flashButton.setText("Flash ON");
                }
            }
        });

        macroButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(MagnificadorActivity.isMACRO()){
                    MagnificadorActivity.setMACRO(false);
                    macroButton.setText("Macro OFF");
                }
                else{
                    MagnificadorActivity.setMACRO(true);
                    macroButton.setText("Macro ON");
                }
            }
        });

        stabButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(MagnificadorActivity.isSTAB()){
                    MagnificadorActivity.setSTAB(false);
                    stabButton.setText("Estabilizador OFF");
                }
                else{
                    MagnificadorActivity.setSTAB(true);
                    stabButton.setText("Estabilizador ON");
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
}
