/**
 * @author Damian Trzpis
 * Universidad Politecnica de Madrid
 * Final Project-Magnificador 
 */

package fi.upm.tfg.magnificador;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

public class MagnificadorProcess extends MagnificadorBase{

    private static final String TAG = "Magnificador";


    public MagnificadorProcess(Context context) {
        super(context);
    }

    @Override
    protected Bitmap processFrame(byte[] data, int option,double threshold, double maxval) {

        Mat mYuv = new Mat(getFrameHeight()+getFrameHeight()/2, getFrameWidth(), CvType.CV_8UC1);
        mYuv.put(0,0,data);
        switch(option){
            //RGB
            case 0:
                Imgproc.cvtColor(mYuv, mYuv, Imgproc.COLOR_YUV420sp2RGB, 0);
                break;
            //GRAY
            case 1:
                Imgproc.cvtColor(mYuv, mYuv, Imgproc.COLOR_GRAY2RGB, 0);
                break;
            //BLACK AND WHITE
            case 2:
                //Imgproc.cvtColor(mYuv, mYuv, Imgproc.COLOR_GRAY2RGB, 0);
                Imgproc.threshold(mYuv, mYuv, threshold, maxval, Imgproc.THRESH_BINARY);
                break;
            //BGR
            case 3:
                Imgproc.cvtColor(mYuv, mYuv, Imgproc.COLOR_YUV420sp2BGR, 0);
                break;
            //BLUE AND YELLOW
            case 4:
                //Imgproc.cvtColor(mYuv, mYuv, Imgproc.COLOR_GRAY2RGB, 0);
                Imgproc.threshold(mYuv, mYuv, threshold, maxval, Imgproc.THRESH_BINARY);
                //set pixels masked by blackWhite to yellow
                Mat bgr = new Mat(mYuv.rows(),mYuv.cols(), CvType.CV_8UC3, new Scalar(255,255,0));
                //set the mask to blue
                bgr.setTo(new Scalar(0,0,127), mYuv);
                mYuv = bgr.clone();


                //bgr.setTo(Scalar(255,0,0), blackWhite);
                //Imgproc.cvtColor(mYuv,mYuv,Imgproc.COLOR_GRAY2RGB,0);
                /*for(int i = 0; i < mYuv.cols(); i++){
                    for(int j = 0; j < mYuv.rows(); j++){
                        double[] aux = mYuv.get(j,i);
                        if (aux[0] == 0.0){
                            aux[0] = 16776960;
                        }
                        mYuv.put(i,j,aux);
                    }
                }*/

                break;
            //BLACK AND WHITE
            /*case 5:
                //Imgproc.cvtColor(mYuv, mYuv, Imgproc.COLOR_GRAY2RGB, 0);
                Imgproc.threshold(mYuv, mYuv, threshold, maxval, Imgproc.THRESH_BINARY);
                break;*/
        }


        Bitmap bmp = Bitmap.createBitmap(mYuv.cols(), mYuv.rows(), Bitmap.Config.ARGB_8888);

        try {
            Utils.matToBitmap(mYuv, bmp);
            return bmp;
        } catch(Exception e) {
            bmp.recycle();
            return null;
        }

    }



}
