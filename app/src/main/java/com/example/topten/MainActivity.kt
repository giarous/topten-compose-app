package com.example.topten

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.topten.navigation.AppNavHost
import com.example.topten.ui.theme.TopTenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {

    val navController = rememberNavController()

    TopTenTheme {
        AppNavHost(navController = navController)
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun PreviewHomeScreen() {
    MyApp()
}


