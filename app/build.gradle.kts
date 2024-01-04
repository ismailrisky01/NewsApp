plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.newsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.newsapp"
        minSdk = 24
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Network
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.7.2")

    // Glide for image loading
    implementation ("com.github.bumptech.glide:glide:4.16.0")

// EasyPrefs for easy SharedPreferences management
    implementation ("com.pixplicity.easyprefs:EasyPrefs:1.10.0")

// AndroidX Startup for app startup tasks
    implementation ("androidx.startup:startup-runtime:1.1.1")
    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0")
    annotationProcessor ("android.arch.persistence.room:compiler:1.1.1")

// Koin for dependency injection
    implementation ("io.insert-koin:koin-core:3.4.3")
    implementation ("io.insert-koin:koin-android:3.4.3")
    testImplementation ("io.insert-koin:koin-test:3.4.3")
    testImplementation ("io.insert-koin:koin-test-junit4:3.4.3")

// OkHttp for HTTP requests
    implementation ("com.squareup.okhttp3:okhttp:4.10.0")

// Gson for JSON parsing
    implementation ("com.google.code.gson:gson:2.9.0")


// Facebook Shimmer for shimmering effect
    implementation ("com.facebook.shimmer:shimmer:0.5.0")

// EasyPermissions for handling Android runtime permissions
    implementation ("pub.devrel:easypermissions:3.0.0")

// Kotlinx Serialization for JSON serialization/deserialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")

}