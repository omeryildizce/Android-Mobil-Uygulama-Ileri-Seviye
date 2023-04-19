package com.omeryildizce.flow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omeryildizce.flow.ui.theme.FlowTheme
import kotlinx.coroutines.flow.StateFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowTheme {
                val viewModel: MyViewModel by viewModels()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //FirstScreen(viewModel)
                    SecondScreen(viewModel)
                }
            }
        }
    }

}

@Composable
fun SecondScreen(viewModel: MyViewModel) {
    val liveDataValue = viewModel.liveData.observeAsState()
    val stateFlowValue = viewModel.stateFlow.collectAsState()
    val sharedFlowValue = viewModel.sharedFlow.collectAsState(initial = " ")


        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = liveDataValue.value ?: "")
            Button(onClick = { viewModel.changeLiveDataValue() }) {
                Text(text = "Live Data Button")
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = stateFlowValue.value ?: "")
            Button(onClick = { viewModel.changeStateFlowValue() }) {
                Text(text = "State Flow Button")
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Text(text = sharedFlowValue.value ?: "")
            Button(onClick = { viewModel.changeSharedFlow() }) {
                Text(text = "Shared Flow Button")
            }


        }

}

@Composable
fun FirstScreen(viewModel: MyViewModel) {
    val counter = viewModel.coutDownTimerFlow.collectAsState(initial = 10)
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = counter.value.toString(),
            fontSize = 50.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )

    }

}