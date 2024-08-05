package com.cerulik.scratchcard.scratch

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
import com.cerulik.scratchcard.ui.scratch.ScratchScreenState
import com.cerulik.scratchcard.ui.scratch.ScratchScreenViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScratchScreen(
    onBackClick: () -> Unit,
) {
    val scratchScreenViewModel : ScratchScreenViewModel = koinViewModel()
    val scratchScreenState = scratchScreenViewModel.viewState.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Scratch Card") },
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
            if (scratchScreenState is ScratchScreenState.Loaded) {
                if (scratchScreenState.scratchCardState is ScratchCardState.Unscratched) {
                    Button(onClick = scratchScreenViewModel::scratchCard) {
                        Text("Scratch Card")
                    }
                } else if (scratchScreenState.scratchCardState is ScratchCardState.Scratched) {
                    Text("Scratched code is ${scratchScreenState.scratchCardState.code}")
                }
            } else {
                CircularProgressIndicator()
            }
        }
    }
}