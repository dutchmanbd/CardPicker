package com.dutchman.cardpicker.presentation.activities

import com.dutchman.cardpicker.domain.Card
import com.dutchman.cardpicker.domain.Route

data class MainUiState(
    val selectedCard: Card = Card.empty,
    val route: Route = Route.Home,
    val cards: List<Card> = emptyList()
)
