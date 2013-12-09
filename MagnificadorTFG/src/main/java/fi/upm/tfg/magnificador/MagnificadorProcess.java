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

            /* HIGH CONTRAST COLORS */

            //WHITE AND BLACK
            case 2:
                Imgproc.cvtColor(mYuv, mYuv, Imgproc.COLOR_GRAY2RGB, 0);
                Imgproc.threshold(mYuv, mYuv, threshold, maxval, Imgproc.THRESH_BINARY);
                break;
            //BLACK AND YELLOW
            case 3:
                Imgproc.threshold(mYuv, mYuv, threshold, maxval, Imgproc.THRESH_BINARY);
                Mat by = new Mat(mYuv.rows(),mYuv.cols(), CvType.CV_8UC3, new Scalar(255,255,0));
                //set the mask to black
                by.setTo(new Scalar(0,0,0), mYuv);
                mYuv = by.clone();
                break;
            //WHITE AND BLACK
            case 4:
                Imgproc.threshold(mYuv, mYuv, threshold, maxval, Imgproc.THRESH_BINARY);
                Mat wb = new Mat(mYuv.rows(),mYuv.cols(), CvType.CV_8UC3, new Scalar(255,255,255));
                //set the mask to black
                wb.setTo(new Scalar(0,0,0), mYuv);
                mYuv = wb.clone();
                break;
            //YELLOW AND BLACK
            case 5:
                Imgproc.threshold(mYuv, mYuv, threshold, maxval, Imgproc.THRESH_BINARY);
                Mat yb = new Mat(mYuv.rows(),mYuv.cols(), CvType.CV_8UC3, new Scalar(0,0,0));
                //set the mask to black
                yb.setTo(new Scalar(255,255,0), mYuv);
                mYuv = yb.clone();
                break;
            //BLUE AND YELLOW
            case 6:
                Imgproc.threshold(mYuv, mYuv, threshold, maxval, Imgproc.THRESH_BINARY);
                //set pixels masked by blackWhite to yellow
                Mat bgr = new Mat(mYuv.rows(),mYuv.cols(), CvType.CV_8UC3, new Scalar(255,255,0));
                //set the mask to blue
                bgr.setTo(new Scalar(0,0,127), mYuv);
                mYuv = bgr.clone();
                break;
            //BLUE AND WHITE
            case 7:
                Imgproc.threshold(mYuv, mYuv, threshold, maxval, Imgproc.THRESH_BINARY);
                //set pixels masked by blackWhite to yellow
                Mat bluew = new Mat(mYuv.rows(),mYuv.cols(), CvType.CV_8UC3, new Scalar(255,255,255));
                //set the mask to blue
                bluew.setTo(new Scalar(0,0,127), mYuv);
                mYuv = bluew.clone();
                break;
            //YELLOW AND BLUE
            case 8:
                Imgproc.threshold(mYuv, mYuv, threshold, maxval, Imgproc.THRESH_BINARY);
                //set pixels masked by blackWhite to yellow
                Mat yblue = new Mat(mYuv.rows(),mYuv.cols(), CvType.CV_8UC3, new Scalar(0,0,127));
                //set the mask to blue
                yblue.setTo(new Scalar(255,255,0), mYuv);
                mYuv = yblue.clone();
                break;
            //WHITE AND BLUE
            case 9:
                Imgproc.threshold(mYuv, mYuv, threshold, maxval, Imgproc.THRESH_BINARY);
                //set pixels masked by blackWhite to yellow
                Mat wblue = new Mat(mYuv.rows(),mYuv.cols(), CvType.CV_8UC3, new Scalar(0,0,127));
                //set the mask to blue
                wblue.setTo(new Scalar(255,255,255), mYuv);
                mYuv = wblue.clone();
                break;


            //RED AND YELLOW
            case 10:
                Imgproc.threshold(mYuv, mYuv, threshold, maxval, Imgproc.THRESH_BINARY);
                //set pixels masked by blackWhite to yellow
                Mat ry = new Mat(mYuv.rows(),mYuv.cols(), CvType.CV_8UC3, new Scalar(255,255,0));
                //set the mask to red
                ry.setTo(new Scalar(255,0,0), mYuv);
                mYuv = ry.clone();
                break;
            //RED AND WHITE
            case 11:
                Imgproc.threshold(mYuv, mYuv, threshold, maxval, Imgproc.THRESH_BINARY);
                //set pixels masked by blackWhite to yellow
                Mat rw = new Mat(mYuv.rows(),mYuv.cols(), CvType.CV_8UC3, new Scalar(255,255,255));
                //set the mask to red
                rw.setTo(new Scalar(255,0,0), mYuv);
                mYuv = rw.clone();
                break;
            //YELLOW AND RED
            case 12:
                Imgproc.threshold(mYuv, mYuv, threshold, maxval, Imgproc.THRESH_BINARY);
                //set pixels masked by blackWhite to yellow
                Mat yr = new Mat(mYuv.rows(),mYuv.cols(), CvType.CV_8UC3, new Scalar(255,0,0));
                //set the mask to red
                yr.setTo(new Scalar(255,255,0), mYuv);
                mYuv = yr.clone();
                break;
            //WHITE AND RED
            case 13:
                Imgproc.threshold(mYuv, mYuv, threshold, maxval, Imgproc.THRESH_BINARY);
                //set pixels masked by blackWhite to yellow
                Mat wr = new Mat(mYuv.rows(),mYuv.cols(), CvType.CV_8UC3, new Scalar(255,0,0));
                //set the mask to red
                wr.setTo(new Scalar(255,255,255), mYuv);
                mYuv = wr.clone();
                break;
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
