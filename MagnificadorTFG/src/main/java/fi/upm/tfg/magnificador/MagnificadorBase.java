
/**
 * @author Damian Trzpis
 * Universidad Politecnica de Madrid
 * Final Project-Magnificador 
 */
package fi.upm.tfg.magnificador;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.ImageFormat;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.os.Build;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public abstract class MagnificadorBase extends SurfaceView implements SurfaceHolder.Callback, Runnable{
	private static final String TAG = "Sample::SurfaceView";

	private Camera              mCamera;
	private SurfaceHolder       mHolder;
	private int                 mFrameWidth;
	private int                 mFrameHeight;
	private byte[]	        	mFrame;
	private boolean             mThreadRun;
	private byte[]              mBuffer;
	private Bitmap				bitmap;
	private boolean			    pause=false;
	private boolean 			scale=false;
	private float 				px=1;
	private float 				py=1;
	private float 				sx=1;
	private float 				sy=1;
	private float				dx=1;
	private float 				dy=1;
	private boolean 			translate=false;
	private int[] 				defaultFps={0,0};
	private int 				option;
	private boolean 			customize;
	private double 				threshold=0;
	private double 				maxval;
	private float 				contrast=1;
	private float 				t;
	private float 				a=0;





	public MagnificadorBase(Context context) {
		super(context);
		mHolder = getHolder();
		mHolder.addCallback(this);
		Log.i(TAG, "Instantiated new " + this.getClass());
	}



	/**Internal method, do not modify.
	 **/
	@TargetApi(11)
	protected void setPreview() throws IOException {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
			mCamera.setPreviewTexture( new SurfaceTexture(10) );
		else
			mCamera.setPreviewDisplay(null);
	}




	/**Internal method, do not modify.
	 **/
	protected void setupCamera(int width, int height) {
		Log.i(TAG, "setupCamera");
		synchronized (this) {
			if (mCamera != null) {
				videoFocus();
				Parameters params = mCamera.getParameters();
				List<Camera.Size> sizes = params.getSupportedPreviewSizes();
				mFrameWidth = width;
				mFrameHeight = height;

				// selecting optimal mCamera preview size
				{
					int  minDiff = Integer.MAX_VALUE;
					for (Camera.Size size : sizes) {
						if (Math.abs(size.height - height) < minDiff) {
							mFrameWidth = size.width;
							mFrameHeight = size.height;
							minDiff = Math.abs(size.height - height);
						}
					}
				}

				params.setPreviewSize(getFrameWidth(), getFrameHeight());


				mCamera.setParameters(params);
				/* Now allocate the buffer */
				params = mCamera.getParameters();
				int size = params.getPreviewSize().width * params.getPreviewSize().height;
				size  = size * ImageFormat.getBitsPerPixel(params.getPreviewFormat()) / 8;
				mBuffer = new byte[size];
				/* The buffer where the current frame will be copied */

				mFrame = new byte [size];

				mCamera.addCallbackBuffer(mBuffer);

				try {
					setPreview();
				} catch (IOException e) {
					Log.e(TAG, "mCamera.setPreviewDisplay/setPreviewTexture fails: " + e);
				}

				/* Notify that the preview is about to be started and deliver preview size */
				//onPreviewStarted(params.getPreviewSize().width, params.getPreviewSize().height);

				/* Now we can start a preview */
				mCamera.startPreview();
			}
		}
	}

	/**Internal method, do not modify.
	 **/
	public void surfaceChanged(SurfaceHolder _holder, int format, int width, int height) {
		Log.i(TAG, "surfaceChanged");
		setupCamera(width, height);
	}

	/**Internal method, do not modify.
	 **/
	public void surfaceCreated(SurfaceHolder holder) {
		Log.i(TAG, "surfaceCreated");
		(new Thread(this)).start();
	}

	/**Internal method, do not modify.
	 **/
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.i(TAG, "surfaceDestroyed");
		releaseCamera();
	}

	/**Internal method, do not modify. 
	 **/
	protected int getFrameWidth() {
		return mFrameWidth;
	}

	/**Internal method, do not modify.
	 **/
	public int getFrameHeight() {
		return mFrameHeight;
	}

	/**Internal method, do not modify.
	 **/
	protected abstract Bitmap processFrame(byte[] data, int option, double threshold, double maxval);


	/**Internal method, do not modify
	 **/
	public void run() {
		mThreadRun = true;
		Log.i(TAG, "Starting processing thread");

		while (mThreadRun) {
			Bitmap bmp = null;

			if(!pause){
				synchronized (this) {
					try {
						this.wait();

						bmp = processFrame(mFrame,option, threshold,maxval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}


			if(customize){

				Log.i(TAG, "inside process: customize");
				bitmap= processFrame(mFrame,option,threshold,maxval);
				customize=false;
				Canvas canvas = mHolder.lockCanvas();
				if (canvas != null) {

					canvas.translate(dx, dy);
					canvas.scale(sx, sy, px, py);
					Paint paint=new Paint();
					ColorMatrix cm=new ColorMatrix();

					cm.set(new float[]{contrast,0,0,0,a,
                                        0,contrast,0,0,a,
                                        0,0,contrast,0,a,
                                        0,0,0,		1,0,
                                        t, t, t,0,1});
					paint.setColorFilter(new ColorMatrixColorFilter(cm));
					canvas.drawBitmap(bitmap, (canvas.getWidth() - getFrameWidth()) / 2, (canvas.getHeight() - getFrameHeight()) / 2, paint);
					mHolder.unlockCanvasAndPost(canvas);
				}
			}

			if(pause && scale){
				Log.i(TAG, "inside process: scale");
				Canvas canvas = mHolder.lockCanvas();
				if (canvas != null) {
					canvas.translate(dx, dy);
					canvas.scale(sx, sy, px, py);
					Paint paint=new Paint();
					ColorMatrix cm=new ColorMatrix();

					cm.set(new float[]{contrast,0,0,0,a,
							0,contrast,0,0,a,
							0,0,contrast,0,a,
							0,0,0,		1,0,
							t, t, t,0,1});
					paint.setColorFilter(new ColorMatrixColorFilter(cm));
					canvas.drawBitmap(bitmap, (canvas.getWidth() - getFrameWidth()) / 2, (canvas.getHeight() - getFrameHeight()) / 2, paint);
					mHolder.unlockCanvasAndPost(canvas);
					scale=false;

				}
			}

			if(pause && translate){
				Log.i(TAG, "inside process:translate");
				Canvas canvas = mHolder.lockCanvas();
				if (canvas != null) {
					canvas.translate(dx, dy);
					canvas.scale(sx, sy, px, py);
					Paint paint=new Paint();
					ColorMatrix cm=new ColorMatrix();

					cm.set(new float[]{contrast,0,0,0,a,
							0,contrast,0,0,a,
							0,0,contrast,0,a,
							0,0,0,		1,0,
							t, t, t,0,1});
					paint.setColorFilter(new ColorMatrixColorFilter(cm));
					canvas.drawBitmap(bitmap, (canvas.getWidth() - getFrameWidth()) / 2, (canvas.getHeight() - getFrameHeight()) / 2, paint);
					mHolder.unlockCanvasAndPost(canvas);
					translate=false;
				}
			}

			if ((!pause && bmp!=null) ) {
				Canvas canvas = mHolder.lockCanvas();
				if (canvas != null ) {
					Paint paint=new Paint();
					ColorMatrix cm=new ColorMatrix();

					cm.set(new float[]{contrast,0,0,0,a,
							0,contrast,0,0,a,
							0,0,contrast,0,a,
							0,0,0,		1,0,
							t, t, t,0,1});


					paint.setColorFilter(new ColorMatrixColorFilter(cm));
					bitmap=bmp;
					canvas.scale(sx,sy,px,py);
					canvas.drawBitmap(bmp, (canvas.getWidth() - getFrameWidth()) / 2, (canvas.getHeight() - getFrameHeight()) / 2, paint);
					mHolder.unlockCanvasAndPost(canvas);
				}
			}
		}

		Log.i(TAG, "Finishing processing thread");

	}




	/**This method initialize the camera and copy the actual frame to mFrame byte array. Should be called before any other method.
	 **/
	public boolean openCamera() {
		Log.i(TAG, "openCamera");
		releaseCamera();
		mCamera = Camera.open();
		if(mCamera == null) {
			Log.e(TAG, "Can't open mCamera!");
			return false;
		}

		mCamera.setPreviewCallbackWithBuffer(new PreviewCallback() {
			public void onPreviewFrame(byte[] data, Camera mCamera) {

				synchronized (MagnificadorBase.this) {
					try {
						if(!pause){
							System.arraycopy(data, 0, mFrame, 0, data.length);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}

					MagnificadorBase.this.notify(); 
				}
				mCamera.addCallbackBuffer(mBuffer);
			}
		});
		return true;
	}



	/**Releases the camera.
	 **/
	public void releaseCamera() {
		Log.i(TAG, "releaseCamera");
		mThreadRun = false;
		synchronized (this) {
			if (mCamera != null) {
				mCamera.stopPreview();
				mCamera.setPreviewCallback(null);
				mCamera.release();
				mCamera = null;
			}
		}
		//onPreviewStopped();
	}


	/**This method should be called to check if the device supports video stabilization.
	 **/
	@TargetApi(15)
	public boolean isVideoStabilizationSupproted(){
		Parameters params=mCamera.getParameters();

		return params.isVideoStabilizationSupported();
	}


	/**Sets on video stabilization if supported.
	 *@return true when stabilization is set on
	 **/
	@TargetApi(15)
	public boolean videoStabilizationOn(){
		if(isVideoStabilizationSupproted()){
			Parameters params=mCamera.getParameters();
			params.setVideoStabilization(true);
			return true;
		}
		return false;
	}


	/**Sets off video stabilization.
	 *@return true when stabilization is set off
	 **/
	@TargetApi(15)
	public boolean videoStabilizationOff(){
		if(isVideoStabilizationSupproted()){
			Parameters params=mCamera.getParameters();
			params.setVideoStabilization(false);
			return true;
		}
		return false;
	}

	/**This method should be called to check if the flash is supported. 
	 * @return true when flash is supported
	 **/
	public boolean isFlashSupported(){
		Log.e(TAG,"isFlashSupported()");
		Parameters params=mCamera.getParameters();
		List<String> flashmodes = params.getSupportedFlashModes();
		if(flashmodes!=null){
			if(params.getSupportedFlashModes().contains(Parameters.FLASH_MODE_TORCH)){
				return true;
			}}
		return false;
	} 

	/**Activates flash in torch mode. 
	 * @return true when flash is activated
	 **/
	public boolean flashOn(){
		Log.e(TAG,"flashOn()");
		if(isFlashSupported()){
			Parameters params=mCamera.getParameters();
			params.setFlashMode(Parameters.FLASH_MODE_TORCH);
			mCamera.setParameters(params);
			return true;
		}
		return false;
	}


	/**Turns off the flash. 
	 * @return true when flash is turned off.
	 **/
	public boolean flashOff(){
		Log.e(TAG,"flashOff()");
		if(isFlashSupported()){
			Parameters params=mCamera.getParameters();
			params.setFlashMode(Parameters.FLASH_MODE_OFF);
			mCamera.setParameters(params);
			return true;}
		return false;

	}


	/**This method should be called to check if the video focus is supported. 
	 * @return true when video focus is supported.
	 * 
	 **/
	public boolean isVideoFocusSupported(){
		Log.e(TAG,"isVideoFocusSupported()");
		Parameters params=mCamera.getParameters();
		List<String> FocusModes = params.getSupportedFocusModes();
		if (FocusModes.contains(Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)){
			return true;
		}    
		return false;


	}


	/**This method set the focus as FOCUS_MODE_CONTINUOUS_VIDEO. 
	 * @return true when focus is set to FOCUS_MODE_CONTINUOUS_VIDEO.
	 **/
	public boolean videoFocus(){
		Log.e(TAG,"videoFocus()");
		if(isVideoFocusSupported()){
			Parameters params=mCamera.getParameters();
			params.setFocusMode(Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
			mCamera.setParameters(params);
			return true;
		}    
		return false;
	}



	/**This method should be called to check if autofocus is supported.
	 * @return true when autofocus is supported.
	 **/
	public boolean isAutoFocusSupported(){
		Log.e(TAG,"isAutoFocusSupported()");
		Parameters params=mCamera.getParameters();
		List<String> FocusModes = params.getSupportedFocusModes();
		if (FocusModes.contains(Parameters.FOCUS_MODE_AUTO)){
			return true;
		}    
		return false;
	}


	/**Sets the autofocus. 
	 * @return true when autofocus is set.
	 **/
	public boolean autoFocus(){
		Log.e(TAG,"autoFocus()");
		if(isAutoFocusSupported()){
			mCamera.autoFocus(null);
			return true;
		}
		return false;
	}


	/**Cancels the autofocus. 
	 * @return true when focus is canceled.
	 **/
	public boolean cancelAutoFocus(){
		Log.e(TAG,"cancelAutoFocus()");
		if(isAutoFocusSupported()){
			mCamera.cancelAutoFocus();
			return true;
		}
		return false;
	}


	/**Sets the lower fps range possible.
	 * @return true when the set is done correctly.
	 **/
	@TargetApi(9)
	public boolean lowFps(){
		Log.e(TAG,"lowFps()");
		Parameters params=mCamera.getParameters();
		List <int[]>list=params.getSupportedPreviewFpsRange();

		if(defaultFps[0]==0 && defaultFps[1]==0){
			params.getPreviewFpsRange(defaultFps);
		}
		if(list.size()>1){
			params.setPreviewFpsRange(list.get(0)[0], list.get(0)[1]);
			mCamera.setParameters(params);
			return true;}

		return false;

	}


	/**Sets the default fps.
	 * @return true when default fps is set.
	 **/
	@TargetApi(9)
	public boolean defaultFps(){
		Log.e(TAG,"defaultFps()");
		Parameters params=mCamera.getParameters();
		if(defaultFps[0]!=0 && defaultFps[1]!=0){
			params.setPreviewFpsRange(defaultFps[0],defaultFps[1]);
			mCamera.setParameters(params);
			return true;
		}
		return false;
	}


	/**This method should be called to check if the device supports area focus.
	 * @return true when area focus is supported.
	 **/
	@TargetApi(14)
	public boolean isfocusAreaSupported(){
		Log.e(TAG,"isFocusAreaSupported()");
		Parameters params=mCamera.getParameters();
		int areas= params.getMaxNumFocusAreas();
		int max_meterings=params.getMaxNumMeteringAreas();
		if(areas>0 && max_meterings>0){
			return true;
		}
		return false;
	}

	/**This method is called to set focus to specified area.
	 * @param rect the bounds of the area
	 * @param weight the weight of the area
	 * @return true when focus is set to given area.
	 **/
	@TargetApi(14)
	public boolean focusArea(Rect rect,int weight){
		Log.e(TAG,"focusArea()");
		if(isfocusAreaSupported()){
			Parameters params=mCamera.getParameters();
			List<Camera.Area> areas=new ArrayList<Camera.Area>();
			Camera.Area area=new Camera.Area(rect,weight);
			areas.add(area);
			params.setFocusAreas(areas);
			params.setMeteringAreas(areas);
			mCamera.setParameters(params);
			return true;
		}
		return false;
	}

	/**This method should be called to check if the device supports macro focus.
	 * @return true when macro focus is supported.
	 **/
	public boolean isMacroFocusSupported(){
		Log.e(TAG,"isMacroFocusSupported()");
		Parameters params=mCamera.getParameters();

		List<String>focus_modes=params.getSupportedFocusModes();
		if(focus_modes.contains(Parameters.FOCUS_MODE_MACRO)){
			return true;
		}
		return false;

	}

	/**Sets focus to macro mode.
	 * @return true when focus is set to FOCUS_MODE_MACRO.
	 **/
	public boolean macroFocus(){
		Log.e(TAG,"macroFocus()");
		if(isMacroFocusSupported()){
			Parameters params=mCamera.getParameters();
			params.setFocusMode(Parameters.FOCUS_MODE_MACRO);
			mCamera.setParameters(params);
			return true;

		}
		return false;
	}

	/**Preview in gray.
	 **/
	public void gray(){
		normal();
		Log.e(TAG,"gray()");
		customize=true;
		option=1;
	}

	/**Preview in RGB.
	 **/
	public void rgb(){
		normal();
		Log.e(TAG,"rgb()");
		customize=true;
		option=0;
	}

	/**Preview in black and white.
	 * @param threshold Threshold value 
	 **/
	public void blackAndWhite(double threshold, double maxval){
		normal();
		this.maxval=maxval;
		this.threshold=threshold;
		Log.e(TAG,"blackAndWhite()");
		customize=true;
		option=2;
	}

    public void blackAndWhiteInvert(double threshold, double maxval){
        normal();
        this.maxval=maxval;
        this.threshold=threshold;
        Log.e(TAG,"blackAndWhiteInvert()");
        customize=true;
        option=6;
    }

    /**Preview in blue and yellow.
     * @param threshold Threshold value
     **/
    public void blueAndYellow(double threshold, double maxval){
        normal();
        this.maxval=maxval;
        this.threshold=threshold;
        Log.e(TAG,"blueAndYellow()");
        customize=true;
        option=4;
    }

    /**Preview in blue and yellow.
     * @param threshold Threshold value
     **/
    public void redAndYellow(double threshold, double maxval){
        normal();
        this.maxval=maxval;
        this.threshold=threshold;
        Log.e(TAG,"redAndYellow()");
        customize=true;
        option=5;
    }

	/**Preview in BGR.
	 **/
	public void bgr(){
		normal();
		Log.e(TAG,"bgr()");
		customize=true;
		option=3;
	}

	/**Pauses the preview.
	 **/
	public void pause(){
		Log.e(TAG,"pause()");
		pause=true;
	}

	/**Unpauses the preview.
	 **/
	public void unpause(){
		Log.e(TAG,"unpause()");
		pause=false;
	}

	/**Translates the paused preview. Take care of parameters passed - no limits are fixed.
	 * @param dx distance to translate in x axis.
	 * @param dy distance to translate in y axis.
	 **/
	public void translate(float dx ,float dy){
		Log.e(TAG,"translate()");
		this.dx=dx;
		this.dy=dy;
		translate=true;
	}


	/**Scales the preview. Take care of parameters passed - no limits are fixed.
	 * @param sx	The amount to scale in X
	 * @param sy	The amount to scale in Y
	 * @param px	The x-coord for the pivot point (unchanged by the scale)
	 * @param py	The y-coord for the pivot point (unchanged by the scale)
	 **/
	public void scale(float sx,float sy, float px, float py){
		Log.e(TAG,"magnify()");
		this.sx=sx;
		this.sy=sy;
		this.px=px;
		this.py=py;
		scale=true;

	}

	/**Modifies the contrast of the preview in RGB mode.
	 * @param contrast value of the contrast
	 */
	public void contrast(float contrast){
		rgb();
		t=(1-contrast)/2;
		this.contrast=contrast;

	}
	/**Inverts the colors
	 */
	public void invert(){
		rgb();
		this.contrast=-1;
		this.t=0;
		this.a=255.0f;
		customize=true;
	}

	/**Sets the contrast to normal mode. This method is called in every preview mode.
	 */
	protected void normal(){
		this.contrast=1;
		this.a=0;
	}
}
