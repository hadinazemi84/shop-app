package com.example.shopapp.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun AnimateSlideIn(delay: Int, content: @Composable () -> Unit) {
    val isVisible =
        remember { MutableTransitionState(false).apply { targetState = true } }
    AnimatedVisibility(
        visibleState = isVisible, enter = slideInVertically(
            tween(1000, delay),
            initialOffsetY = { -700 }
        )
    ) {
        content()
    }
}

