package org.sahagin.share;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.Manifest;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.io.IOUtils;

public class CommonUtils {

    // cannot use Path.relativize since Sahagin support Java 1.6 or later
    public static File relativize(File target, File baseDir) {
        String separator = File.separator;
        String absTargetPath = target.getAbsolutePath();
        absTargetPath = FilenameUtils.normalizeNoEndSeparator(
                FilenameUtils.separatorsToSystem(absTargetPath));
        String absBasePath = baseDir.getAbsolutePath();
        absBasePath = FilenameUtils.normalizeNoEndSeparator(
                FilenameUtils.separatorsToSystem(absBasePath));

        if (absTargetPath.equals(absBasePath)) {
            throw new IllegalArgumentException("target and base are equal: " + absTargetPath);
        }

        String[] absTargets = absTargetPath.split(separator);
        String[] absBases = absBasePath.split(separator);

        int minLength = Math.min(absTargets.length, absBases.length);

        int lastCommonRoot = -1;
        for (int i = 0; i < minLength; i++) {
            if (absTargets[i].equals(absBases[i])) {
                lastCommonRoot = i;
            } else {
                break;
            }
        }

        if (lastCommonRoot == -1) {
            // This case can happen on Windows when drive of two file paths differ.
            throw new IllegalArgumentException("no common root");
        }

        String relativePath = "";

        for (int i = lastCommonRoot + 1; i < absBases.length; i++) {
            relativePath = relativePath + ".." + separator;
        }

        for (int i = lastCommonRoot + 1; i < absTargets.length; i++) {
            relativePath = relativePath + absTargets[i];
            if (i != absTargets.length - 1) {
                relativePath = relativePath + separator;
            }
        }

        return new File(relativePath);
    }

    public static Manifest readManifestFromExternalJar(File jarFile) {
        if (!jarFile.getName().endsWith(".jar")) {
            throw new IllegalArgumentException("not jar file : " + jarFile);
        }
        InputStream in = null;
        String urlStr = "jar:file:" + jarFile.getAbsolutePath() + "!/META-INF/MANIFEST.MF";
          try {
            URL inputURL = new URL(urlStr);
            JarURLConnection conn = (JarURLConnection) inputURL.openConnection();
            in = conn.getInputStream();
            return new Manifest(in);
          } catch (MalformedURLException e) {
              throw new RuntimeException(e);
          } catch (IOException e) {
              throw new RuntimeException(e);
          } finally {
              IOUtils.closeQuietly(in);
          }
    }

}
