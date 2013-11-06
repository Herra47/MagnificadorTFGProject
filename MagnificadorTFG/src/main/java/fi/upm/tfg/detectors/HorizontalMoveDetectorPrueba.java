package fi.upm.tfg.detectors;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.TextView;

public class HorizontalMoveDetectorPrueba implements GestureInterface {

	// Declaración e inicialización de variables

	// Variables que representan las coordenadas X e Y inmediatamente anteriores
	// a las actuales, en las que nos movemos al desplazarnos por la pantalla
	private double mX, mY;
	// Variable que representa el movimiento hacia la derecha
	int aderecha = 0;
	// Variables que nos indicarán la diferencia entre las coordenadas actuales
	// y las inmediatamente anteriores
	double dx, dy;
	// Variable que representa la vista de texto dónde se emitirán los mensajes
	// por pantalla
	private TextView txt;


	// Constructor de la clase
	public HorizontalMoveDetectorPrueba(Context applicationContext) {

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
			// La siguiente operación permite calcular la diferencia entre las
			// coordenadas actuales y las inmediatamente anteriores
			dx = Math.abs(X - mX);
			dy = Math.abs(Y - mY);
			// Activamos el movimiento hacia la derecha en caso de que la
			// coordenada X actual sea mayor que la coordenada X inmediatamente
			// anterior.
			if (X > mX)
				aderecha = 1;
			if (X < mX)
				aderecha = 0;
			break;

		case MotionEvent.ACTION_UP:

			// Condiciones de un movimiento horizontal(Los valores a comparar
			// pueden depender del dispositivo móvil)
			if (dy < 6 && dx > 6) {
				if (aderecha == 0){
                    //TextMessage("Movimiento Horizontal Derecha a Izda MENU COLORES");

                }
				else if (aderecha == 1){
                    //TextMessage("Movimiento Horizontal Izda a Derecha MENU MODOS DE VISUALIZACION");
                }
			}
		}
		// Actualizamos los valores mX y mY
		mX = X;
		mY = Y;

		return true;
	}

	// Método que imprime el contenido de la variable "texto" por pantalla
	//@Override
	public void TextMessage(String texto) {
		txt.setText(texto);
	}

    public int getAderecha() {
        return aderecha;
    }
}
