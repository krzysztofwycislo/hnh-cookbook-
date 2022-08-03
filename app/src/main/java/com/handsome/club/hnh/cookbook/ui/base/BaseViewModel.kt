package com.handsome.club.hnh.cookbook.ui.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T : ScreenState>(
    initialState: T
) : ViewModel() {

    var screenState by mutableStateOf<T>(initialState)
        protected set

}
