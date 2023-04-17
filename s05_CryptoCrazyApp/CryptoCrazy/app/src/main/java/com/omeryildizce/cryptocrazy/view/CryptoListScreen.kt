package com.omeryildizce.cryptocrazy.view


import android.content.res.Resources.Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.omeryildizce.cryptocrazy.viewmodel.CryptoListViewModel


@Composable
fun CryptoListScreen(
    navController: NavController ,
    viewModel: CryptoListViewModel = hiltViewModel()
) {
    Surface(color = MaterialTheme.colorScheme.secondary , modifier = Modifier.fillMaxSize()) {
        
    }
}