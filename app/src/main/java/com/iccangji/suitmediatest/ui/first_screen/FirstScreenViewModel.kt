package com.iccangji.suitmediatest.ui.first_screen

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel

class FirstScreenViewModel: ViewModel() {

    private val _isPalindrome = MutableStateFlow(false)
    val isPalindrome: StateFlow<Boolean> get() = _isPalindrome

    fun isPalindrome(palindrome: String) {
        _isPalindrome.value = palindrome[0] == palindrome[palindrome.length-1]
    }
}