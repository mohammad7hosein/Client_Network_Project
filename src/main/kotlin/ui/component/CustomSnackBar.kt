package ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp


@Composable
fun CustomSnackBar(
    text: MutableState<String>,
    visible: MutableState<Boolean>,
    color: MutableState<Color>
) {
    Box(
        modifier = Modifier.width(500.dp),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(visible.value) {
            Snackbar(
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = text.value,
                            style = TextStyle(
                                fontFamily = FontFamily(
                                    Font(resource = "vazir.ttf", weight = FontWeight.Bold)
                                ),
                                textAlign = TextAlign.Right,
                                textDirection = TextDirection.Rtl,
                            ),
                        )
                    }
                },
                modifier = Modifier.padding(16.dp),
                backgroundColor = color.value,
                contentColor = Color.White
            )
        }
    }
}