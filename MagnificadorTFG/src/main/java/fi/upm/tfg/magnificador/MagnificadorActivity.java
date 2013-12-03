package fi.upm.tfg.magnificador;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;


import android.content.Intent;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import fi.upm.tfg.detectors.DiagonalMoveDetector;
import fi.upm.tfg.detectors.LongTapMoveDetector;
import fi.upm.tfg.detectors.MoveDetector;
import fi.upm.tfg.detectors.NewTapTwoFingersDetector;
import fi.upm.tfg.detectors.SingleTapDetector;
import fi.upm.tfg.detectors.TapTwoFingersDetector;
import fi.upm.tfg.detectors.ThreeFingersHorizontalMoveDetector;
import fi.upm.tfg.detectors.TwoFingersHorizontalMoveDetector;
import fi.upm.tfg.detectors.TwoFingersVerticalMoveDetector;
import fi.upm.tfg.enums.CameraColors;

public class MagnificadorActivity extends Activity {

    private static final String TAG = "MagnificadorActivity: ";

    /*Camera parameters*/
    private MagnificadorProcess mView;
    private MenuItem macro;
    private MenuItem lowfps;
    private MenuItem focusarea;
    private MenuItem autofocus;
    private MenuItem videofocus;
    private MenuItem normalfps;
    private MenuItem bgr;
    private MenuItem cancelAF;
    private static float px;
    private static float py;
    private static float mPOSX = 0;
    private static float mPOSY = 0;
    float x;
    float y;

    private static CameraColors CURRENT_COLOR = CameraColors.RGB;
    private static CameraColors HIGH_CONTRAST_COLOR = CameraColors.REDANDYELLOW;

    private float mScaleFactor = 1.f;
    private float mOldScaleFactor = 1.f;
    private static float SCALE;
    private static int THRESH;

   private static boolean primera = true;
    private double maxval=100;
    private BaseLoaderCallback  mOpenCVCallBack = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG, "OpenCV loaded successfully");
                    // Create and set View
                    mView = new MagnificadorProcess(mAppContext);
                    setContentView(mView);
                    mView.setKeepScreenOn(true);
                    // Check native OpenCV camera
                    if( !mView.openCamera() ) {
                        AlertDialog ad = new AlertDialog.Builder(mAppContext).create();
                        ad.setCancelable(false); // This blocks the 'BACK' button
                        ad.setMessage("Fatal error: can't open camera!");
                        ad.setButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                finish();
                            }
                        });
                        ad.show();
                    }

                    initializeColor();

                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }




        }
    };

    private void initializeColor() {
        switch (CURRENT_COLOR){
            case RGB:
                mView.rgb();
                break;
            case GRAY:
                mView.gray();
                break;
            case INVERT:
                mView.invert();
                break;
            case HIGHCONTRAST:
                switch (HIGH_CONTRAST_COLOR){
                    case REDANDYELLOW:
                        mView.redAndYellow(127,255);
                        break;
                    case YELLOWANDBLUE:
                        mView.blueAndYellow(127,255);
                        break;
                    case BLACKANDWHITE:
                        mView.blackAndWhite(127,255);
                        break;
                }
                break;
        }

    }

    private MenuItem contrast;
    private MenuItem brightness;
    private float cont=1;
    private MenuItem invert;
    private MenuItem contrastRest;
    private MenuItem contrastAdd;
    private MenuItem stabilizatiON;
    private MenuItem stabilizatiOFF;
    private MenuItem finish;

    /* Gestures */

    private MoveDetector mMoveDetector;
    private ScaleGestureDetector mScaleDetector;
    private DiagonalMoveDetector mDiagonalMoveDetector;
    private TwoFingersHorizontalMoveDetector mTwoFingersHorizontalMoveDetector;
    private ThreeFingersHorizontalMoveDetector mThreeFingersHorizontalMoveDetector;
    private NewTapTwoFingersDetector mNewTapTwoFingersDetector;
    private TwoFingersVerticalMoveDetector mTwoFingersVerticalMoveDetector;
    private SingleTapDetector mSingleTapDetector;

    private static boolean SCALING;
    private static boolean PAUSED;
    private static boolean ZOOMED;
    private static boolean FLASHED;
    private static boolean STAB;

    public MagnificadorActivity() {
        Log.i(TAG, "Instantiated new" + this.getClass());
        PAUSED = false;
        ZOOMED = false;
        THRESH = 127;
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause");
        super.onPause();
        mView.releaseCamera();
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_camera);


        Log.i(TAG, "Trying to load OpenCV library");
        if (!OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_2, this, mOpenCVCallBack))
        {
            Log.e(TAG, "Cannot connect to OpenCV Manager");
        }

        mMoveDetector = new MoveDetector(getApplicationContext());
        mScaleDetector = new ScaleGestureDetector(getApplicationContext(),new simpleOnScaleGestureListener());
        mDiagonalMoveDetector = new DiagonalMoveDetector(getApplicationContext());
        mTwoFingersHorizontalMoveDetector = new TwoFingersHorizontalMoveDetector(getApplicationContext());
        mThreeFingersHorizontalMoveDetector = new ThreeFingersHorizontalMoveDetector(getApplicationContext());
        mNewTapTwoFingersDetector = new NewTapTwoFingersDetector(getApplicationContext());
        mTwoFingersVerticalMoveDetector = new TwoFingersVerticalMoveDetector(getApplicationContext());
        mSingleTapDetector = new SingleTapDetector(getApplicationContext());

        // Establecemos el detector de pulsaciones sobre la variable TittleID
        // del layout de la aplicación
        SurfaceView mySurface = (SurfaceView) findViewById(R.id.surface);
        mySurface.setOnTouchListener(new View.OnTouchListener() {

            @Override
            ///Método encargado de detectar el evento y reconocerlo
            public boolean onTouch(View arg0, MotionEvent event) {
                onTouchEvent(event);
                return true;
            }
        });
    }

    public boolean onTouchEvent(MotionEvent event) {

        /* Activamos los detectores o listeners */
        mScaleDetector.onTouchEvent(event);
        /*if(mScaleDetector.isInProgress()){
            return true;
        }*/
        if(PAUSED && getScale()>1.0f){
            mMoveDetector.onTouchEvent(event,mView, getScale());
        }
        if(!PAUSED && !mScaleDetector.isInProgress()){
            mSingleTapDetector.onTouchEvent(event,mView);
            mDiagonalMoveDetector.onTouchEvent(event,mView);
        }

        mTwoFingersHorizontalMoveDetector.onTouchEvent(event, mView);
        mThreeFingersHorizontalMoveDetector.onTouchEvent(event, mView);

        if(CURRENT_COLOR == CameraColors.HIGHCONTRAST){
            mTwoFingersVerticalMoveDetector.onTouchEvent(event,mView);
        }
        mNewTapTwoFingersDetector.onTouchEvent(event,mView);

        return true;
    }

    public class simpleOnScaleGestureListener extends
            ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {

            px = mView.getWidth()/2;
            py = mView.getHeight()/2;

            x = mPOSX;
            y = mPOSY;
            SCALING = true;
            return true;
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            mScaleFactor *= detector.getScaleFactor();

            SCALE = mScaleFactor;

            mScaleFactor = Math.max(1.0f, Math.min(mScaleFactor, 10.0f));

            //ZOOM IN
            if(mOldScaleFactor < mScaleFactor){

                /*if(x < 0){
                    x = mPOSX + (px/2 - px/(float)(Math.pow(2,mScaleFactor-2)));
                }
                else if(x > 0){
                    x = mPOSX + (px/2 - px/(float)(Math.pow(2,mScaleFactor-2)));
                }
                if(y < 0){
                    y = mPOSY + (py/2 - py/(float)(Math.pow(2,mScaleFactor-2)));
                }
                else if(y > 0){
                    y = mPOSY + (py/2 - py/(float)(Math.pow(2,mScaleFactor-2)));
                }

                Log.i(TAG, "SCALE - mPosX = " + Float.toString(x));
                Log.i(TAG, "SCALE - mPosY = " + Float.toString(y));*/

                mView.translate(mPOSX,mPOSY);
                mView.scale(mScaleFactor,mScaleFactor,px,py);
                Log.i(TAG, "Scale - mScaleFactor = " + Float.toString(mScaleFactor));

            }

            //ZOOM OUT
            else if(mOldScaleFactor > mScaleFactor){
                if(x < (-px)*(mScaleFactor-1)){
                    x = (-px)*(mScaleFactor-1);
                }
                else if(x > (mScaleFactor-1)*px){
                    x = (mScaleFactor-1)*px;
                }
                if(y < (-py)*(mScaleFactor-1)){
                    y = (-py)*(mScaleFactor-1);
                }
                else if(y > (mScaleFactor-1)*py){
                    y = (mScaleFactor-1)*py;
                }
                mView.translate(x,y);
                mView.scale(mScaleFactor,mScaleFactor,px,py);
            }

            //Log.i(TAG, "SCALE - mPosX = " + Float.toString(mPOSX));
            //Log.i(TAG, "SCALE - mPosY = " + Float.toString(mPOSY));

            mOldScaleFactor = mScaleFactor;

            mView.invalidate();

            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            mPOSX = x;
            mPOSY = y;
            SCALING = false;
            if (mScaleFactor > 1.0){
                setToast("Zoom: " + String.format("%.1f", mScaleFactor));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(TAG, "onCreateOptionsMenu");
        macro=menu.add("macro");
        autofocus=menu.add("AF");
        cancelAF=menu.add("cancelAF");
        videofocus=menu.add("videofocus");
        lowfps=menu.add("lowfps");
        normalfps=menu.add("normalfps");
        focusarea=menu.add("focusarea");
        contrastAdd=menu.add("contrastadd");
        contrastRest=menu.add("contrastrest");
        invert=menu.add("invert");
        stabilizatiOFF=menu.add("stabOFF");
        stabilizatiON=menu.add("stabON");
        finish=menu.add("finish");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(TAG, "Menu Item selected " + item);

        if(item==macro){
            mView.macroFocus();
        }

        if(item==autofocus){
            mView.autoFocus();
        }
        if(item==cancelAF){
            mView.cancelAutoFocus();
        }
        if(item==videofocus){
            mView.videoFocus();
        }

        if(item==lowfps){
            mView.lowFps();
        }
        if(item==normalfps){
            mView.defaultFps();
        }
        if(item==focusarea){
            Rect rect=new Rect(120,110,140,115);
            mView.focusArea(rect, 1000);
        }
        if(item==contrastAdd){
            cont=cont+0.2f;
            mView.contrast(cont);
        }
        if(item==contrastRest){
            cont=cont-0.2f;
            mView.contrast(cont);
        }
        if(item==invert){
            mView.invert();
        }
        if(item==stabilizatiON){
            mView.videoStabilizationOn();
        }
        if(item==stabilizatiOFF){
            mView.videoStabilizationOff();
        }
        if(item==finish){
            mView.releaseCamera();
            finish();
        }
        return true;
    }

    /* Volume key options */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){

            if(PAUSED){
                mView.unpause();
                PAUSED = false;
                setToast("Sin pausar");
                if(MagnificadorActivity.getFlashed()){
                    mView.flashOn();
                }
            }
            else{
                mView.pause();
                PAUSED = true;
                setToast("Pausado");
                if(MagnificadorActivity.getFlashed()){
                    mView.flashOff();
                }
            }
            return true;
        }
        else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            if(!PAUSED){
                if(FLASHED){
                    mView.flashOff();
                    FLASHED = false;
                    setToast("Flash Off");
                }
                else{
                    mView.flashOn();
                    FLASHED = true;
                    setToast("Flash On");
                }
            }
            return true;
        }
        else if (keyCode == KeyEvent.KEYCODE_BACK){
            mView.releaseCamera();
            finish();
        }
        else if (keyCode == KeyEvent.KEYCODE_MENU){
            Intent intent = new Intent(MagnificadorActivity.this,MainActivity.class);
            //Comenzamos la nueva actividad
            startActivity(intent);
            finish();
        }
        return false;
    }

    public void setToast(String msg){
        final Toast toast1 = new Toast(getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,
                (ViewGroup) findViewById(R.id.lytLayout));
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

    /* Getters and Setters*/

    public static float getScale() {
        return SCALE;
    }

    public static boolean getPaused(){
        return PAUSED;
    }

    public static void setPaused(boolean paused){
        MagnificadorActivity.PAUSED = paused;
    }

    public static boolean isZOOMED() {
        return ZOOMED;
    }

    public static void setZOOMED(boolean ZOOMED) {
        MagnificadorActivity.ZOOMED = ZOOMED;
    }

    public static boolean getFlashed(){
        return FLASHED;
    }

    public static void setFlashed(boolean flashed){
        MagnificadorActivity.FLASHED = flashed;
    }

    public static boolean isSTAB() {
        return STAB;
    }

    public static void setSTAB(boolean STAB) {
        MagnificadorActivity.STAB = STAB;
    }

    public static boolean isSCALING() {
        return SCALING;
    }

    public static CameraColors getCURRENT_COLOR() {
        return CURRENT_COLOR;
    }

    public static void setCURRENT_COLOR(CameraColors CURRENT_COLOR) {
        MagnificadorActivity.CURRENT_COLOR = CURRENT_COLOR;
    }

    public static float getmPOSX() {
        return mPOSX;
    }

    public static void setmPOSX(float mPOSX) {
        MagnificadorActivity.mPOSX = mPOSX;
    }

    public static float getmPOSY() {
        return mPOSY;
    }

    public static void setmPOSY(float mPOSY) {
        MagnificadorActivity.mPOSY = mPOSY;
    }

    public static float getPx() {
        return px;
    }

    public static void setPx(float px) {
        MagnificadorActivity.px = px;
    }

    public static float getPy() {
        return py;
    }

    public static void setPy(float py) {
        MagnificadorActivity.py = py;
    }

    public static int getTHRESH() {
        return THRESH;
    }

    public static void setTHRESH(int THRESH) {
        MagnificadorActivity.THRESH = THRESH;
    }

    public static boolean isPrimera() {
        return primera;
    }

    public static void setPrimera(boolean primera) {
        MagnificadorActivity.primera = primera;
    }
}
