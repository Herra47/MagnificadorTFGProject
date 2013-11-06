package fi.upm.tfg.detectors;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.TextView;

public class DiagonalMoveDetector implements GestureInterface {

	// Declarac��n e inicializaci�n de variables

	// Variables que representan las coordenadas X e Y inmediatamente anteriores
	// a las actuales, en las que nos movemos al desplazarnos por la pantalla
	private double mX, mY;
	// Variables que nos indicar�n la diferencia entre las coordenadas actuales
	// y las inmediatamente anteriores
	private double dx, dy;
	//Variable para capturar el tiempo en que se realiz� la primera pulsaci�n (ACTION_DOWN)*/
	private long startTime = 0;
	// Variable que representa la vista de texto d�nde se emitir�n los mensajes
	// por pantalla
	private TextView txt;
	// Variables que representan las coordenadas d�nde se han realizado las
	// pulsaciones y la distancia recorrida entre el "DOWN" y el "UP"
	float CoordenadaX1, CoordenadaXFINAL, Distancia;

	// Constructor de la clase
	public DiagonalMoveDetector(Context applicationContext, TextView txto) {
		txt = txto;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// Obtenenemos las coordenadas X e Y 
		double X = event.getX();
		double Y = event.getY();
		// Variable que indica el identificador de puntero recibido en el
		// evento
		// (En caso de ser 0, se refiere a una pulsaci�n con un dedo y en
		// caso
		// se der 1, se refiere a una pulsaci�n con dos dedos)
		int pointerID = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;

		int mov = event.getActionMasked();
		switch (mov) {
		case MotionEvent.ACTION_DOWN:
			// Obtenemos el tiempo en que se presion� la pantalla(DOWN)
			startTime = System.currentTimeMillis();
			// Obtenemos la coordenada X inicial de pulsaci�n
			CoordenadaX1 = event.getX();
			break;
		case MotionEvent.ACTION_MOVE:
			// La siguiente operaci�n permite calcular la diferencia entre las coordenadas actuales y las inmediatamente anteriores
			dx = Math.abs(X - mX);
			dy = Math.abs(Y - mY);

			break;

		case MotionEvent.ACTION_UP:
			// Obtenemos la coordenada X final
			CoordenadaXFINAL = event.getX();
			// Calculamos la duraci�n entre el "UP" y el "DOWN"
			long duration = System.currentTimeMillis() - startTime;
			// Calculamos la distancia recorrida en el movimiento
			Distancia = Math.abs(CoordenadaXFINAL - CoordenadaX1);

			// En caso de que sea movimiento diagonal, la duraci�n sea mayor que
			// 100 (En caso de ser menor ser�a un TAP)
			// y la distancia recorrida se mayor de 200(200 Representa la
			// distancia m�nima de recorrido) y solo sea presionado por un dedo:
			if ((Math.abs(dx - dy) < 2) && (duration > 100)
					&& (Distancia > 200) && (pointerID==0)) {
				if (Y < X) {
					TextMessage("Movimiento DIAGONAL FLASH ON/OFF !!");
				}
				if (X < Y) {
					TextMessage("Movimiento DIAGONAL ESTABILIZADOR DE IMAGEN ON/OFF !!");
				}
			}
			break;
		}
		// Actualizamos los valores mX y mY
		mX = X;
		mY = Y;

		return true;
	}

	// M�todo que imprime el contenido de la variable "texto" por pantalla
	//@Override
	public void TextMessage(String texto) {
		txt.setText(texto);
	}

}
