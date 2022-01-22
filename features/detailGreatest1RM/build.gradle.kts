plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = Config.compileSdkVersion
    buildToolsVersion = Config.buildToolsVersion

    defaultConfig {
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        // Flag to enable support for the new language APIs
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {
    implementation(project(":persistence:asset"))
    implementation(project(":persistence:database-room"))
    implementation(project(":core-base:domain-data-layer"))
    implementation(project(":core-base:context"))
    implementation(project(":core-base:ui"))
    implementation(project(":core-base:navigation"))

    implementation(Dependencies.androidCoreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.coroutines)
    implementation(Dependencies.fragmentKtx)
    implementation(Dependencies.viewModelScope)
    implementation(Dependencies.materialDesign)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.koin)
    testImplementation(Dependencies.junit)

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5") //TODO Do we need this ?
}