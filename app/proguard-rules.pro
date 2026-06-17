# Keep overlay service
-keep class com.deskpet.overlay.** { *; }

# Keep Compose
-dontwarn androidx.compose.**
-keep class androidx.compose.** { *; }

# Keep Lottie
-dontwarn com.airbnb.lottie.**
-keep class com.airbnb.lottie.** { *; }

# Keep Coil
-dontwarn coil.**
-keep class coil.** { *; }
