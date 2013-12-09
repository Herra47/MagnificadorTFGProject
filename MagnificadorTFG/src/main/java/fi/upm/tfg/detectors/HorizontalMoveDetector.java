package fi.upm.tfg.detectors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.widget.TextView;

import fi.upm.tfg.magnificador.MagnificadorActivity;
import fi.upm.tfg.magnificador.MagnificadorProcess;
import fi.upm.tfg.magnificador.MainActivity;

public class HorizontalMoveDetector implements GestureInterface {

	// Declaración e inicialización de variables
    private double mFirstTouchX, mFirstTouchY;
	// Variables que nos indicarán la diferencia entre las coordenadas actuales
	// y las inmediatamente anteriores
	double dx, dy;
    Context context;

	// Constructor de la clase
	public HorizontalMoveDetector(Context applicationContext) {
        this.context = applicationContext;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// Obtenenemos las coordenadas X e Y


		int action = event.getActionMasked();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
            mFirstTouchX = event.getX();
            mFirstTouchY = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
            double mX = event.getX();
            double mY = event.getY();
			dx = Math.abs(mFirstTouchX - mX);
			dy = Math.abs(mFirstTouchY - mY);
			break;
		case MotionEvent.ACTION_UP:
            if(mFirstTouchX < 30 && (dy < 20 || dy > -20) && dx > 100){
                //((Activity)context).finish();
                Intent intent = new Intent();
                intent.setClass(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);
            }
		}
		return true;
	}

}
