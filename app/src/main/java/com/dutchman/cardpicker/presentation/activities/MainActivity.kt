package com.dutchman.cardpicker.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dutchman.cardpicker.R
import com.dutchman.cardpicker.domain.Card
import com.dutchman.cardpicker.domain.Route
import com.dutchman.cardpicker.presentation.theme.CardPickerTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardPickerTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                BackHandler {
                    if (uiState.route == Route.Home) {
                        finish()
                    } else {
                        viewModel.navigate(Route.Home, Card.empty)
                    }
                }
                Scaffold {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {

                        when (uiState.route) {
                            Route.Home -> {
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(4),
                                    verticalArrangement = Arrangement.spacedBy(
                                        12.dp
                                    ),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp)
                                ) {
                                    items(
                                        uiState.cards.size
                                    ) { index ->
                                        val card = uiState.cards[index]
                                        Image(
                                            painter = painterResource(card.cardResId),
                                            contentDescription = card.cardName,
                                            modifier = Modifier.clickable {
                                                viewModel.navigate(
                                                    Route.SelectedCard,
                                                    card
                                                )
                                            }
                                        )
                                    }
                                }
                            }

                            Route.SelectedCard -> {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(
                                        space = 8.dp,
                                        alignment = Alignment.CenterVertically
                                    ),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(
                                        painter = painterResource(uiState.selectedCard.cardResId),
                                        contentDescription = uiState.selectedCard.cardName,
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(360.dp)
                                    )

                                    Text(
                                        text = uiState.selectedCard.cardName,
                                        style = MaterialTheme.typography.titleMedium
                                    )

                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
