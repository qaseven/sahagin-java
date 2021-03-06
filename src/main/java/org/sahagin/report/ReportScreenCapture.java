package org.sahagin.report;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.sahagin.share.Logging;

public class ReportScreenCapture {
    private static Logger logger = Logging.getLogger(ReportScreenCapture.class.getName());

    // relative path from HTML report file
    private String path;
    private String imageId;
    private int imageWidth;
    private int imageHeight;
    private int executionTime;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }

    public void setImageSizeFromImageFile(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            setImageWidth(image.getWidth());
            setImageHeight(image.getHeight());
        } catch (IOException e) {
            // image does not exist
            logger.log(Level.INFO, "", e);
            setImageWidth(0);
            setImageHeight(0);
        }
    }

    public void setImageSizeFromImageStream(InputStream stream) {
        try {
            BufferedImage image = ImageIO.read(stream);
            setImageWidth(image.getWidth());
            setImageHeight(image.getHeight());
        } catch (IOException e) {
            // image does not exist
            logger.log(Level.INFO, "", e);
            setImageWidth(0);
            setImageHeight(0);
        }
    }


}
