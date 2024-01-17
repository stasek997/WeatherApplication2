plugins {
    id("com.android.application")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}



android {
    namespace = "com.example.weatherapplication"
    compileSdk = 34



    defaultConfig {


        applicationId = "com.example.weatherapplication"
        minSdk = 26
        targetSdk = 31
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

}



dependencies {


// For Kotlin use core-ktx

    implementation ("com.github.bumptech.glide:glide:4.16.0")

    implementation ("androidx.activity:activity-ktx:1.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.google.dagger:hilt-android:2.49")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    implementation ("androidx.navigation:navigation-dynamic-features-fragment:2.7.6")
    implementation ("com.google.android.gms:play-services-location:21.0.1")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // REST API calling library
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // JSON parsing library
    implementation("androidx.core:core-splashscreen:1.0.1")

    ksp("com.google.dagger:hilt-android-compiler:2.49")
    ksp("com.github.bumptech.glide:compiler:4.16.0")


    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("com.google.android.material:material:1.12.0-alpha02")


    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    testImplementation ("org.mockito:mockito-core:5.5.0")
    testImplementation ("org.mockito.kotlin:mockito-kotlin:5.1.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("org.robolectric:robolectric:4.11.1")
    testImplementation("com.google.truth:truth:1.1.3")

}

secrets {
    // Optionally specify a different file name containing your secrets.
    // The plugin defaults to "local.properties"
    propertiesFileName = "local.properties"

    // A properties file containing default secret values. This file can be
    // checked in version control.
    defaultPropertiesFileName = "local.properties"

    // Configure which keys should be ignored by the plugin by providing regular expressions.
    // "sdk.dir" is ignored by default.
    ignoreList.add("keyToIgnore") // Ignore the key "keyToIgnore"
    ignoreList.add("sdk.*")       // Ignore all keys matching the regexp "sdk.*"
}
