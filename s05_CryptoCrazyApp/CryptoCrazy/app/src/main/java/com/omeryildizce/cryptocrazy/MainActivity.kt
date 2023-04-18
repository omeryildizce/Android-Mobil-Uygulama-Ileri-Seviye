package com.omeryildizce.cryptocrazy


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.omeryildizce.cryptocrazy.ui.theme.CryptoCrazyTheme
import com.omeryildizce.cryptocrazy.view.CryptoDetailsScreen
import com.omeryildizce.cryptocrazy.view.CryptoListScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoCrazyTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "crypto_list_screen") {
                    composable("crypto_list_screen") {
                        // CryptoListScreen
                        CryptoListScreen(navController)
                    }

                    composable("crypto_detail_screen/{cryptoId}/{cryptoName}", arguments = listOf(
                        navArgument("cryptoId") {
                            type = NavType.StringType
                        },
                        navArgument("cryptoName") {
                            type = NavType.StringType
                        }
                    )) {
                        // CryptoDetailsScreen
                        val cryptoId = remember {
                            it.arguments?.getString("cryptoId")
                        }
                        val cryptoPrice = remember {
                            it.arguments?.getString("cryptoName")
                        }
                        CryptoDetailsScreen(cryptoId ?: "", cryptoPrice ?: "", navController)
                    }
                }
            }
        }
    }
}