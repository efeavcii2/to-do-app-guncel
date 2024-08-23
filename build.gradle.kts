// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}
buildscript{
    dependencies{
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.45")

    }
}