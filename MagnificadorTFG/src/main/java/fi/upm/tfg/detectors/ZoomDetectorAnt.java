package fi.upm.tfg.detectors;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.TextView;

public class ZoomDetectorAnt implements GestureInterface {

	// Declarac��n e inicializaci�n de variables

	// Variable que representa la vista de texto d�nde se emitir�n los mensajes
	// por pantalla
	private TextView txt;
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

	// Constructor de la clase
	public ZoomDetectorAnt(Context applicationContext, TextView txto) {
		txt = txto;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// Variable que indica el identificador de puntero recibido en el evento
		// (En caso de ser 0, se refiere a una pulsaci�n con un dedo y en caso
		// se der 1, se refiere a una pulsaci�n con dos dedos)
		int pointerID = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:			
		case MotionEvent.ACTION_POINTER_DOWN:
			// Obtenemos la coordenada X inicial de pulsaci�n
			CoordenadaX1 = event.getX(0);
		case MotionEvent.ACTION_POINTER_2_DOWN:
		case MotionEvent.ACTION_MOVE:
			if (pointerID == 0) {
				coordenadasDedo1[0] = (int) event.getX(pointerID);
				coordenadasDedo1[1] = (int) event.getY(pointerID);
			} 
			else {
				coordenadasDedo2[0] = (int) event.getX(pointerID);
				coordenadasDedo2[1] = (int) event.getY(pointerID);
			}
			break;
		case MotionEvent.ACTION_POINTER_UP:
			// Obtenemos la coordenada X final
			CoordenadaXFINAL = event.getX(0);
			// Calculamos la distancia recorrida en el movimiento
			DistX = Math.abs(CoordenadaXFINAL - CoordenadaX1);
			//TextMessage("Distancia   =  "+ DistX);

		case MotionEvent.ACTION_POINTER_2_UP:
			if (pointerID == 0) {
				coordenadasDedo1[0] = -1;
				coordenadasDedo1[1] = -1;
			}
			else {
				coordenadasDedo2[0] = -1;
				coordenadasDedo2[1] = -1;
			}
			gesto = 0;
			break;
		case MotionEvent.ACTION_UP:

			coordenadasDedo1[0] = -1;
			coordenadasDedo1[1] = -1;
			coordenadasDedo2[0] = -1;
			coordenadasDedo2[1] = -1;
			break;
		}

		if (event.getPointerCount() == 2) {
			int x, y;

			switch (event.getAction()) {
			case MotionEvent.ACTION_POINTER_DOWN:		

			case MotionEvent.ACTION_POINTER_2_DOWN:
				x = coordenadasDedo2[0] - coordenadasDedo1[0];
				y = coordenadasDedo2[1] - coordenadasDedo1[1];
				distancia = Math.sqrt(x * x + y * y);
				break;
			case MotionEvent.ACTION_MOVE:
				x = coordenadasDedo2[0] - coordenadasDedo1[0];
				y = coordenadasDedo2[1] - coordenadasDedo1[1];
				gesto = Math.sqrt(x * x + y * y) - distancia;
			}

		}
		// Determinamos el tipo de gesto y si la distancia recorrida supera el umbral mínimo requerido para considerarse Zoom):
		if ((gesto < 0) &&(DistX>50)){
			TextMessage("Movimiento Reducir");
		}
		if ((gesto > 0) &&(DistX>50)){
			TextMessage("Movimiento Ampliar");
		}

		return true;
	}

	// M�todo que imprime el contenido de la variable "texto" por pantalla
	//@Override
	public void TextMessage(String texto) {
		txt.setText(texto);
	}

}
