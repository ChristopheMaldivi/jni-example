#!/bin/sh
rm -f *.o *.dylib
gcc -I $JAVA_HOME/include -I $JAVA_HOME/include/darwin -Wall -c dothejob.c JNI_DoTheJob.c | exit 1
gcc -dynamiclib dothejob.o JNI_DoTheJob.o -o libdothejob.dylib | exit 2
cp libdothejob.dylib ../src/main/resources | exit 3
rm -f *.o *.dylib
