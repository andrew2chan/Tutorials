package org.example.opencv;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.opencv.opencv_core.*;

import static org.bytedeco.opencv.global.opencv_core.CV_32FC1;
import static org.bytedeco.opencv.global.opencv_core.minMaxLoc;
import static org.bytedeco.opencv.global.opencv_highgui.*;
import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgproc.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Main m = new Main();

        m.doStuff();
    }

    private void doStuff() {
        Mat img = imread(getClass().getResource("/image.jpg").getPath().substring(1), IMREAD_GRAYSCALE);
        Mat template = imread(getClass().getResource("/explore.jpg").getPath().substring(1), IMREAD_GRAYSCALE);

        final int[] methods = {TM_CCOEFF, TM_CCOEFF_NORMED, TM_CCORR, TM_CCORR_NORMED, TM_SQDIFF, TM_SQDIFF_NORMED};

        for(int m : methods) {
            Mat img2 = img.clone();

            Size size = new Size(img2.cols() - template.cols() + 1, img2.rows() - template.rows() + 1);
            Mat result = new Mat(size, CV_32FC1);

            matchTemplate(img2, template, result, m);

            DoublePointer minVal = new DoublePointer();
            DoublePointer maxVal = new DoublePointer();
            Point min = new Point();
            Point max = new Point();
            minMaxLoc(result, minVal, maxVal, min, max, null);
            rectangle(img2, new Rect(max.x(), max.y(), template.cols(), template.rows()), new Scalar(255,255,255,1), 2, 0, 0);

            imshow("Match", img2);
            waitKey(0);
            destroyAllWindows();
        }
    }
}