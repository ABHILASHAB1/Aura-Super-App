import os
import zipfile

# Define the project structure
project_name = "AuraSuperApp"
base_dir = f"/mnt/data/{project_name}"

# Create directories
dirs = [
    f"{base_dir}/app/src/main/java/com/aura/app",
    f"{base_dir}/app/src/main/res/values",
    f"{base_dir}/.github/workflows",
    f"{base_dir}/gradle/wrapper"
]

for d in dirs:
    os.makedirs(d, exist_ok=True)

# 1. settings.gradle
settings_gradle = """
rootProject.name = "AuraSuperApp"
include ':app'
"""

# 2. build.gradle (Project level)
project_build_gradle = """
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:8.2.2'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22"
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}
"""

# 3. app/build.gradle
app_build_gradle = """
plugins {
    id 'com.android.application'
    id 'kotlin-android'
}

android {
    namespace 'com.aura.app'
    compileSdk 34

    defaultConfig {
        applicationId "com.aura.app"
        minSdk 26
        targetSdk 34
        versionCode 1
        versionName "1.0"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_17
        targetCompatibility JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = '17'
    }

    buildFeatures {
        compose true
    }

    composeOptions {
        kotlinCompilerExtensionVersion '1.5.8'
    }
}

dependencies {
    implementation 'androidx.core:core-ktx:1.12.0'
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.7.0'
    implementation 'androidx.activity:activity-compose:1.8.2'
    implementation platform('androidx.compose:compose-bom:2024.02.00')
    implementation 'androidx.compose.ui:ui'
    implementation 'androidx.compose.ui:ui-graphics'
    implementation 'androidx.compose.ui:ui-tooling-preview'
    implementation 'androidx.compose.material3:material3'
    implementation 'androidx.compose.material:material-icons-extended'
}
"""

# 4. AndroidManifest.xml
manifest_xml = """
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <application
        android:allowBackup="true"
        android:icon="@android:mipmap/sym_def_app_icon"
        android:label="Aura"
        android:roundIcon="@android:mipmap/sym_def_app_icon"
        android:supportsRtl="true"
        android:theme="@android:style/Theme.Material.NoActionBar">
        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:theme="@android:style/Theme.Material.NoActionBar">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
"""

# 5. MainActivity.kt (The UI Code)
main_activity_kt = """
package com.aura.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val SkyHighAccent = Color(0xFF00F5FF)
val DeepBlack = Color(0xFF050505)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuraApp()
        }
    }
}

@Composable
fun AuraApp() {
    var currentScreen by remember { mutableStateOf("home") }

    Box(modifier = Modifier.fillMaxSize().background(DeepBlack)) {
        AnimatedContent(
            targetState = currentScreen,
            transitionSpec = { fadeIn(tween(500)) togetherWith fadeOut(tween(500)) }
        ) { screen ->
            when (screen) {
                "home" -> NexusHomeScreen(onNavigate = { currentScreen = "skyhigh" })
                "skyhigh" -> SkyHighResultsScreen(onBack = { currentScreen = "home" })
            }
        }
    }
}

@Composable
fun NexusHomeScreen(onNavigate: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
        Row(Modifier.fillMaxWidth().padding(24.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text("Welcome back, Anitha", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("Vault Secured • 100%", color = SkyHighAccent, fontSize = 12.sp)
            }
            Icon(Icons.Rounded.Notifications, contentDescription = null, tint = Color.White)
        }

        Surface(
            modifier = Modifier.fillMaxWidth().padding(24.dp).height(160.dp),
            color = Color.White.copy(alpha = 0.05f),
            shape = RoundedCornerShape(32.dp),
            border = BorderStroke(1.dp, Color.White.copy(alpha = 0.1f))
        ) {
            Column(Modifier.padding(24.dp)) {
                Text("VAULT BALANCE", color = Color.Gray, fontSize = 12.sp)
                Text("$42,500.00", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Black)
            }
        }

        Text("SKYHIGH SERVICES", color = Color.Gray, modifier = Modifier.padding(start = 24.dp), fontSize = 12.sp, letterSpacing = 2.sp)
        
        Surface(
            modifier = Modifier.fillMaxWidth().padding(24.dp).clickable { onNavigate() },
            color = Color.White.copy(alpha = 0.05f),
            shape = RoundedCornerShape(24.dp),
            border = BorderStroke(1.dp, Color.White.copy(alpha = 0.1f))
        ) {
            Row(Modifier.padding(24.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Rounded.FlightTakeoff, contentDescription = null, tint = SkyHighAccent)
                Spacer(Modifier.width(16.dp))
                Column {
                    Text("Search Flights", color = Color.White, fontWeight = FontWeight.Bold)
                    Text("AI Sniper Active", color = Color.Gray, fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun SkyHighResultsScreen(onBack: () -> Unit) {
    val scrollState = rememberLazyListState()
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(state = scrollState) {
            item { 
                Box(Modifier.fillMaxWidth().height(300.dp).background(Brush.verticalGradient(listOf(Color.DarkGray, DeepBlack)))) {
                    Text("TOKYO", modifier = Modifier.align(Alignment.Center), fontSize = 64.sp, color = Color.White, fontWeight = FontWeight.Black)
                }
            }
            items(10) { index ->
                FlightCard(index)
            }
        }
        
        IconButton(
            onClick = onBack, 
            modifier = Modifier.statusBarsPadding().padding(16.dp).background(Color.Black.copy(0.5f), CircleShape)
        ) {
            Icon(Icons.Rounded.ArrowBack, contentDescription = null, tint = Color.White)
        }
    }
}

@Composable
fun FlightCard(index: Int) {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        color = Color.White.copy(alpha = 0.05f),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.1f))
    ) {
        Row(Modifier.padding(24.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text("SKYHIGH OPTIMAL", color = SkyHighAccent, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                Text("$${842 + (index * 15)}", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Black)
            }
            Icon(Icons.Rounded.Bolt, contentDescription = null, tint = SkyHighAccent)
        }
    }
}
"""

# 6. build.yml (GitHub Actions)
build_yml = """
name: Build Aura APK
on: [push]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      
      - name: set up JDK 17
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'

      - name: Build with Gradle
        run: |
          gradle wrapper
          chmod +x gradlew
          ./gradlew assembleDebug

      - name: Upload APK
        uses: actions/upload-artifact@v4
        with:
          name: Aura-Super-App-Demo
          path: app/build/outputs/apk/debug/app-debug.apk
"""

# Write all files
files_to_write = {
    f"{base_dir}/settings.gradle": settings_gradle,
    f"{base_dir}/build.gradle": project_build_gradle,
    f"{base_dir}/app/build.gradle": app_build_gradle,
    f"{base_dir}/app/src/main/AndroidManifest.xml": manifest_xml,
    f"{base_dir}/app/src/main/java/com/aura/app/MainActivity.kt": main_activity_kt,
    f"{base_dir}/.github/workflows/build.yml": build_yml,
}

for path, content in files_to_write.items():
    with open(path, "w") as f:
        f.write(content.strip())

# Zip the project
zip_path = "/mnt/data/AuraSuperApp_Project.zip"
with zipfile.ZipFile(zip_path, 'w', zipfile.ZIP_DEFLATED) as zipf:
    for root, dirs, files in os.walk(base_dir):
        for file in files:
            file_path = os.path.join(root, file)
            zipf.write(file_path, os.path.relpath(file_path, base_dir))

zip_path