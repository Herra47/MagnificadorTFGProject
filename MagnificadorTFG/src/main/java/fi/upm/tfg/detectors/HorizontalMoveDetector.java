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

    long startTime;

	// Constructor de la clase
	public HorizontalMoveDetector(Context applicationContext) {
        this.context = applicationContext;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getActionMasked();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
            mFirstTouchX = event.getX();
            mFirstTouchY = event.getY();
            startTime = System.currentTimeMillis();
			break;
		case MotionEvent.ACTION_MOVE:
            double mX = event.getX();
            double mY = event.getY();
			dx = Math.abs(mFirstTouchX - mX);
			dy = Math.abs(mFirstTouchY - mY);
			break;
		case MotionEvent.ACTION_UP:
            long duration = System.currentTimeMillis() - startTime;
            if(mFirstTouchX < 10 && (dy < 20 || dy > -20) && dx > 200 && duration > 300){
                Intent intent = new Intent();
                intent.setClass(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
		}
		return true;
	}

}
