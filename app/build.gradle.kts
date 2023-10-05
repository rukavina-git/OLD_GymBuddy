plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.compose")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.rukavina.gymbuddy"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.rukavina.gymbuddy"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "0.0.1-SNAPSHOT"
        compileSdkPreview = "UpsideDownCakePrivacySandbox"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":progress"))
    implementation(project(":workout"))
    implementation(project(":exercise"))
    implementation(project(":auth"))
    implementation(project(":common"))

    implementation (libs.navigation.compose)
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
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    testImplementation(libs.junit)
    androidTestImplementation(libs.testExtJunit)
    androidTestImplementation(libs.espressoCore)
}

kapt {
    correctErrorTypes = true
}