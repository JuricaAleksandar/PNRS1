#include "statistics.h"

#include <jni.h>

JNIEXPORT jfloat JNICALL Java_ra47_12014_pnrs1_rtrk_taskmanager_CalculateStatistics_calculate
  (JNIEnv *env, jobject obj, jfloat done, jfloat total)
{
	return (done/total)*100;
}

