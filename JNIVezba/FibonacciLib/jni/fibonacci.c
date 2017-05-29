#include "fibonacci.h"

#include <jni.h>
#include <math.h>


#define GOLDEN_RATIO 1.6180339887
#define SQRT_5 2.236067977

JNIEXPORT jint JNICALL Java_com_example_jnivezba_FibonacciNative_get
  (JNIEnv *env, jobject obj, jint n)
{
    return (jint) floor(pow(GOLDEN_RATIO, (double) n)/SQRT_5 + 0.5);
}

