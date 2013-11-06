package fi.upm.tfg.detectors;

import android.content.Context;
import android.view.MotionEvent;

import fi.upm.tfg.magnificador.MagnificadorActivity;
import fi.upm.tfg.magnificador.MagnificadorProcess;

public class TapTwoFingersDetector implements GestureInterfaceTest {

    private boolean paused;


	// Constructor de la clase
	public TapTwoFingersDetector(Context applicationContext) {
	}

	@Override
	public boolean onTouchEvent(MotionEvent event, MagnificadorProcess mView) {

        /* Detecta la pulsación con dos dedos al levantarlos */

        final int action = event.getAction();
        final int fingersCount = event.getPointerCount();


        paused = MagnificadorActivity.getPaused();

        if ((action == MotionEvent.ACTION_POINTER_UP) && (fingersCount == 2)) {
            if(paused){
                 mView.unpause();
                MagnificadorActivity.setPaused(false);
                }
            else{
                mView.pause();
                MagnificadorActivity.setPaused(true);
            }
        }
        return true;
    }

}
