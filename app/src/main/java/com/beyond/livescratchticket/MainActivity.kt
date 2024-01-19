package com.beyond.livescratchticket

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.beyond.livescratchticket.ui.addDetail
import com.beyond.livescratchticket.ui.addHome
import com.beyond.livescratchticket.ui.theme.LiveScratchTicketTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiveScratchTicketTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable(
                            "home"
                        ) {
                            addHome(navController, arrayListOf("洗碗", "拖地"))
                        }
                        composable(
                            route = "detail/{index}/{item}",
                            arguments = listOf(
                                navArgument("index") { type = NavType.IntType },
                                navArgument("item") { type = NavType.StringType })
                        ) {
                            val index = it.arguments?.getInt("index")
                            val arguments = it.arguments?.getString("item")
                            addDetail(index ?: 0, arguments ?: "")
                        }
                    }
                }
            }
        }
    }
}