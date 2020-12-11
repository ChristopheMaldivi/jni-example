package org.tof.example;

public class DoTheJob {
    static {
        new NativeLibraryLoader().loadNativeLibrarySafely("dothejob");
    }

    public native String doTheJobNative();

    public native int doTheJobArrayNative(byte[] inputArray, int[] outputArray, int arraySize);
}
