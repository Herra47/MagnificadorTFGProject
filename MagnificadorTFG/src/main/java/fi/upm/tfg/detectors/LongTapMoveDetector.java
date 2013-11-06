
package fi.upm.tfg.detectors;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;

import fi.upm.tfg.magnificador.MagnificadorProcess;

public class LongTapMoveDetector extends GestureDetector.SimpleOnGestureListener
		implements GestureInterfaceTest {

	// Constructor de la clase
	public LongTapMoveDetector(Context applicationContext) {
	}

	@Override
	public boolean onTouchEvent(MotionEvent event,MagnificadorProcess mView) {

        /* Detecta la pulsación con un dedo al levantarlo */

            final int action = event.getAction();
            final int fingersCount = event.getPointerCount();

            if ((action == MotionEvent.ACTION_POINTER_UP) && (fingersCount == 1)) {
                return true;
            }
            return false;
	}
}
