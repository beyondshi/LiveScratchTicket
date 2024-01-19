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
import kotlinx.serialization.json.Json

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
                            addHome(navController, taskList)
                        }
                        composable(
                            route = "detail/{index}/{data}",
                            arguments = listOf(
                                navArgument("index") { type = NavType.IntType },
                                navArgument("data") { type = NavType.StringType })
                        ) {
                            val index = it.arguments?.getInt("index")
                            val data = it.arguments?.getString("data") ?: ""
                            val task: FamilyTask = Json.decodeFromString(data)
                            Log.i(TAG, "index = $index arguments = $task")
                            addDetail(index ?: 0, task)
                        }
                    }
                }
            }
        }
    }
}