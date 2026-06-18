package com.aura.app
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF050505)) {
                Column(Modifier.padding(24.dp)) {
                    Text("Aura Super App", color = Color.White, style = MaterialTheme.typography.headlineLarge)
                    Spacer(Modifier.height(20.dp))
                    Text("Vault Balance: $42,500", color = Color(0xFF00F5FF))
                }
            }
        }
    }
}
