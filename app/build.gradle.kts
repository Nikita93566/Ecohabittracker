plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.ecohabittracker"

    // ✅ FIX: upgrade SDK
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.ecohabittracker"
        minSdk = 24

        // ✅ keep same as compileSdk (safe)
        targetSdk = 36

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // ROOM DATABASE
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")

    // ✅ FIX: use compatible core version
    implementation("androidx.core:core:1.13.1")
}