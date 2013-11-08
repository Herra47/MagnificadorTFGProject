package fi.upm.tfg.detectors;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Matrix;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import fi.upm.tfg.magnificador.MagnificadorActivity;
import fi.upm.tfg.magnificador.MagnificadorProcess;

public class MoveDetector implements GestureInterfaceTest2 {

    float mLastTouchX, mLastTouchY,mPosX,mPosY;
    int INVALID_POINTER_ID = -1;
    // The ‘active pointer’ is the one currently moving our object.
    private int mActivePointerId = INVALID_POINTER_ID;

    DisplayMetrics displaymetrics = new DisplayMetrics();
    int screenHeight = displaymetrics.heightPixels;
    int screenWidth = displaymetrics.widthPixels;

    // Constructor de la clase
	public MoveDetector(Context applicationContext) {
	}

	@Override
	public boolean onTouchEvent(MotionEvent event, MagnificadorProcess mView, Float mScaleFactor) {

        /*  ESTA ES LA BUENA */

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
                final int pointerIndex =
                        MotionEventCompat.findPointerIndex(event, mActivePointerId);

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

                //limitar los limites de la imagen
                limitDrag(mView,mScaleFactor);

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
                    // This was our active pointer going up. Choose a new
                    // active pointer and adjust accordingly.
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
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void limitDrag(View view,Float mScaleFactor) {

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        float viewWidth = layoutParams.width;
        float viewHeight = layoutParams.height;



        float topLimit = viewHeight*mScaleFactor;
        float bottomLimit = 0 - (topLimit - viewHeight);
        float rightLimit = viewWidth*mScaleFactor;
        float leftLimit = 0 - (rightLimit - viewWidth);

        Matrix matrix = view.getMatrix();
        view.getMinimumHeight();



        /*if (mPosX > rightLimit){
            mPosX = rightLimit;
        }
        if (mPosY > topLimit){
            mPosY = topLimit;
        }
        if (mPosX < leftLimit){
            mPosX = leftLimit;
        }
        if (mPosY < bottomLimit){
            mPosY = bottomLimit;
        }*/
    }



}
