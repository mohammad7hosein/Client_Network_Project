package ui.component

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection

@Composable
fun InputTextField(
    text: MutableState<String>,
    label: String,
    limit: Int,
    rtl: Boolean
) {
    OutlinedTextField(
        value = text.value,
        onValueChange = {
            if (it.length <= limit)
                text.value = it
        },
        label = {
            Text(
                text = label,
                style = TextStyle(
                    textAlign = TextAlign.Right,
                    textDirection = TextDirection.Rtl,
                    fontFamily = FontFamily(
                        Font(resource = "vazir.ttf")
                    )
                ),
            )
        },
        singleLine = true,
        textStyle = TextStyle(
            textAlign = if (rtl) TextAlign.Right else TextAlign.Left,
            textDirection = if (rtl) TextDirection.Rtl else TextDirection.Ltr,
            fontFamily = FontFamily(
                Font(resource = "vazir.ttf")
            )
        )
    )

}