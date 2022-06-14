package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import ui.component.MyButton

@ExperimentalUnitApi
@Composable
fun MenuScreen(
    addStudent: () -> Unit,
    average: () -> Unit,
    sortAverage: () -> Unit,
    maxAverage: () -> Unit,
    minAverage: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(Modifier.height(50.dp))
        Text(
            text = "منو",
            style = TextStyle(
                fontSize = TextUnit(32f, TextUnitType.Sp),
                fontFamily = FontFamily(
                    Font(resource = "vazir.ttf", weight = FontWeight.Bold)
                ),
                textAlign = TextAlign.Right,
                textDirection = TextDirection.Rtl,
            ),
        )
        Spacer(Modifier.height(30.dp))
        MyButton(
            text = "اضافه کردن دانشجو",
            onClick = addStudent
        )
        MyButton(
            text = "معدل دانشجویان",
            onClick = average
        )
        MyButton(
            text = "معدل مرتب شده دانشجویان",
            onClick = sortAverage
        )
        MyButton(
            text = "بیشترین معدل",
            onClick = maxAverage
        )
        MyButton(
            text = "کمترین معدل",
            onClick = minAverage
        )
    }

}