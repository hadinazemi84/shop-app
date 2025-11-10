import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shopapp.ui.component.BasketItemsRow
import com.example.shopapp.ui.component.PriceText
import com.example.shopapp.vm.BasketViewmodel

@Composable
fun BasketScreen(navController: NavHostController, vm: BasketViewmodel) {
    val basketItems by vm.basketItems.observeAsState(emptyList())
    val totalPrice = basketItems.sumOf { (it.price ?: 0L) * it.quantity }


    if (basketItems.isNotEmpty()) {
        Box(
            Modifier.fillMaxSize()
        ) {
            BasketItemsRow(basketItems, vm)
            PaymentBottomBar(totalPrice)

        }
    } else {
        Box(Modifier.fillMaxSize(), Alignment.Center) { Text("Basket is empty") }
    }
}

@Composable
fun BoxScope.PaymentBottomBar(totalPrice: Long) {
    Column(
        Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .background(Color.White, RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .padding(vertical = 18.dp, horizontal = 12.dp)
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Total price")
            PriceText(totalPrice, color = Color.Black, fontSize = 18.sp, isColorDark = true)
        }
        Spacer(Modifier.height(12.dp))
        Row(Modifier.fillMaxWidth()) {
            Button(
                onClick = {},
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(Color.LightGray, Color.Black)
            ) {
                Text("Continue shopping")
            }
            Spacer(Modifier.width(12.dp))
            Button(
                onClick = {},
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(Color.Green, Color.White)
            ) {
                Text("Processed to payment")
            }

        }
    }
}


