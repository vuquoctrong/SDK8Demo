# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontwarn com.sun.jna.**
-keep class com.sun.jna.**{*;}


#xlog Obfuscation of mars
-keep class com.tencent.mars.** {
 public protected private *;
}
# Gson Obfuscation
-keepattributes Annotation
-keep class sun.misc.Unsafe { *; }
-keep class com.idea.fifaalarmclock.entity.*
-keep class com.google.gson.stream.* { *; }


-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

-keep class okio.** { *; }
-dontwarn okio.**

-keep class com.viettel.vht.core.utils.**{*;}
-dontwarn com.vht.sdkcore.core.utils.**

-keep class com.viettel.vht.sdk.model.** {*;}
-dontwarn com.viettel.vht.sdk.model.**

-keep class com.viettel.vht.sdk.ui.jftechaddcamera.model.** {*;}
-dontwarn com.viettel.vht.sdk.ui.jftechaddcamera.model.**

-keep class com.viettel.vht.sdk.funtionsdk.** {*;}
-dontwarn com.viettel.vht.sdk.funtionsdk.**



-dontwarn com.sun.jna.**
-keep class com.sun.jna.**{*;}



-keep class javax.inject.** { *; }
-keep class dagger.** { *; }
-keep interface dagger.** { *; }
-keep class dagger.hilt.android.internal.generated.root.** { *; }
-keep class dagger.hilt.android.internal.generated.components.** { *; }
-keep class dagger.hilt.android.internal.generated.** { *; }
-keep class dagger.hilt.android.** { *; }
-keep class javax.inject.** { *; }
-keep @dagger.hilt.GeneratesRootInput class * { *; }


-keepclassmembers class * {
    @javax.inject.Inject <fields>;
    @javax.inject.Inject <init>(...);
}

-keep public class * extends androidx.fragment.app.Fragment
-keep public class * extends android.app.Fragment
-keep public class * extends androidx.appcompat.app.AppCompatActivity
-keep public class com.lib.** {*;}
-keep public class com.basic.** {*;}
-keep public class com.manager.**{public<methods>;public<fields>;}
-keep public  class com.video.opengl.GLSurfaceView20 {*;}
-keep public class com.xm.ui.**{public<methods>;protected<methods>;public<fields>;protected<fields>;}
-keep public class com.utils.**{public<methods>;}
-keep public class com.xm.activity.base.XMBasePresenter{public protected *;}
-keep public class com.xm.activity.base.XMBaseActivity{public<methods>;protected <fields>;}
-keep public class com.xm.activity.base.XMBaseFragment{public<methods>;protected <fields>;}
-keep public class com.xm.kotlin.**{public<methods>;protected<methods>;public<fields>;protected<fields>;}
-keep public class com.xm.ui.**{public<methods>;}
-keep public class com.xm.linke.**{public<methods>;}
-keep public class com.**$*{*;}

-keepattributes Signature
-dontwarn com.google.gson.**
-keep class com.google.gson.**{*;}

#eventbus
-keep class org.greenrobot.eventbus.**{*;}
-keepclassmembers class **{
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode {*;}

#zxing.jar
-dontwarn com.google.zxing.**
-keep class com.google.zxing.**{*;}
