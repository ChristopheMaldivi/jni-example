package org.tof.example;

public class DoTheJob {
    static {
        new NativeLibraryLoader().loadNativeLibrarySafely();
    }

    public native String doTheJobNative();

    //private static native int doTheJobArrayNative(byte[] inputArray, int[] outputArray, int arraySize);

    /*
    int doTheJobArray(byte[] inputArray, int[] outputArray, int arraySize) {
        return doTheJobArrayNative(inputArray, outputArray, arraySize);
    }
    */
}
