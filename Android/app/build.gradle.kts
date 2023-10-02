plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.appfranchisor.app"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.appfranchisor.app"
        minSdk = 26
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }


    flavorDimensions.add("type")
    productFlavors {

        create("local") {
            buildConfigField ("String", "BASE_URL","\"${project.property("base_url")}\"")

            setDimension("type")
        }

        create("publics") {

            buildConfigField ("String", "BASE_URL","\"${project.property("base_url")}\"")

            setDimension("type")
        }
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.2")
    implementation ("com.squareup.retrofit2:converter-scalars:2.9.0")

    implementation ("de.hdodenhof:circleimageview:3.1.0")

    implementation ("com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.7")
    implementation ("androidx.fragment:fragment-ktx:1.5.3")
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation ("com.github.bumptech.glide:glide:4.15.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.0")
    implementation ("com.joooonho:selectableroundedimageview:1.0.1")
    implementation ("com.amulyakhare:com.amulyakhare.textdrawable:1.0.1")


    implementation ("androidx.activity:activity-ktx:1.2.3")
    implementation ("androidx.fragment:fragment-ktx:1.3.3")
    implementation ("com.github.dhaval2404:imagepicker:2.1")
    implementation ("com.guolindev.permissionx:permissionx:1.7.1")
    implementation ("com.github.chivorns:smartmaterialspinner:1.5.0")

    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")

    //coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //mvvm
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    //hilt
    implementation ("com.google.dagger:hilt-android:2.48")
    kapt ("com.google.dagger:hilt-android-compiler:2.48") 

}