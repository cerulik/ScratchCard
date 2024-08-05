package com.cerulik.scratchcard.ui.activation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cerulik.scratchcard.domain.model.ScratchCardState
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivationScreen(
    onBackClick: () -> Unit,
) {
    val activationScreenViewModel : ActivationScreenViewModel = koinViewModel()
    val activationScreenState = activationScreenViewModel.viewState.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Activate Card") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier.padding(scaffoldPadding).padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (activationScreenState is ActivationScreenState.Loaded) {
                if (activationScreenState.scratchCardState is ScratchCardState.Scratched) {
                    if (activationScreenState.scratchCardState.isActivating) {
                        CircularProgressIndicator()
                    } else {
                        Button(onClick = activationScreenViewModel::activateCard) {
                            Text("Activate Card")
                        }
                        if(activationScreenState.errorMessage != null) {
                            Text("${activationScreenState.errorMessage}")
                        }
                    }
                } else if (activationScreenState.scratchCardState is ScratchCardState.Activated) {
                    Text("Scratch card is activated with result ${activationScreenState.scratchCardState.code}")
                } else if (activationScreenState.scratchCardState is ScratchCardState.Unscratched) {
                    Text("Scratch card is not scratched")
                }
            }
        }
    }
}