package com.dutchman.cardpicker.data

import androidx.annotation.DrawableRes
import com.dutchman.cardpicker.domain.Card

data class CardDto(
    val cardName: String,
    @DrawableRes val cardResId: Int? = null
)

fun CardDto.toCard() = Card(
    cardName = cardName,
    cardResId = cardResId ?: -1
)
