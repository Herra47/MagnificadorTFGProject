package fi.upm.tfg.detectors;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import fi.upm.tfg.magnificador.MagnificadorActivity;
import fi.upm.tfg.magnificador.MagnificadorProcess;
import fi.upm.tfg.magnificador.R;

public class TapTwoFingersDetector implements GestureInterfaceTest {

    long startTimeF1, startTimeF2;

    // Constructor de la clase
    public TapTwoFingersDetector(Context applicationContext) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event, MagnificadorProcess mView) {

        /* Detecta la pulsación con dos dedos */

        final int action = event.getAction();
        final int fingersCount = event.getPointerCount();
        boolean paused = MagnificadorActivity.getPaused();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                startTimeF1 = System.currentTimeMillis();
                startTimeF2 = System.currentTimeMillis();
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                long durationF2 = System.currentTimeMillis() - startTimeF1;
                if (fingersCount == 2 && durationF2 < 350){
                    if(paused){
                        mView.unpause();
                        MagnificadorActivity.setPaused(false);
                        setToast("UNPAUSED",mView);
                    }
                    else{
                        mView.pause();
                        MagnificadorActivity.setPaused(true);
                        setToast("PAUSED",mView);
                    }
                }
        }
        return false;
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
