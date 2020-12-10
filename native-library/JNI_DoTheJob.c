#include "org_tof_example_DoTheJob.h"
#include "dothejob.h"
#include <stdio.h>

#define USE_CRITICAL_REGION

JNIEXPORT jstring JNICALL Java_org_tof_example_DoTheJob_doTheJobNative
  (JNIEnv * env, jobject obj) {
  char * resultat = doTheJobNative();
  return (*env)->NewStringUTF(env, resultat);
}

JNIEXPORT jint JNICALL Java_org_tof_example_DoTheJob_doTheJobArrayNative
  (JNIEnv *env, jobject obj, jbyteArray p_jniInputArray, jintArray p_jniOutputArray, jint arraySize) {

#ifdef USE_CRITICAL_REGION
    jbyte *inputArray = (*env)->GetPrimitiveArrayCritical(env, p_jniInputArray, 0);
    jint *outputArray = (*env)->GetPrimitiveArrayCritical(env, p_jniOutputArray, 0);

    int result = doTheJobArrayNative((unsigned char *)inputArray, outputArray, arraySize);

    (*env)->ReleasePrimitiveArrayCritical(env, p_jniInputArray, inputArray, JNI_ABORT);
    (*env)->ReleasePrimitiveArrayCritical(env, p_jniOutputArray, outputArray, 0);

    return result;
#else
    jbyte *inputArray = (*env)->GetByteArrayElements(env, p_jniInputArray, 0);
    jint *outputArray = (*env)->GetIntArrayElements(env, p_jniOutputArray, 0);

    int result = doTheJobArrayNative((unsigned char *)inputArray, outputArray, arraySize);

    (*env)->ReleaseByteArrayElements(env, p_jniInputArray, inputArray, JNI_ABORT);
    (*env)->ReleaseIntArrayElements(env, p_jniOutputArray, outputArray, 0);

    return result;
#endif
}
