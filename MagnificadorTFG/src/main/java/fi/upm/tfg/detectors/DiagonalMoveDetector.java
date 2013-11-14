package fi.upm.tfg.detectors;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import fi.upm.tfg.magnificador.MagnificadorActivity;
import fi.upm.tfg.magnificador.MagnificadorProcess;

public class DiagonalMoveDetector implements GestureInterfaceTest {

	// Declaración e inicialización de variables

	// Variables que representan las coordenadas X e Y inmediatamente anteriores
	// a las actuales, en las que nos movemos al desplazarnos por la pantalla
	private double mX, mY;
	/* Variables que nos indicarán la diferencia entre las coordenadas actuales
	   y las inmediatamente anteriores */
	private double dx, dy;
	//Variable para capturar el tiempo en que se realizó la primera pulsación (ACTION_DOWN)
	private long startTime = 0;

	/* Variables que representan las coordenadas donde se han realizado las
	   pulsaciones y la distancia recorrida entre el "DOWN" y el "UP" */
	float CoordenadaX1, CoordenadaXFINAL, Distancia;

	// Constructor de la clase
	public DiagonalMoveDetector(Context applicationContext) {

	}

	@Override
	public boolean onTouchEvent(MotionEvent event, MagnificadorProcess mView) {
		// Obtenemos las coordenadas X e Y
		double X = event.getX();
		double Y = event.getY();
		/* Variable que indica el identificador de puntero recibido en el evento
		(En caso de ser 0, se refiere a una pulsación con un dedo y en caso
		de ser 1, se refiere a una pulsación con dos dedos)*/
		int pointerID = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
		int mov = event.getActionMasked();

		switch (mov) {
		case MotionEvent.ACTION_DOWN:
			// Obtenemos el tiempo en que se presionó la pantalla(DOWN)
			startTime = System.currentTimeMillis();
			// Obtenemos la coordenada X inicial de pulsación
			CoordenadaX1 = event.getX();
			break;
		case MotionEvent.ACTION_MOVE:
			// La siguiente operación permite calcular la diferencia entre las coordenadas actuales y las inmediatamente anteriores
			dx = Math.abs(X - mX);
			dy = Math.abs(Y - mY);

			break;

		case MotionEvent.ACTION_UP:
			// Obtenemos la coordenada X final
			CoordenadaXFINAL = event.getX();
			// Calculamos la duración entre el "UP" y el "DOWN"
			long duration = System.currentTimeMillis() - startTime;
			// Calculamos la distancia recorrida en el movimiento
			Distancia = Math.abs(CoordenadaXFINAL - CoordenadaX1);

			/* En caso de que sea movimiento diagonal, la duración sea mayor que
			   100 (En caso de ser menor sería un TAP)
			   y la distancia recorrida se mayor de 200(200 Representa la
			   distancia mínima de recorrido) y solo sea presionado por un dedo: */
			if ((Math.abs(dx - dy) < 2) && (duration > 100)
					&& (Distancia > 200) && (pointerID==0)) {
				if (Y < X) {
					//TextMessage("Movimiento DIAGONAL FLASH ON/OFF !!");
                    if(MagnificadorActivity.getFlashed()){
                        mView.flashOff();
                        MagnificadorActivity.setFlashed(false);
                    }
                    else{
                        mView.flashOn();
                        MagnificadorActivity.setFlashed(true);
                    }
				}
				if (X < Y) {
                    if(MagnificadorActivity.isSTAB()){
                        mView.videoStabilizationOff();
                        MagnificadorActivity.setSTAB(false);
                    }
                    else{
                        mView.videoStabilizationOn();
                        MagnificadorActivity.setSTAB(true);
                    }//TextMessage("Movimiento DIAGONAL ESTABILIZADOR DE IMAGEN ON/OFF !!");
				}
			}
			break;
		}
		// Actualizamos los valores mX y mY
		mX = X;
		mY = Y;

		return true;
	}


}
