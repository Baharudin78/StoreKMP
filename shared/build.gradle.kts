plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    iosArm64()
    iosX64()
    iosSimulatorArm64()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }
    val myAttribute = Attribute.of("myOwnAttribute", String::class.java)

    configurations.all {
        if (name == "podDebugFrameworkIosFat") {
            attributes {
                attribute(myAttribute, "pod-debug")
            }
        }
        if (name == "podReleaseFrameworkIosFat") {
            attributes {
                attribute(myAttribute, "pod-release")
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies{
                api(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.json)
                implementation(libs.ktor.client.negotiation)
                implementation(libs.ktor.client.serialization)
                api(libs.moko.mvvm.core)
                api(libs.moko.mvvm.compose)
                implementation(libs.kamel.image)
            }
        }
//        commonMain.dependencies {
//
//        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        val androidMain by getting {
            dependencies {
                api(libs.androidx.activity.compose)
                api(libs.androidx.appcompat)
                api(libs.androidx.core.ktx)
                implementation(libs.ktor.client.android)
                implementation(libs.koin.core)
                implementation(libs.koin.android)
                implementation(libs.compose.ui.tooling.preview)
                implementation(libs.datastore.core)
            }

        }
//        androidMain.dependencies {
//
//        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies{
                implementation(libs.ktor.client.darwing)
            }
        }
    }
}

android {
    namespace = "com.baharudin.learnkmp"
    compileSdk = 34
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin{
        jvmToolchain(17)
    }
}
