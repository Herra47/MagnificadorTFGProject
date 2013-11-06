package fi.upm.tfg.detectors;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

public class TapMoveDetector extends GestureDetector.SimpleOnGestureListener
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

	// Constructor de la clase
	public TapMoveDetector(Context applicationContext, TextView txto) {
		txt = txto;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		{
			switch (event.getAction() & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN:
				// Obtenemos el tiempo en que se presion� la pantalla(DOWN)
				startTime = System.currentTimeMillis();
				break;
			case MotionEvent.ACTION_UP:
				// Calculamos la duraci�n entre el "UP" y el "DOWN"
				long duration = System.currentTimeMillis() - startTime;
				if ((duration) < 90) {
					TextMessage("TAP SIMPLE");
				}
				break;
			}
		}
		return true;
	}

	// M�todo que imprime el contenido de la variable "texto" por pantalla
	//@Override
	public void TextMessage(String texto) {
		txt.setText(texto);
	}

}
