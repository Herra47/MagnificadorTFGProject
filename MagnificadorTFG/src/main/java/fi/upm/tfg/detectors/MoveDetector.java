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

                    mPosX += dx;
                    mPosY += dy;

                    mView.invalidate();

                    // Remember this touch position for the next move event
                    mLastTouchX = x;
                    mLastTouchY = y;

                    /* Limit bounds*/
                    if (mPosX > ((mScaleFactor*255.0f)-255.0f)){
                        mPosX = (mScaleFactor*255.0f)-255.0f;
                    }
                    if (mPosY > ((mScaleFactor*100.0f)-100.0f)){
                        mPosY = (mScaleFactor*100.0f)-100.0f;
                    }
                    if (mPosX < -((mScaleFactor*685.0f)-685.0f)){
                        mPosX = -((mScaleFactor*685.0f)-685.0f);
                    }
                    if (mPosY < -((mScaleFactor*432.0f)-432.0f)){
                        mPosY = -((mScaleFactor*432.0f)-432.0f);
                    }
                    /* translate the image*/
                    mView.translate(mPosX, mPosY);

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
        return true;
    }
}
