package org.tof.example;

public class LibDivsufsortNative {
    static {
        new NativeLibraryLoader().loadNativeLibrarySafely("divsufsort");
    }

    public native int divsufsort(byte[] inputArray, int[] outputArray, int arraySize);
}
