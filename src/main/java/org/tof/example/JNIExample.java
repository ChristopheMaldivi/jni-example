package org.tof.example;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

public class JNIExample {

  public static void main(String... args) throws IOException {
      if (args.length == 0) {
          System.out.println("Please provide input file name");
          return;
      }

      final String filename = args[0];
      final byte[] bytes = FileUtils.readFileToByteArray(new File(filename));

      byte[] saisInput = getDoubleBytesCopy(bytes);
      int length = saisInput.length;
      int[] suffixArrayIndices = new int[length];

      final LibDivsufsortNative libDivsufsortNative = new LibDivsufsortNative();
      // warmup
      libDivsufsortNative.divsufsort(saisInput, suffixArrayIndices, length);

      Instant t0 = Instant.now();
      libDivsufsortNative.divsufsort(saisInput, suffixArrayIndices, length);
      Instant t1 = Instant.now();

      long diffMicro = diffMicro(t0, t1);
      long s = diffMicro / 1000000;
      long ms = diffMicro / 1000 - s * 1000;
      long us = diffMicro - ms * 1000 - s * 1000000;
      System.out.printf("java libdivsufsort JNI took %d s %d ms %d us for file %s%n", s, ms, us, filename);

      StringBuilder b = new StringBuilder();
      for (int val : suffixArrayIndices) {
          if (val < suffixArrayIndices.length / 2) {
              b.append(val);
              b.append(" ");
          }
      }
      b.append("\n");
      FileUtils.write(new File(filename + ".sa"), b.toString(), "utf8");
  }

    private static byte[] getDoubleBytesCopy(byte[] bytes) {
        int length = bytes.length;
        byte[] doubleBytes = new byte[length * 2];
        System.arraycopy(bytes, 0, doubleBytes, 0, length);
        System.arraycopy(bytes, 0, doubleBytes, length, length);
        return doubleBytes;
    }

    private static long diffMicro(Instant t1, Instant t2) {
        Instant diff = t2.minusSeconds(t1.getEpochSecond());
        diff = diff.minusNanos(t1.getNano());
        return diff.getEpochSecond() * 1000 * 1000 + diff.getNano() / 1000;
    }
}
