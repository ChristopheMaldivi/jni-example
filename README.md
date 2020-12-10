We demonstrate here the use of JNI, the java native interface, with two simple functions:
 - `String doTheJobNative()` which simply returns "it works!" from native side
 - `int doTheJobArrayNative(byte[] inputArray, int[] outputArray, int arraySize)` which plays with array reading and writing


## Build and run
 1. generate JNI headers: `mvn clean compile`
 - build the native library (mac and linux are supported): `(cd native-library/ && ./build-linux.sh)`
 - run the tests on java side: `mvn clean install`


We use the following maven configuration to generate automatically during the compilation JNI headers:
```
<compilerArgs>
  <arg>-h</arg>
  <arg>jni-headers</arg>
</compilerArgs>
```


NB: you need java 11 at least to get performance measures working (relies on `Instant` class)
