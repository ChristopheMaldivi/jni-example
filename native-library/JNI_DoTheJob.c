#include "org_tof_example_DoTheJob.h"
#include "dothejob.h"

JNIEXPORT jstring JNICALL Java_org_tof_example_DoTheJob_doTheJobNative
  (JNIEnv * env, jobject obj) {
  char * resultat = doTheJobNative();
  return (*env)->NewStringUTF(env, resultat);
}