#!/bin/sh
JAVA_HOME=$(java -XshowSettings:properties -version 2>&1 > /dev/null | awk '/java.home/ {print $3}')
echo JAVA_HOME=$JAVA_HOME
echo

rm -f *.o *.so
gcc -I $JAVA_HOME/include -I $JAVA_HOME/include/linux -Wall -c dothejob.c JNI_DoTheJob.c | exit 1
gcc -shared dothejob.o JNI_DoTheJob.o -o libdothejob.so | exit 2
cp libdothejob.so ../src/main/resources | exit 3
rm -f *.o *.so

