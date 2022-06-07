import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
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
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowSize
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

@ExperimentalUnitApi
fun main() = application {

//    val client = Client("localhost", 9999)
//    val clientNumber = client.reader.nextLine().toInt()
    for (i in 1..1) {
        Window(
            state = WindowState(size = WindowSize(850.dp, 850.dp)),
//            icon = rememberVectorPainter(Icons.Default.ThumbUp),
            onCloseRequest = ::exitApplication, title = "Client $i"
        ) {
            Main()
        }
    }

}

@Composable
fun ClientUi(
    onClick: () -> Unit
) {
    DesktopMaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onClick
            ) {
                Text(
                    "اضافه کردن دانشجو",
                    fontFamily = FontFamily(
                        Font(resource = "vazir.ttf")
                    )
                )
            }
        }
    }
}

@ExperimentalUnitApi
@Composable
fun InputForm() {
    var firstName = remember { mutableStateOf("") }
    var lastName = remember { mutableStateOf("") }
    var nationalCode = remember { mutableStateOf("") }
    var identityNumber = remember { mutableStateOf("") }
    var courseOne = remember { mutableStateOf("") }
    var courseTwo = remember { mutableStateOf("") }
    var courseThree = remember { mutableStateOf("") }
    var courseFour = remember { mutableStateOf("") }
    var courseFive = remember { mutableStateOf("") }

    DesktopMaterialTheme {

        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp).verticalScroll(
                rememberScrollState()
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "اطلاعات دانشجو",
                style = TextStyle(
                    fontSize = TextUnit(32f, TextUnitType.Sp),
                    fontFamily = FontFamily(
                        Font(resource = "vazir.ttf", weight = FontWeight.Bold)
                    ),
                    textAlign = TextAlign.Right,
                    textDirection = TextDirection.Rtl,
                ),
            )
            Spacer(Modifier.height(16.dp))
            InputTextField(
                text = firstName,
                label = "نام",
                limit = 50,
                rtl = true
            )
            InputTextField(
                text = lastName,
                label = "نام خانوادگی",
                limit = 50,
                rtl = true
            )
            InputTextField(
                text = nationalCode,
                label = "کد ملی",
                limit = 10,
                rtl = false
            )
            InputTextField(
                text = identityNumber,
                label = "شماره شناسنامه",
                limit = 10,
                rtl = false
            )
            InputTextField(
                text = courseOne,
                label = "درس یک",
                limit = 5,
                rtl = false
            )
            InputTextField(
                text = courseTwo,
                label = "درس دو",
                limit = 5,
                rtl = false
            )
            InputTextField(
                text = courseThree,
                label = "درس سه",
                limit = 5,
                rtl = false
            )
            InputTextField(
                text = courseFour,
                label = "درس چهار",
                limit = 5,
                rtl = false
            )
            InputTextField(
                text = courseFive,
                label = "درس پنج",
                limit = 5,
                rtl = false
            )
            Spacer(Modifier.height(16.dp))
            Button(onClick = {}) {
                Text("تایید")
            }

        }
    }

}

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

@ExperimentalUnitApi
@Composable
fun Main() {
    var screenState by remember { mutableStateOf<Screen>(Screen.ClientUi) }

    when (val state = screenState) {
        is Screen.ClientUi -> ClientUi(
            onClick = { screenState = Screen.InputForm }
        )
        is Screen.InputForm -> InputForm()
    }

}



