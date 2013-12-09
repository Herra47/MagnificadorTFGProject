package fi.upm.tfg.detectors;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.TextView;

import fi.upm.tfg.magnificador.MagnificadorProcess;

public class HorizontalMoveDetector implements GestureInterfaceTest {

    private static final String TAG = "MagnificadorActivity: ";

    long startTime;

    // Variables que representan las coordenadas X e Y inmediatamente anteriores
    // a las actuales, en las que nos movemos al desplazarnos por la pantalla
    private double mX1, mY1;
    // Variable que representa el movimiento hacia la derecha
    int aderecha = 0;
    // Variables que nos indicarán la diferencia entre las coordenadas actuales
    // y las inmediatamente anteriores
    double dx1, dy1, x,y;

    public HorizontalMoveDetector(Context applicationContext) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event, MagnificadorProcess mView) {


        //Number of touches
        int pointerCount = event.getPointerCount();
        if(pointerCount == 1){
            int action = event.getActionMasked();
            int actionIndex = event.getActionIndex();
            String actionString;

            switch (action)
            {
                case MotionEvent.ACTION_DOWN:
                    x = event.getX(0);
                    y = event.getY(0);
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_MOVE:

                    dx1 = Math.abs(x - mX1);
                    dy1 = Math.abs(y - mY1);
                    // Activamos el movimiento hacia la derecha en caso de que la
                    // coordenada X actual sea mayor que la coordenada X inmediatamente
                    // anterior.
                    if (x > mX1){
                        aderecha = 1;
                    }
                    else if (x < mX1){
                        aderecha = 0;
                    }
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    startTime = System.currentTimeMillis();
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    long duration = System.currentTimeMillis() - startTime;
                    // Condiciones de un movimiento horizontal(Los valores a comparar
                    // pueden depender del dispositivo móvil)
            }
            // Actualizamos los valores mX y mY
            mX1 = x;
            mY1 = y;

            pointerCount = 0;
        }
        return false;
    }
}