package org.tof.example;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

class NativeLibraryLoader {


    void loadNativeLibrarySafely(String libName) {
        String libPath = String.format("%s", getLibName(libName));
        try {
            tryToLoadNativeLibrary(libPath);
        } catch (UnsatisfiedLinkError e) {
            failedToLoadNativeLib(libPath, "(load failed)");
        } catch (IOException e) {
            failedToLoadNativeLib(libPath, "(failed to write lib to local directory)");
        }
    }

    private void tryToLoadNativeLibrary(String libPath) throws IOException {
        ClassLoader loader = getClass().getClassLoader();
        InputStream is = loader.getResourceAsStream(libPath);
        if (is == null) {
            failedToLoadNativeLib(libPath, "(resource not found)");
        } else {
            extractLibrary(is, libPath);
        }
    }

    private void extractLibrary(InputStream is, String libPath) throws IOException {
        FileOutputStream os = new FileOutputStream(libPath);
        IOUtils.copy(is, os);
        is.close();
        os.close();
        System.load((new File("." + File.separator + libPath).getAbsolutePath()));
        System.out.println("[OK] " + libPath + " loaded, turbo mode enabled :)");
    }

    private void failedToLoadNativeLib(String libPath, String cause) {
        System.out.printf("[WARN] failed to load %s%s, fall back to pure (but slow) java implementation%n", libPath, cause);
    }

    private static String getLibName(String libName) {
        String os = System.getProperty("os.name").toLowerCase();
        return String.format("lib%s.%s", libName, os.contains("mac") ? "dylib" : os.contains("setwidth") ? "dll" : "so");
    }
}
