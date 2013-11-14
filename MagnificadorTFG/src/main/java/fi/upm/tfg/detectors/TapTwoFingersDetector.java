package fi.upm.tfg.detectors;

import android.content.Context;
import android.view.MotionEvent;

import fi.upm.tfg.magnificador.MagnificadorActivity;
import fi.upm.tfg.magnificador.MagnificadorProcess;

public class TapTwoFingersDetector implements GestureInterfaceTest {

    long startTimeF1, startTimeF2;
    private boolean f1 = false;


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
                /*if (fingersCount == 2) {
                    startTime = System.currentTimeMillis();
                }*/
                //break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                long durationF2 = System.currentTimeMillis() - startTimeF1;
                if (fingersCount == 2 && durationF2 < 350){
                    if(paused){
                        mView.unpause();
                        MagnificadorActivity.setPaused(false);
                    }
                    else{
                        mView.pause();
                        MagnificadorActivity.setPaused(true);
                    }
                }
        }
        /*if ((action == MotionEvent.ACTION_POINTER_UP) && (fingersCount == 2)) {
            if(paused){
                 mView.unpause();
                MagnificadorActivity.setPaused(false);
                }
            else{
                mView.pause();
                MagnificadorActivity.setPaused(true);
            }
            return true;
        }*/
        return false;
    }

}
