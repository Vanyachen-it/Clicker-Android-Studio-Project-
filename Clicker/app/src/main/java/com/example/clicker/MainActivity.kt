package com.example.clicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainNavigation()
            }
        }
    }
}

enum class Screen {
    MAIN, GAME, ACHIEVEMENTS
}
@Composable
fun MainNavigation() {
    var clicks by remember { mutableStateOf(0)}
    var balance by remember { mutableStateOf(0)}
    var currentScreen by remember { mutableStateOf(Screen.MAIN)}

    when (currentScreen) {
    Screen.MAIN -> MainScreen(
        onNavigateToGame = {currentScreen = Screen.GAME},
        onNavigateToAchievements = { currentScreen = Screen.ACHIEVEMENTS }
    )
    Screen.GAME -> GameScreen(
        clicks = clicks,
        balance = balance,
        onClick = {
            clicks++
            balance += 1
        },
        onBack = {currentScreen = Screen.MAIN}
    )
        Screen.ACHIEVEMENTS -> AchievementsScreen (
            clicks = clicks,
            onBack = {currentScreen = Screen.MAIN}
        )

    }
}

@Composable
fun MainCreen(onNavigateToGame: () -> Unit, onNavigateToAchievements: () -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(
            text = "Cool Clicker",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 48.dp)
        )
        Button(
            onClick = onNavigateToGame,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "In Game", fontSize = 18.sp)
        }
        Button (
            onClick = onNavigateToAchievements,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
        ) {
            Text(text = "Achievements", fontSize = 18.sp)
        }
    }
}

@Composable
fun GameScreen(clicks: Int, balance: Int, onClick: () -> Unit, onBack: () -> Unit) {
    Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        TextButton(onClick = onBack) {
         Text(text = "Back", fontSize = 16.sp)
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

            }
        }
    }
}
