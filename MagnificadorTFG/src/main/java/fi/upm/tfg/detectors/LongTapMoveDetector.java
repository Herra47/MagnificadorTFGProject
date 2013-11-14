
package fi.upm.tfg.detectors;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;

import fi.upm.tfg.magnificador.MagnificadorProcess;

public class LongTapMoveDetector extends GestureDetector.SimpleOnGestureListener
        implements GestureInterfaceTest {

    long startTime;
    // Constructor de la clase
    public LongTapMoveDetector(Context applicationContext) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event,MagnificadorProcess mView) {

        /* Detecta la pulsación con un dedo al levantarlo */

        final int action = event.getAction();
        final int fingersCount = event.getPointerCount();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                startTime = System.currentTimeMillis();
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                long duration = System.currentTimeMillis() - startTime;
                if(fingersCount == 1 && duration < 200){
                    mView.autoFocus();
                }
        }
        return true;
    }
}
