plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.dersolog"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.dersolog"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // Core Android libraries for app compatibility and UI components
    implementation(libs.appcompat) // Provides backward compatibility for newer Android features
    implementation(libs.material) // Material Design components for building modern UIs
    implementation(libs.activity) // Provides components to manage app activities
    implementation(libs.constraintlayout) // Layout manager for flexible and efficient UI design

    // Unit testing libraries
    testImplementation(libs.junit) // JUnit for running unit tests

    // Android testing libraries
    androidTestImplementation(libs.ext.junit) // JUnit for Android-specific testing
    androidTestImplementation(libs.espresso.core) // Espresso for UI testing on Android

    // Retrofit for handling network requests
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
}
