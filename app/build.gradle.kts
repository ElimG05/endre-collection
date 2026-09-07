plugins {
    kotlin("multiplatform") version "2.2.20"
}

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        jsMain.dependencies {
        }
    }
}