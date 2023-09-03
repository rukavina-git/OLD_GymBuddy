plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.compose")
}

android {
    namespace = "com.rukavina.gymbuddy"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rukavina.gymbuddy"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        compileSdkPreview = "UpsideDownCakePrivacySandbox"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
    implementation(project(":progress"))
    implementation(project(":workout"))
    implementation(project(":exercise"))
    implementation(project(":common"))

    implementation(libs.androidxCoreKtx)
    implementation(libs.androidxAppCompat)
    implementation(libs.androidxMaterial)
    implementation(libs.constraintLayout)
    implementation(libs.lifecycleKtx)
    implementation(libs.navigationKtx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.daggerHilt)
    kapt(libs.daggerHiltCompiler)
    implementation(libs.composeUi)
    implementation(libs.composeMaterial)
    implementation(libs.composeLiveData)
    testImplementation(libs.junit)
    androidTestImplementation(libs.testExtJunit)
    androidTestImplementation(libs.espressoCore)
}

kapt {
    correctErrorTypes = true
}