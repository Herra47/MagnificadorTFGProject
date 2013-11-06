
package fi.upm.tfg.detectors;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

public class LongTapMoveDetectorAnt extends GestureDetector.SimpleOnGestureListener
		implements GestureInterface {

	// Declarac��n e inicializaci�n de variables

	// Variable que representa la vista de texto d�nde se emitir�n los mensajes
	// por pantalla
	private TextView txt;
	// Variable para capturar el tiempo en que se realiz� la primera pulsaci�n
	// (ACTION_DOWN)*/
	long startTime;
	// Variable que calcula el tiempo transcurrido entre que se puls� y se solt�
	// el dedo sobre la pantalla
	long duration;
	// Variables que representan las coordenadas d�nde se han realizado las
	// pulsaciones y la distancia recorrida entre el "DOWN" y el "UP"
	float CoordenadaX1, CoordenadaXFINAL, Dist;

	// Constructor de la clase
	public LongTapMoveDetectorAnt(Context applicationContext, TextView txto) {
		txt = txto;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		{
			
			// Variable que indica el identificador de puntero recibido en el
						// evento
						// (En caso de ser 0, se refiere a una pulsaci�n con un dedo y en
						// caso
						// se der 1, se refiere a una pulsaci�n con dos dedos)
			int pointerID = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
			
			switch (event.getAction() & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN:
				//Calculamos las coordenada X en el DOWN
				CoordenadaX1=event.getX();
				// Obtenemos el tiempo en que se presion� la pantalla(DOWN)
				startTime = System.currentTimeMillis();
				break;
			case MotionEvent.ACTION_UP:
				//Calculamos las coordenada X final en el UP
				CoordenadaXFINAL=event.getX();
				//Calculamos la distancia recorrida entre el DOWN y el UP
				Dist = Math.abs(CoordenadaXFINAL - CoordenadaX1);
				// Calculamos la duraci�n entre el "UP" y el "DOWN"
				long duration = System.currentTimeMillis() - startTime;
				if (((duration) > 90) &&(Dist<1)&&(pointerID==0)){
					//TextMessage("Movimiento Enfocar");
				}
				break;
			}
		}
		return true;
	}
}
