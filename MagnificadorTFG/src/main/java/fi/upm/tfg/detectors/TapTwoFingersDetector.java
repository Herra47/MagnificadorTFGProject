package fi.upm.tfg.detectors;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import fi.upm.tfg.magnificador.MagnificadorActivity;
import fi.upm.tfg.magnificador.MagnificadorProcess;
import fi.upm.tfg.magnificador.R;

public class TapTwoFingersDetector implements GestureInterfaceTest {

    private static final String TAG = "MagnificadorActivity: ";

    long startTime;

    // Constructor de la clase
    public TapTwoFingersDetector(Context applicationContext) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event, MagnificadorProcess mView) {

        /* Detecta la pulsación con dos dedos */

        final int action = event.getActionMasked();
        final int fingersCount = event.getPointerCount();
        boolean paused = MagnificadorActivity.getPaused();
        boolean movido = false;

            switch (action){
                case MotionEvent.ACTION_POINTER_DOWN:
                    startTime = System.currentTimeMillis();

                case MotionEvent.ACTION_MOVE:
                    Log.i(TAG, "Moviendose condicion");
                    movido = true;

                case MotionEvent.ACTION_POINTER_UP:
                    long duration = System.currentTimeMillis() - startTime;
                    if (!movido && event.getPointerCount() == 2 && duration < 350){
                        if(paused){
                            mView.unpause();
                            MagnificadorActivity.setPaused(false);
                            setToast("Sin pausar",mView);
                        }
                        else{
                            mView.pause();
                            MagnificadorActivity.setPaused(true);
                            setToast("Pausado",mView);
                        }
                    }
            }
        return true;
    }

    public void setToast(String msg, MagnificadorProcess mView){
        Context context = mView.getContext();
        final Toast toast1 = new Toast(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_layout,null);
        TextView txtMsg = (TextView)layout.findViewById(R.id.txtMensaje);
        txtMsg.setText(msg);
        toast1.setDuration(Toast.LENGTH_SHORT);
        toast1.setView(layout);
        toast1.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast1.cancel();
            }
        }, 750);
    }

}
