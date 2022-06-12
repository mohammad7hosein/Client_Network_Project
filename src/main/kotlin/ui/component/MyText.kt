package ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType

@ExperimentalUnitApi
@Composable
fun MyText(
    text: String,
    textSize: Float
) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = TextUnit(textSize, TextUnitType.Sp),
            fontFamily = FontFamily(
                Font(resource = "vazir.ttf")
            ),
            textAlign = TextAlign.Right,
            textDirection = TextDirection.Rtl,
        ),
    )
}
