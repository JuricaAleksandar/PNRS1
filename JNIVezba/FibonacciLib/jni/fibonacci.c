#include "com.example.ra47_2014.FibonacciNative.h"

#include <jni.h>

JNIEXPORT jint JNICALL Java_com_example_jnivezba_FibonacciNative_get
  (JNIEnv *env, jobject obj, jint n)
{
    return (jint) floor(pow(GOLDEN_RATIO, (double) n)/SQRT_5 + 0.5);
}

