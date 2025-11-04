package com.example.shopapp.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shopapp.model.site.Slider
import com.example.shopapp.vm.HomeViewModel

@Composable
fun SlidersRow(vm: HomeViewModel) {

    ShowViewByState(
        vm.sliders
    ) { sliders ->
        LazyRow(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            itemsIndexed(sliders) { index, slider ->
                AnimatedSlideIn(index * 100) {
                    ItemSlider(slider)
                }
            }
        }
    }


}


@Composable
fun ItemSlider(slider: Slider) {
    CostumeCard(modifier = Modifier, slider.image, slider.title,slider.subTitle)
}


