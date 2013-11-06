package fi.upm.tfg.detectors;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.TextView;

import fi.upm.tfg.magnificador.MagnificadorProcess;

public class ZoomDetector implements GestureInterfaceTest {

    // Variables que representan vectores de coordenadas de dos posiciones(0 en
    // caso de X y 1 en caso de Y)
    private int[] coordenadasDedo1 = { -1, -1 };
    private int[] coordenadasDedo2 = { -1, -1 };
    // Variable que representa la distancia de separaci�n de los dedos tras
    // realizar el gesto Zoom In o Zoom Out
    private double distancia;
    // Variable que representa el tipo de gesto realizado(0 en caso de ning�n
    // gesto, positivo en caso de ampliaci�n o negativo en caso de reducci�n).
    private double gesto = 0;
    // Variables que representan las coordenadas d�nde se han realizado las
    // pulsaciones y la distancia recorrida entre el "DOWN" y el "UP"
    float CoordenadaX1, CoordenadaXFINAL, DistX;

    float x1,y1,x2,y2,dx1,dy1,dx2,dy2,d1,d2;


    // Constructor de la clase
	public ZoomDetector(Context applicationContext) {
	}

	@Override
	public boolean onTouchEvent(MotionEvent event, MagnificadorProcess mView) {

        // Get the pointer ID
        int pointerID = event.getPointerId(0);

        // ... Many touch events later...

        // Use the pointer ID to find the index of the active pointer
        // and fetch its position
        int pointerIndex = event.findPointerIndex(pointerID);


        final int action = event.getAction();
        final int fingersCount = event.getPointerCount();

        switch (event.getAction()) {


            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
            case MotionEvent.ACTION_POINTER_DOWN:
                x2 = event.getX(pointerID);
                y2 = (int) event.getY(pointerID);

                d1 = (float) Math.hypot(x2-x1, y2-y1);
            case MotionEvent.ACTION_MOVE:
                if (pointerID == 0) {
                    dx1 = event.getX(pointerID);
                    dy1 = event.getY(pointerID);
                }
                else {
                    dx2 = event.getX(pointerID);
                    dy2 = event.getY(pointerID);
                }

                d2 = (float) Math.hypot(dx2-dx1, dy2-dy1);
                mView.scale(x2-x1,y2-y1,100,100);
                mView.scale(x2-x1,y2-y1,100,100);

                break;
        }
		return true;
	}



}
