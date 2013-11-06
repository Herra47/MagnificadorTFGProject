package fi.upm.tfg.detectors;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import fi.upm.tfg.magnificador.MagnificadorProcess;

public class MoveDetector implements GestureInterfaceTest {

	// Declarac��n e inicializaci�n de variables
	// Variable para capturar el tiempo en que se realiz� la primera pulsaci�n
	// (ACTION_DOWN)
	long startTime = 0;
	// Variables que representan las coordenadas d�nde se han realizado las
	// pulsaciones y la distancia recorrida entre el "DOWN" y el "UP"
	float CoordenadaX1, CoordenadaXFINAL, Dist,dx,dy;
    float prevX,prevY;
	

	// Constructor de la clase
	public MoveDetector(Context applicationContext) {
	}

	@Override
	public boolean onTouchEvent(MotionEvent event, MagnificadorProcess mView) {

        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) mView.getLayoutParams();

        float heigth = lp.height;
        float width = lp.width;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dx =  event.getX();
                dy =  event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                float x =  event.getX();
                float y =  event.getY();






                /*if (x > heigth && y < width){
                    mView.translate(heigth, y - dy);
                }
                else if(x < heigth && y > width){
                    mView.translate(x - dx,width);
                }
                else if(x < heigth && y < width){
                    mView.translate(x - dx, y - dy);
                }*/
                mView.translate(width + (x - dx), heigth + (y - dy));
               /* float left = lp.width + (x - dx);
                float top = lp.height + (y - dy);
                lp.width = (int)left;
                lp.height = (int)top;
                mView.setLayoutParams(lp);*/
                break;
        }
        return true;



		/* Variable que indica el identificador de puntero recibido en el evento
		(En caso de ser 0, se refiere a una pulsación con un dedo y en caso
		se der 1, se refiere a una pulsación con dos dedos)
		int pointerID = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;

		int mov = event.getActionMasked();
		switch (mov) {
		case MotionEvent.ACTION_DOWN:
			//Calculamos las coordenada X en el DOWN
			CoordenadaX1=event.getX();
			// Obtenemos el tiempo en que se presion� la pantalla(DOWN)
			startTime = System.currentTimeMillis();
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			//Calculamos las coordenada X final en el UP
			CoordenadaXFINAL=event.getX();
			//Calculamos la distancia recorrida entre el DOWN y el UP
			Dist = Math.abs(CoordenadaXFINAL - CoordenadaX1);
			// Calculamos la duraci�n entre el "UP" y el "DOWN"
			long duration = System.currentTimeMillis() - startTime;
			if (((duration) > 500)&&(Dist>5) &&(pointerID==0)) {
				//TextMessage("Movimiento Desplazamiento en Imagen");
			}
			break;
		}
		return true;*/
	}



}
