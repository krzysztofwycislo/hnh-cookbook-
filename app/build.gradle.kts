plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)

    id("kotlin-kapt")
    alias(libs.plugins.hilt)
    alias(libs.plugins.realm)

    alias(libs.plugins.kotlin.compose.compiler)
}

android {

    namespace = "com.handsome.club.hnh.cookbook"

    compileSdk = 34

    defaultConfig {
        applicationId = "com.handsome.club.hnh.cookbook"

        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {

        debug {
            isMinifyEnabled = false
        }

        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"

        freeCompilerArgs += listOf(
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            "-opt-in=androidx.compose.foundation.layout.ExperimentalLayoutApi",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.ktx)

    // UI
    implementation(platform(libs.ui.compose.bom))
    implementation(libs.ui.compose)
    implementation(libs.ui.compose.runtime)
    implementation(libs.ui.compose.activity)
    implementation(libs.ui.compose.tooling.preview)
    implementation(libs.ui.compose.material3)
    debugImplementation(libs.ui.compose.tooling)

    implementation(libs.ui.coil.compose)

    // Navigation
    implementation(libs.ui.compose.navigation)

    // Persistence
    implementation(libs.persistence.realm)

    // Network
    implementation(libs.network.retrofit)
    implementation(libs.network.retrofit.moshi)
    implementation(libs.network.okhttp3.logging)
    implementation(libs.network.moshi.kotlin)
    implementation(libs.network.moshi)
    ksp(libs.network.moshi.codegen)


    // DI
    implementation(libs.di.hilt)
    kapt(libs.di.hilt.compiler)
    implementation(libs.di.hilt.compose.navigation)

    // Utils
    implementation(libs.util.timber)

    // Test
    testImplementation(libs.test.junit)
    testImplementation(libs.test.coroutines)

    debugImplementation(libs.test.compose.mainfest)
}