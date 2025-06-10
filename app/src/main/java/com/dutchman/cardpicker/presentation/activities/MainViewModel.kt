package com.dutchman.cardpicker.presentation.activities

import androidx.lifecycle.ViewModel
import com.dutchman.cardpicker.R
import com.dutchman.cardpicker.data.CardDto
import com.dutchman.cardpicker.data.toCard
import com.dutchman.cardpicker.domain.Card
import com.dutchman.cardpicker.domain.Route
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState>
        get() = _uiState.asStateFlow()


    init {
        val cards = R.drawable::class.java.fields.mapNotNull { field ->
            val name = field.name
            val resId = field.getInt(null)
            CardDto(
                cardName = name.replace("_", " ").trim(),
                cardResId = resId
            )
        }.filter {
            !it.cardName.contains("launcher")
        }.map { it.toCard() }

        _uiState.update { state ->
            state.copy(
                cards = cards
            )
        }
    }

    fun navigate(route: Route, card: Card) {
        _uiState.update { state ->
            state.copy(
                route = route,
                selectedCard = card
            )
        }
    }

}