package org.example.opencv;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;

import java.net.URL;

import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import static org.bytedeco.opencv.global.opencv_imgproc.GaussianBlur;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Main m = new Main();

        m.doStuff("tree.jpg");
    }

    private void doStuff(String fileName) {
        URL resourceURL = getClass().getResource("/" + fileName);
        if (resourceURL == null) {
            throw new NullPointerException("Resource not found: " + fileName);
        }

        String imagePath = resourceURL.getPath().substring(1);
        Mat image = imread(imagePath);

        // Check if the image was successfully loaded
        if (image != null && !image.empty()) {
            // Apply Gaussian blur
            GaussianBlur(image, image, new Size(3, 3), 0);

            // Save processed image to a specific location
            String outputFileName = "processed_tree.jpg";
            imwrite(outputFileName, image);
            System.out.println("Processed image saved to: " + outputFileName);
        } else {
            System.out.println("Failed to load image: " + fileName);
        }
    }
}