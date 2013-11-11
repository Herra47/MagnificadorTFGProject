package fi.upm.tfg.magnificador;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;


import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import fi.upm.tfg.detectors.LongTapMoveDetector;
import fi.upm.tfg.detectors.MoveDetector;
import fi.upm.tfg.detectors.TapTwoFingersDetector;

public class MagnificadorActivity extends Activity {
	
	private static final String TAG = "MagnificadorActivity: ";

    /*Camera parameters*/
	private MagnificadorProcess mView;
	private MenuItem pause;
	private MenuItem rgb;
	private MenuItem unpause;
	private MenuItem gray;
	private MenuItem macro;
	private MenuItem lowfps;
	private MenuItem focusarea;
	private MenuItem bw;
	private MenuItem autofocus;
	private MenuItem videofocus;
	private MenuItem normalfps;
	private MenuItem bgr;
	private MenuItem cancelAF;
	private MenuItem flashOff;
	private MenuItem flashOn;
	private MenuItem translateTop;
	private MenuItem translateRight;
	private MenuItem translateBott;
	private MenuItem translateLeft;
	private MenuItem zoomIN;
	private MenuItem zoomOUT;
	private float dx=1;
	private float dy=1;
	private float mx=1.f;
	private float px=100;
	private float py=100;
	private float my=1.f;

    DisplayMetrics displaymetrics = new DisplayMetrics();
    int zoomY = displaymetrics.heightPixels/2;
    int zoomX = displaymetrics.widthPixels/2;

    private float mScaleFactor = 1.f;
    private double thresh;
	private double maxval=100;
	private BaseLoaderCallback  mOpenCVCallBack = new BaseLoaderCallback(this) {
		@Override
		public void onManagerConnected(int status) {
			switch (status) {
			case LoaderCallbackInterface.SUCCESS:
			{
				//			Log.i(TAG, "OpenCV loaded successfully");
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
			} break;
			default:
			{
				super.onManagerConnected(status);
			} break;
			}
		}
	};
	private MenuItem contrast;
	private MenuItem brightness;
	private float cont=1;
	private int bri;
	private MenuItem invert;
	private MenuItem contrastRest;
	private MenuItem contrastAdd;
	private MenuItem stabilizatiON;
	private MenuItem stabilizatiOFF;
	private MenuItem finish;
//    private ViewGroup.LayoutParams lp = mView.getLayoutParams();

    /* Gestures */

    private TapTwoFingersDetector mTapTwoFingersDetector;
    private LongTapMoveDetector mLongTapMoveDetector;
    private MoveDetector mMoveDetector;
    private ScaleGestureDetector mScaleDetector;

    private static boolean PAUSED;
    private static boolean ZOOMED;

	public MagnificadorActivity() {
		Log.i(TAG, "Instantiated new " + this.getClass());
        PAUSED = false;
        ZOOMED = false;
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

        mTapTwoFingersDetector = new TapTwoFingersDetector(getApplicationContext());
        mLongTapMoveDetector = new LongTapMoveDetector(getApplicationContext());
        mMoveDetector = new MoveDetector(getApplicationContext());
        mScaleDetector = new ScaleGestureDetector(getApplicationContext(),new simpleOnScaleGestureListener());

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
        //while (!mScaleDetector.isInProgress()){

        mTapTwoFingersDetector.onTouchEvent(event,mView);

        mScaleDetector.onTouchEvent(event);

        if(mLongTapMoveDetector.onTouchEvent(event,mView)){
            mView.autoFocus();
        }


        if(PAUSED&&ZOOMED){
            mMoveDetector.onTouchEvent(event,mView, mScaleFactor);
        }

        /*final int action = event.getAction();
        final int fingersCount = event.getPointerCount();

        if ((action == MotionEvent.ACTION_POINTER_UP) && (fingersCount == 2) && !mScaleDetector.isInProgress()) {
            if(PAUSED){
                mView.unpause();
                MagnificadorActivity.setPaused(false);
            }
            else{
                mView.pause();
                MagnificadorActivity.setPaused(true);
            }
            return true;
        }*/


        return true;
    }

    public class simpleOnScaleGestureListener extends
            ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            // TODO Auto-generated method stub
            //scaleGesture.setText(String.valueOf(detector.getScaleFactor()));
            mScaleFactor *= detector.getScaleFactor();

            mScaleFactor = Math.max(1.0f, Math.min(mScaleFactor, 5.0f));

            px = detector.getFocusX();
            py = detector.getFocusY();

            mView.scale(mScaleFactor,mScaleFactor,px,py);

            if (mScaleFactor > 1.0f){
                ZOOMED = true;
            }

            return true;
        }

        /*@Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            // TODO Auto-generated method stub
            scaleGesture.setVisibility(View.VISIBLE);
            return true;
        }*/

        /*@Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            // TODO Auto-generated method stub
            scaleGesture.setVisibility(View.INVISIBLE);
        }*/

    }



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.i(TAG, "onCreateOptionsMenu");
		rgb = menu.add("rgb");
		gray = menu.add("gray");
		bgr=menu.add("bgr");
		bw=menu.add("bw");
		pause = menu.add("pause");
		unpause=menu.add("unpause");
		zoomIN = menu.add("zoomIN");
		zoomOUT=menu.add("zoomout");
		translateTop = menu.add("translateTop");
		translateBott=menu.add("translateBott");
		translateRight=menu.add("translateRigh");
		translateLeft=menu.add("translateLeft");
		flashOn=menu.add("flashon");
		flashOff=menu.add("flashoff");
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
		if (item == rgb){
			mView.rgb();
		}
		if (item == gray){
			mView.gray();}

		if(item==pause){
			mView.pause();
            PAUSED = true;
		}

		if(item==zoomIN){
			mx=mx+0.2f;
			my=my+0.2f;
			px=px+20;
			py=py+20;
			mView.scale(mx, my, px, py);
            ZOOMED = true;
		}
		if(item==zoomOUT){
			mx=mx-0.2f;
			my=my-0.2f;
			px=px-20;
			py=py-20;
			mView.scale(mx, my, px, py);
		}

		if(item==translateTop){  	
			dy=dy+10;
			mView.translate(dx,dy);	           
		}
		if(item==translateLeft){  	
			dx=dx+10;
			mView.translate(dx,dy);	           
		}
		if(item==translateRight){  	
			dx=dx-10;
			mView.translate(dx,dy);	           
		}
		if(item==translateBott){  	
			dy=dy-10;
			mView.translate(dx,dy);	           
		}
		if(item==rgb){
			mView.rgb();
		}
		if(item==gray){
			mView.gray();

		}
		if(item==unpause){
			mView.unpause();
            PAUSED = false;
		}

		if(item==bw){
			thresh=thresh+20;
			maxval=maxval+50;
			mView.blackAndWhite(thresh,maxval);

		}
		if(item==flashOff){
			mView.flashOff();

		}

		if((item==flashOn)){
			mView.flashOn();

		}


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

		if(item==bgr){
			mView.bgr();

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
			if(mView.videoStabilizationOn()){
				
					}
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

    public static boolean getPaused(){
        return PAUSED;
    }

    public static void setPaused(boolean paused){
        PAUSED = paused;
    }

    public static boolean isZOOMED() {
        return ZOOMED;
    }

    public static void setZOOMED(boolean ZOOMED) {
        MagnificadorActivity.ZOOMED = ZOOMED;
    }
}
