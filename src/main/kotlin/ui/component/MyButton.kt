package ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp

@Composable
fun MyButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.size(250.dp, 50.dp),
        shape = RoundedCornerShape(15.dp),
        onClick = onClick
    ) {
        Text(
            text = text,
            fontFamily = FontFamily(
                Font(resource = "vazir.ttf")
            )
        )
    }
    Spacer(Modifier.height(20.dp))
}