package com.dutchman.cardpicker.domain

data class Card(
    val cardName: String,
    val cardResId: Int
){
    companion object {
        val empty = Card(
            cardName = "",
            cardResId = -1
        )
    }
}
