package fi.upm.tfg.detectors;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import fi.upm.tfg.enums.CameraColors;
import fi.upm.tfg.magnificador.MagnificadorActivity;
import fi.upm.tfg.magnificador.MagnificadorProcess;
import fi.upm.tfg.magnificador.R;

public class TwoFingersHorizontalMoveDetector implements GestureInterfaceTest{

    long startTime;

    // Variables que representan las coordenadas X e Y inmediatamente anteriores
    // a las actuales, en las que nos movemos al desplazarnos por la pantalla
    private double mX1, mY1, mX2, mY2;
    // Variable que representa el movimiento hacia la derecha
    int aderecha = 0;
    // Variables que nos indicarán la diferencia entre las coordenadas actuales
    // y las inmediatamente anteriores
    double dx1, dy1, dx2, dy2;

    public TwoFingersHorizontalMoveDetector(Context applicationContext) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event, MagnificadorProcess mView) {


        //Number of touches
        int pointerCount = event.getPointerCount();
        if(pointerCount == 2){
            int action = event.getActionMasked();
            int actionIndex = event.getActionIndex();
            String actionString;
            double x1 = event.getX(0);
            double x2 = event.getX(1);
            double y1 = event.getY(0);
            double y2 = event.getY(1);


            switch (action)
            {
                case MotionEvent.ACTION_DOWN:

                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    dx1 = Math.abs(x1 - mX1);
                    dy1 = Math.abs(y1 - mY1);
                    dx2 = Math.abs(x2 - mX2);
                    dy2 = Math.abs(y2 - mY2);
                    // Activamos el movimiento hacia la derecha en caso de que la
                    // coordenada X actual sea mayor que la coordenada X inmediatamente
                    // anterior.
                    if (x1 > mX1 && x2 > mX2){
                        aderecha = 1;
                    }
                    else if (x1 < mX1 && x2 < mX2){
                        aderecha = 0;
                    }
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    startTime = System.currentTimeMillis();
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    long duration = System.currentTimeMillis() - startTime;
                    // Condiciones de un movimiento horizontal(Los valores a comparar
                    // pueden depender del dispositivo móvil)
                    if (dy1 < 20 && dy2 < 20 && dx1 > 10 && dx2 > 10) {
                        if (aderecha == 0){
                            colorLeft(mView);
                            return true;
                        }
                        else if (aderecha == 1){
                            colorRight(mView);
                            return true;
                        }
                    }
            }
            // Actualizamos los valores mX y mY
            mX1 = x1;
            mX2 = x2;
            mY1 = y1;
            mY2 = y2;

            pointerCount = 0;
        }
        return false;
    }

    private void colorRight(MagnificadorProcess mView) {
        switch (MagnificadorActivity.getCURRENT_COLOR()){
            case RGB:
                mView.bgr();
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.BGR);
                setToast("BGR",mView);
                break;
            case BGR:
                mView.invert();
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.INVERT);
                setToast("Invertido",mView);
                break;
            case INVERT:
                mView.gray();
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.GRAY);
                setToast("Gris",mView);
                break;
            case GRAY:
                mView.blackAndWhite(0,255/2);
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.BLACKANDWHITE);
                setToast("Blanco y negro",mView);
                break;
            case BLACKANDWHITE:
                mView.rgb();
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.RGB);
                setToast("RGB",mView);
                break;
        }
    }

    private void colorLeft(MagnificadorProcess mView) {
        switch (MagnificadorActivity.getCURRENT_COLOR()){
            case RGB:
                mView.blackAndWhite(0,255);
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.BLACKANDWHITE);
                setToast("Blanco y negro",mView);
                break;
            case BGR:
                mView.rgb();
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.RGB);
                setToast("RGB",mView);
                break;
            case INVERT:
                mView.bgr();
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.BGR);
                setToast("BGR",mView);
                break;
            case GRAY:
                mView.invert();
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.INVERT);
                setToast("Invertido",mView);
                break;
            case BLACKANDWHITE:
                mView.gray();
                MagnificadorActivity.setCURRENT_COLOR(CameraColors.GRAY);
                setToast("Gris",mView);
                break;

        }
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
