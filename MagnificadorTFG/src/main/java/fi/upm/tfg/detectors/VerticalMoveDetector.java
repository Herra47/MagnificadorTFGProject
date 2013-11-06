package fi.upm.tfg.detectors;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.TextView;

public class VerticalMoveDetector implements GestureInterface {

	// Declarac��n e inicializaci�n de variables

	// Variables que representan las coordenadas X e Y inmediatamente anteriores
	// a las actuales, en las que nos movemos al desplazarnos por la pantalla
	private double mX, mY;
	// Variable que representa el movimiento hacia abajo
	int aabajo = 0;
	// Variables que nos indicar�n la diferencia entre las coordenadas actuales
	// y las inmediatamente anteriores
	double dx, dy;
	// Variable que representa la vista de texto d�nde se emitir�n los mensajes
	// por pantalla
	private TextView txt;

	// Constructor de la clase
	public VerticalMoveDetector(Context applicationContext, TextView txto) {
		txt = txto;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// Obtenenemos las coordenadas X e Y
		double X = event.getX();
		double Y = event.getY();

		int mov = event.getActionMasked();
		switch (mov) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_MOVE:
			// La siguiente operaci�n permite calcular la diferencia entre las
			// coordenadas actuales y las inmediatamente anteriores
			dx = Math.abs(X - mX);
			dy = Math.abs(Y - mY);
			// Activamos el movimiento hacia abjo en caso de que la coordenada Y
			// actual sea mayor que la coordenada Y inmediatamente anterior.
			if (Y > mY)
				aabajo = 1;
			if (Y < mY)
				aabajo = 0;

			break;

		case MotionEvent.ACTION_UP:
			// Condiciones de un movimiento vertical(Los valores a comparar
			// pueden depender del dispositivo m�vil)
			if (dx < 6 && dy > 6) {
				// txt.setText("Estas realizando un movimiento VERTICAL !!");
				if (aabajo == 0)
					TextMessage("Movimiento Vertical de Abajo a Arriba  MENU ANDROID !!");
				if (aabajo == 1)
					TextMessage("Movimiento Vertical de Arriba a Abajo  MENU APLICACION!!");
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
