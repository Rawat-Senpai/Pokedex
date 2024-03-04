plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.pokedex"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.pokedex"
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

    buildFeatures{
        viewBinding = true
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")




    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.8.0")
    // view model scope
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    // hilt dependency
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")


    val activity_version = "1.8.2"

    // Java language implementation
    implementation("androidx.activity:activity:$activity_version")
    // Kotlin
    implementation("androidx.activity:activity-ktx:$activity_version")


    // sdp
//    implementation 'com.intuit.ssp:ssp-android:1.1.0'
//    implementation 'com.intuit.sdp:sdp-android:1.1.0'
    implementation("com.intuit.ssp:ssp-android:1.1.0")
    implementation("com.intuit.sdp:sdp-android:1.1.0")


    // for permissions
    implementation ("com.guolindev.permissionx:permissionx:1.6.1")


    // ok http
    implementation("com.squareup.okhttp3:okhttp:4.12.0")


    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    //implementation ("com.github.ybq:Android-SpinKit:1.4.0")


    // GLide dependency
    implementation ("com.github.bumptech.glide:glide:4.16.0")


    // transfromation layout
    implementation("com.github.skydoves:transformationlayout:1.1.3")
    implementation ("com.github.skydoves:bundler:1.0.4")
    implementation ("com.github.skydoves:progressview:1.1.3")
    implementation ("com.github.skydoves:androidribbon:1.0.4")

    implementation("androidx.palette:palette:1.0.0")



}


kapt {
    correctErrorTypes = true
}