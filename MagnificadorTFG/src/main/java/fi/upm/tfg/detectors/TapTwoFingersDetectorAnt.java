package fi.upm.tfg.detectors;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.TextView;

public class TapTwoFingersDetectorAnt implements GestureInterface {

	// Declaración e inicialización de variables

	// Variable que representa la vista de texto donde se emitirán los mensajes
	// por pantalla
	private TextView txt;
	// Variable para capturar el tiempo en que se realiz� la primera pulsaci�n
	// (ACTION_DOWN)*/
	long startTime;

	// Constructor de la clase
	public TapTwoFingersDetectorAnt(Context applicationContext, TextView txto) {
		txt = txto;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// Variable que indica el identificador de puntero recibido en el
		// evento
		// (En caso de ser 0, se refiere a una pulsaci�n con un dedo y en
		// caso
		// se der 1, se refiere a una pulsaci�n con dos dedos)
		int pointerID = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
	
		int mov = event.getAction();
		switch (mov) {
		case MotionEvent.ACTION_DOWN:
			startTime = System.currentTimeMillis();
			if (pointerID == 1) {
				TextMessage("CAPTURAR IMAGEN / VOLVER A LA IMAGEN REAL");
			}
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_POINTER_2_UP:
			// Calculamos la duraci�n entre el "UP" y el "DOWN"
			//long duration = System.currentTimeMillis() - startTime;
				if (pointerID == 1) {
			  long duration = System.currentTimeMillis() - startTime;
				if ((duration) < 350) {
				TextMessage("CAPTURAR IMAGEN / VOLVER A LA IMAGEN REAL");
			}			
		}
				break;
			}
		return true;
			}
	

	// M�todo que imprime el contenido de la variable "texto" por pantalla
	//@Override
	public void TextMessage(String texto) {
		txt.setText(texto);
	}

}
