package fi.upm.tfg.detectors;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import fi.upm.tfg.magnificador.MagnificadorActivity;
import fi.upm.tfg.magnificador.MagnificadorProcess;
import fi.upm.tfg.magnificador.R;

public class SingleTapDetector implements GestureInterfaceTest {

    private static final String TAG = "MagnificadorActivity: ";

    long startTime;

    double mLastTouchX1, mLastTouchY1, mLastTouchX2, mLastTouchY2;
    int INVALID_POINTER_ID = -1;
    private int mActivePointerId = INVALID_POINTER_ID;
    private int mActivePointerId2 = INVALID_POINTER_ID;
    double dx1, dy1, dx2, dy2;

    // Constructor de la clase
    public SingleTapDetector(Context applicationContext) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event, MagnificadorProcess mView) {

        /* Detecta la pulsación con dos dedos */
        int pointerCount = event.getPointerCount();

        /*if(pointerCount != 2) {
            Log.i(TAG, "not pinching - exactly 2 fingers are needed but have " + pointerCount);
            return false;
        }*/

        int action = MotionEventCompat.getActionMasked(event);

        switch(action){
            case MotionEvent.ACTION_DOWN:{
                startTime = System.currentTimeMillis();
                final int pointerIndex = MotionEventCompat.getActionIndex(event);
                final float x1 =  MotionEventCompat.getX(event,pointerIndex);
                final float y1 =  MotionEventCompat.getY(event,pointerIndex);

                // Remember where we started
                mLastTouchX1 = x1;
                mLastTouchY1 = y1;
                break;
            }
            case MotionEvent.ACTION_MOVE:{

                try{

                final int pointerIndex = MotionEventCompat.findPointerIndex(event, mActivePointerId);
                final float x1 =  MotionEventCompat.getX(event,pointerIndex);
                final float y1 =  MotionEventCompat.getY(event,pointerIndex);
                // Calculate the distance moved
                dx1 = Math.abs(x1 - mLastTouchX1);
                dy1 = Math.abs(y1 - mLastTouchY1);
                //Log.i(TAG, "Distancias (" + dx1 + ", " + dy1 + ") y (" + dx2 + ", " + dy2 + ")");
                break;
                }
                catch (IllegalArgumentException e){
                    break;
                }
            }
            case MotionEvent.ACTION_UP:{
                long duration = System.currentTimeMillis() - startTime;
                if (event.getPointerCount() == 1 && duration < 300){
                    if(dx1 < 10 && dy1 < 10){
                        mView.autoFocus();
                    }
                }
                break;
            }
        }







        return true;
    }

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
