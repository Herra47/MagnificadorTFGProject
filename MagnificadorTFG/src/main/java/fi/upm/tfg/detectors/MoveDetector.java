package fi.upm.tfg.detectors;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.opencv.core.Rect;

import fi.upm.tfg.magnificador.MagnificadorActivity;
import fi.upm.tfg.magnificador.MagnificadorProcess;

public class MoveDetector implements GestureInterfaceTest2 {

    private static final String TAG = "MagnificadorActivity: ";

    float mLastTouchX, mLastTouchY,mPosX,mPosY;
    int INVALID_POINTER_ID = -1;
    // The ‘active pointer’ is the one currently moving our object.
    private int mActivePointerId = INVALID_POINTER_ID;


    // Constructor de la clase
    public MoveDetector(Context applicationContext) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event, MagnificadorProcess mView, Float mScaleFactor) {

        final int action = MotionEventCompat.getActionMasked(event);

        if (event.getPointerCount() == 1){
            switch (action) {
                case MotionEvent.ACTION_DOWN: {

                    final int pointerIndex = MotionEventCompat.getActionIndex(event);
                    final float x =  MotionEventCompat.getX(event,pointerIndex);
                    final float y =  MotionEventCompat.getY(event,pointerIndex);

                    // Remember where we started (for dragging)
                    mLastTouchX = x;
                    mLastTouchY = y;
                    // Save the ID of this pointer (for dragging)
                    mActivePointerId = MotionEventCompat.getPointerId(event, 0);
                    break;
                }
                case MotionEvent.ACTION_MOVE: {

                    try{

                        // Find the index of the active pointer and fetch its position
                        final int pointerIndex = MotionEventCompat.findPointerIndex(event, mActivePointerId);

                        final float x =  MotionEventCompat.getX(event,pointerIndex);
                        final float y =  MotionEventCompat.getY(event,pointerIndex);

                        // Calculate the distance moved
                        final float dx = x - mLastTouchX;
                        final float dy = y - mLastTouchY;

                        mPosX = MagnificadorActivity.getmPOSX();
                        mPosY = MagnificadorActivity.getmPOSY();

                        mPosX += dx;
                        mPosY += dy;

                        //Log.i(TAG, "mPosX = " + Float.toString(mPosX));
                       // Log.i(TAG, "mPosY = " + Float.toString(mPosY));

                        /*Limit bounds*/
                        float mWidth = mView.getWidth();
                        float mHeight = mView.getHeight();

                        //Por si cambio los pivotes, para mantener el limite tengo que cambiar
                        //mWidth/2 por el pivote x y mHeight/2 por el pivote y

                        if(mPosX < (-mWidth/2)*(mScaleFactor-1)){
                            mPosX = (-mWidth/2)*(mScaleFactor-1);
                        }
                        else if(mPosX > (mScaleFactor-1)*mWidth/2){
                            mPosX = (mScaleFactor-1)*mWidth/2;
                        }
                        if(mPosY < (-mHeight/2)*(mScaleFactor-1)){
                            mPosY = (-mHeight/2)*(mScaleFactor-1);
                        }
                        else if(mPosY > (mScaleFactor-1)*mHeight/2){
                            mPosY = (mScaleFactor-1)*mHeight/2;
                        }

                        /* translate the image*/

                        mView.translate(mPosX, mPosY);

                        MagnificadorActivity.setmPOSX(mPosX);
                        MagnificadorActivity.setmPOSY(mPosY);



                        mView.invalidate();

                        // Remember this touch position for the next move event
                        mLastTouchX = x;
                        mLastTouchY = y;

                        break;
                    }
                    catch (IllegalArgumentException e){
                        break;
                    }
                }
                case MotionEvent.ACTION_UP: {
                    mActivePointerId = INVALID_POINTER_ID;
                    break;
                }
                case MotionEvent.ACTION_CANCEL: {
                    mActivePointerId = INVALID_POINTER_ID;
                    break;
                }
                case MotionEvent.ACTION_POINTER_UP: {

                    final int pointerIndex = MotionEventCompat.getActionIndex(event);
                    final int pointerId = MotionEventCompat.getPointerId(event, pointerIndex);

                    if (pointerId == mActivePointerId) {
                    /* This was our active pointer going up. Choose a new
                     active pointer and adjust accordingly.*/
                        final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                        mLastTouchX = MotionEventCompat.getX(event, newPointerIndex);
                        mLastTouchY = MotionEventCompat.getY(event, newPointerIndex);
                        mActivePointerId = MotionEventCompat.getPointerId(event, newPointerIndex);
                    }
                    break;
                }
            }
        }
        return true;
    }
}
