package fi.upm.tfg.detectors;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import fi.upm.tfg.magnificador.MagnificadorActivity;
import fi.upm.tfg.magnificador.MagnificadorProcess;
import fi.upm.tfg.magnificador.R;

public class DiagonalMoveDetector implements GestureInterfaceTest {

    private static final String TAG = "MagnificadorActivity: ";

    // Declaración e inicialización de variables

    // Variables que representan las coordenadas X e Y inmediatamente anteriores
    // a las actuales, en las que nos movemos al desplazarnos por la pantalla
    private double mX, mY;
    /* Variables que nos indicarán la diferencia entre las coordenadas actuales
       y las inmediatamente anteriores */
    private double dx, dy;
    //Variable para capturar el tiempo en que se realizó la primera pulsación (ACTION_DOWN)
    private long startTime = 0;

    /* Variables que representan las coordenadas donde se han realizado las
       pulsaciones y la distancia recorrida entre el "DOWN" y el "UP" */
    float CoordenadaX1, CoordenadaXFINAL, Distancia;

    // Constructor de la clase
    public DiagonalMoveDetector(Context applicationContext) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event, MagnificadorProcess mView) {
        // Obtenemos las coordenadas X e Y
        double X = event.getX();
        double Y = event.getY();
		/* Variable que indica el identificador de puntero recibido en el evento
		(En caso de ser 0, se refiere a una pulsación con un dedo y en caso
		de ser 1, se refiere a una pulsación con dos dedos)*/
        int pointerID = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
        int mov = event.getActionMasked();
        final int fingersCount = event.getPointerCount();

        switch (mov) {
            case MotionEvent.ACTION_DOWN:
                // Obtenemos el tiempo en que se presionó la pantalla(DOWN)
                startTime = System.currentTimeMillis();
                // Obtenemos la coordenada X inicial de pulsación
                CoordenadaX1 = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:

                // La siguiente operación permite calcular la diferencia entre las coordenadas actuales y las inmediatamente anteriores
                dx = Math.abs(X - mX);
                dy = Math.abs(Y - mY);

                break;

            case MotionEvent.ACTION_UP:
                // Obtenemos la coordenada X final
                CoordenadaXFINAL = event.getX();
                // Calculamos la duración entre el "UP" y el "DOWN"
                long duration = System.currentTimeMillis() - startTime;
                // Calculamos la distancia recorrida en el movimiento
                Distancia = Math.abs(CoordenadaXFINAL - CoordenadaX1);

			/* En caso de que sea movimiento diagonal, la duración sea mayor que 100
			   (En caso de ser menor sería un TAP) y menos de 750, y la distancia recorrida
			   se mayor de 500 y solo sea presionado por un dedo: */

                if ((Math.abs(dx - dy) < 2) && (duration > 100)
                        && (duration < 750)
                        && (Distancia > 500)
                        && (pointerID==0)
                        && event.getPointerCount() == 1) {
                    if (Y < X) {
                        if(MagnificadorActivity.getFlashed() && !MagnificadorActivity.isSCALING()){
                            mView.flashOff();
                            MagnificadorActivity.setFlashed(false);
                            setToast("Flash Off",mView);
                        }
                        else{
                            mView.flashOn();
                            MagnificadorActivity.setFlashed(true);
                            setToast("Flash On",mView);
                        }
                    }
                    if (X < Y) {
                        if(MagnificadorActivity.isSTAB() && !MagnificadorActivity.isSCALING()){
                            mView.videoStabilizationOff();
                            MagnificadorActivity.setSTAB(false);
                            setToast("Estabilizador Off",mView);
                        }
                        else{
                            mView.videoStabilizationOn();
                            MagnificadorActivity.setSTAB(true);
                            setToast("Estabilizador On",mView);
                        }
                    }
                }
                break;
        }
        // Actualizamos los valores mX y mY
        mX = X;
        mY = Y;

        return true;
    }
    /*Método que muestra un Toast por pantalla de 750ms de duración*/
    public void setToast(String msg, MagnificadorProcess mView){
        Context context = mView.getContext();
        final Toast toast1 = new Toast(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_layout,null);
        TextView txtMsg = (TextView)layout.findViewById(R.id.txtMensaje);
        txtMsg.setText(msg);
        toast1.setDuration(Toast.LENGTH_SHORT);
        toast1.setView(layout);
        toast1.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast1.cancel();
            }
        }, 750);
    }
}
