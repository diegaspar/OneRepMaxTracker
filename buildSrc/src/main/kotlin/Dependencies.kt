object BuildPlugins {
    val androidBuildGradle by lazy { "com.android.tools.build:gradle:${Versions.androidGradle}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
}

object Dependencies {
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val androidCoreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    val coroutines by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}" }
    val fragmentKtx by lazy { "androidx.fragment:fragment-ktx:${Versions.fragment}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}" }
    val koin by lazy { "io.insert-koin:koin-android:${Versions.koin}" }
    val room by lazy { "androidx.room:room-runtime:${Versions.room}" }
    val roomCoroutines by lazy { "androidx.room:room-ktx:${Versions.room}" }
    val roomKapt by lazy { "androidx.room:room-compiler:${Versions.room}" }
    val viewModelScope by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelScope}" }

    val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
    val mpaAndroidChart by lazy { "com.github.PhilJay:MPAndroidChart:${Versions.mpaAndroidChart}" }
    val desugar by lazy { "com.android.tools:desugar_jdk_libs:${Versions.desugar}" }

    val junit by lazy { "junit:junit:${Versions.jUnit}" }
}