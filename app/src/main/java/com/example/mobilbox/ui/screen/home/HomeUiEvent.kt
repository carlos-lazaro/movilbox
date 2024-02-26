package com.example.mobilbox.ui.screen.home

sealed class HomeUiEvent {
    data object ResetScroll : HomeUiEvent()
}
