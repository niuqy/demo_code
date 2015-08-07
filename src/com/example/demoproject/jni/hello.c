#include <string.h>
#include <jni.h>

/*
 *#include <com_example_demoproject_jnidemo_HelloJNIActivity.h>
 * Class:     drm_hellojni_HelloJNIActivity
 * Method:    helloJni
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_demoproject_jnidemo_HelloJNIActivity_hiJNI
  (JNIEnv *env, jobject obj)
{
    
    return (*env)->NewStringUTF(env, "Hi jni !!!");
}
