@file:OptIn(ExperimentalMaterial3Api::class)

package com.omeryildizce.cryptocrazy.view

import Crypto
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.omeryildizce.cryptocrazy.util.Resource
import com.omeryildizce.cryptocrazy.viewmodel.CryptoDetailViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun CryptoDetailsScreen(
    id: String,
    name: String,
    navController: NavController,
    viewModel: CryptoDetailViewModel = hiltViewModel()
) {

    val cryptoItem = produceState<Resource<Crypto>>(initialValue = Resource.Loading()) {
        value = viewModel.getCrypto(id)
    }.value

    Scaffold(
        topBar = {
            TopAppBar(

                title = {
                    Card(modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()  ,elevation = CardDefaults.cardElevation(8.dp), colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary)) {

                        Text(

                            modifier = Modifier.padding(8.dp),
                            text = name,
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.secondary)

                )
        },
        content = {
            Column(

                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(it)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())

            ) {
                when (cryptoItem) {
                    is Resource.Success -> {
                        val selectedCrypto = cryptoItem.data!!

                        MyText(text = selectedCrypto.name)

                        Image(
                            painter = rememberAsyncImagePainter(model = selectedCrypto.image.large),
                            contentDescription = selectedCrypto.name,
                            modifier = Modifier
                                .padding(16.dp)
                                .size(200.dp, 200.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Gray, CircleShape)
                        )
                        MyText(text = selectedCrypto.description.en)
                        MyText(text = selectedCrypto.links.homepage.get(0))
                        MyText(text = selectedCrypto.countryOrigin)

                    }

                    is Resource.Error -> {
                        Text(cryptoItem.message!!)
                    }

                    is Resource.Loading -> {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }

                }
            }

        }
    )
}

@Composable
fun MyText(text: String?) {
    if (!text.isNullOrEmpty()){
        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(2.dp),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center

        )
    }
}


