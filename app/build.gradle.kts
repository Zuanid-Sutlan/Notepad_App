//import org.jetbrains.kotlin.gradle.internal.kapt.incremental.UnknownSnapshot.classpath

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    // for room database
//    kotlin("jvm") version "1.9.21" apply false
//    kotlin("jvm")

//    id("com.google.devtools.ksp")

    id("com.google.devtools.ksp") version "1.9.21-1.0.16"
//    id ("com.google.devtools.ksp" version "1.6.21-1.0.5")



//    kotlin("kapt") version "1.9.22"


}

android {


    namespace = "com.example.notepadapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.notepadapp"
        minSdk = 26
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

//    kapt {
//        useBuildCache = false
//    }
//    kapt {
//        showProcessorStats = true
//    }

    // room database content
//    repositories {
//        mavenCentral()
//    }


}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.room:room-ktx:2.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // room database dependencies

//    val roomVersion = "androidx.room:room-testing:2.6.1.0"
//
//    implementation("androidx.room:room-runtime:$roomVersion")
//    annotationProcessor("androidx.room:room-compiler:$roomVersion")

    // To use Kotlin annotation processing tool (kapt)
//    kapt("androidx.room:room-compiler:$roomVersion")
//    kapt("groupId:artifactId:version")


    // To use Kotlin Symbol Processing (KSP)
//    ksp("androidx.room:room-compiler:$room_version")
//    classpath(kotlin("gradle-plugin", version = "1.9.21"))
//    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.21-1.0.15")


    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    // To use Kotlin Symbol Processing (KSP)

        ksp("androidx.room:room-compiler:$room_version")
//    ksp ("com.github.bumptech.glide:compiler:4.13.2")


    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    // optional - RxJava2 support for Room
    implementation("androidx.room:room-rxjava2:$room_version")

    // optional - RxJava3 support for Room
    implementation("androidx.room:room-rxjava3:$room_version")

    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation("androidx.room:room-guava:$room_version")

    // optional - Test helpers
    testImplementation("androidx.room:room-testing:$room_version")

    // optional - Paging 3 Integration
    implementation("androidx.room:room-paging:$room_version")


        // view model mvvm architecture
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")


}

