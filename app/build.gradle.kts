import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("jacoco")
    id("org.jlleitschuh.gradle.ktlint")
    id("io.gitlab.arturbosch.detekt") version "1.23.8"
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "uz.example.cicd"
    compileSdk = 36

    defaultConfig {
        applicationId = "uz.example.cicd"
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
                "proguard-rules.pro",
            )
        }
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    buildFeatures {
        compose = true
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
    ktlint {
        android.set(true)
        outputToConsole.set(true)
        verbose.set(true)
    }
    detekt {
        config.setFrom(files("$rootDir/.detekt.yml"))
        buildUponDefaultConfig = true
        allRules = false
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Room
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)

    // ViewModel & Lifecycle
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.compose)

    // Hilt Navigation Compose
    implementation(libs.hilt.navigation.compose)
}

jacoco {
    toolVersion = "0.8.11"
}

// Jacoco report task
tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn("testDebugUnitTest")

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    classDirectories.setFrom(
        fileTree("build/intermediates/javac/debug") {
            exclude("**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*")
        },
        fileTree("build/tmp/kotlin-classes/debug") {
            exclude("**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*")
        },
    )

    sourceDirectories.setFrom(
        files("src/main/java", "src/main/kotlin"),
    )

    executionData.setFrom(
        fileTree(layout.buildDirectory) {
            include("jacoco/testDebugUnitTest.exec")
        },
    )
}
tasks.register("ciFull") {
    dependsOn(
        "ktlintCheck",
        "detekt",
        "lintDebug",
        "testDebugUnitTest",
        "jacocoTestReport",
        "assembleDebug",
    )
}

tasks.register("verifyCodeQuality") {
    dependsOn("ktlintCheck", "detekt", "lintDebug")
}
