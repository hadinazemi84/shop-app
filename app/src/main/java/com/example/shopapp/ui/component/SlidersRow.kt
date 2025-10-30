package com.example.shopapp.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopapp.model.site.Slider
import com.example.shopapp.ui.DataUiState
import com.example.shopapp.vm.HomeViewModel

@Composable
fun SlidersRow(vm: HomeViewModel) {

    ShowViewByState(
        vm.sliders
    ) { sliders ->
        LazyRow(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            itemsIndexed(sliders) { index, slider ->
                AnimateSlideIn(index * 100) {
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


