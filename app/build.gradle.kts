plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)

    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.oytra"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.oytra"
        minSdk = 24
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
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Coil
    implementation ("io.coil-kt:coil-compose:2.7.0")

    implementation("androidx.navigation:navigation-compose:2.7.7")

    //Retrofit2
    implementation ("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation ("com.squareup.retrofit2:retrofit:3.0.0")


    implementation("com.google.dagger:hilt-android:2.59.2")
    ksp("com.google.dagger:hilt-android-compiler:2.59.2")

    implementation("androidx.hilt:hilt-navigation-compose:1.3.0")

    val cameraxVersion = "1.4.0" // Check for latest
    implementation("androidx.camera:camera-camera2:$cameraxVersion")
    implementation("androidx.camera:camera-lifecycle:$cameraxVersion")
    implementation("androidx.camera:camera-view:$cameraxVersion")

    // 2. Google ML Kit Barcode Scanning
    implementation("com.google.mlkit:barcode-scanning:17.3.0")

    // 3. Guava (Required for CameraX ListenableFuture)
    implementation("com.google.guava:guava:33.0.0-android")

    implementation("com.google.accompanist:accompanist-permissions:0.34.0")


}