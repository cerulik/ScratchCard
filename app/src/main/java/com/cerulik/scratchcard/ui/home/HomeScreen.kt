package com.cerulik.scratchcard.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onGoToScratchClick: () -> Unit,
    onGoToActivationClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") }
            )
        }
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .padding(scaffoldPadding)
                .fillMaxSize()
        ) {
            HomeScreenContent(onGoToScratchClick, onGoToActivationClick)
        }
    }
}

@Composable
fun HomeScreenContent(
    onGoToScratchClick: () -> Unit,
    onGoToActivationClick: () -> Unit
) {
    val homeScreenViewModel : HomeScreenViewModel = koinViewModel()
    val homeScreenState = homeScreenViewModel.homeScreenState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Card State: ${homeScreenState.javaClass.simpleName}")
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = onGoToScratchClick) {
            Text("Go to Scratch Screen")
        }
        Button(onClick = onGoToActivationClick) {
            Text("Go to Activation Screen")
        }
    }
}