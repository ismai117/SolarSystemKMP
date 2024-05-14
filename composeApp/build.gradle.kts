import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import com.android.build.api.dsl.ManagedVirtualDevice
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.buildConfig)
}

kotlin {

    js {
        browser()
        binaries.executable()
    }

    wasmJs {
        browser()
        binaries.executable()
    }

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    compilerOptions {
        languageVersion.set(KotlinVersion.KOTLIN_2_0)
    }

    sourceSets {

        all {
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
        }

        val desktopMain by getting

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.ktor.client.android)
            implementation(libs.koin.android)
            implementation(libs.kstore.file)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel.compose)
            implementation(libs.androidx.navigation.compose)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.bundles.ktor.common)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.napier)
            implementation(libs.bundles.coil.common)
            implementation(libs.kstore)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.client.java)
            implementation(libs.kstore.file)
            implementation(libs.harawata.appdirs)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.kstore.file)
        }
        jsMain.dependencies {
            implementation(compose.html.core)
            implementation(libs.ktor.client.js)
            implementation(libs.kstore.storage)
        }
        wasmJsMain.dependencies {
            implementation(libs.kstore.storage)
        }
    }
}

android {
    namespace = "org.ncgroup.solarsystemkmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "org.ncgroup.solarsystemkmp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.ncgroup.solarsystemkmp"
            packageVersion = "1.0.0"
        }
    }
}

val properties = Properties().apply {
    load(project.rootProject.file("local.properties").inputStream())
}

buildConfig {
    buildConfigField(
        type = "String",
        name = "apiKey",
        value = "\"${properties.getProperty("apiKey")}\""
    )
}