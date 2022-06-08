import androidx.compose.animation.AnimatedVisibility
import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalUnitApi
fun main() = application {

    val mainClient = Client("localhost", 9999, 0)
    val clientNumber = mainClient.reader.nextLine().toInt()
    for (i in 1..clientNumber) {
        val client = Client("localhost", 9999, i)
        Window(
            state = WindowState(size = WindowSize(850.dp, 850.dp)),
//            icon = rememberVectorPainter(Icons.Default.ThumbUp),
            onCloseRequest = ::exitApplication, title = "Client $i"
        ) {
            Main(client)
        }
    }

}

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

@ExperimentalUnitApi
@Composable
fun MenuScreen(
    addStudent: () -> Unit,
    average: () -> Unit,
    sortAverage: () -> Unit,
    maxAverage: () -> Unit,
    minAverage: () -> Unit
) {
    DesktopMaterialTheme {
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
                text = "معدل دانشجوها",
                onClick = average
            )
            MyButton(
                text = "معدل مرتب شده دانشجوها",
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
}

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

@ExperimentalUnitApi
@Composable
fun InputFormScreen(client: Client) {
    var firstName = remember { mutableStateOf("") }
    var lastName = remember { mutableStateOf("") }
    var nationalCode = remember { mutableStateOf("") }
    var identityNumber = remember { mutableStateOf("") }
    var courseOne = remember { mutableStateOf("") }
    var courseTwo = remember { mutableStateOf("") }
    var courseThree = remember { mutableStateOf("") }
    var courseFour = remember { mutableStateOf("") }
    var courseFive = remember { mutableStateOf("") }

    val textFieldList = listOf(
        firstName,
        lastName,
        nationalCode,
        identityNumber,
        courseOne,
        courseTwo,
        courseThree,
        courseFour,
        courseFive
    )

    val scope = rememberCoroutineScope()
    var visible = remember { mutableStateOf(false) }
    var snackBarMessage = remember { mutableStateOf("") }
    var snackBarColor = remember { mutableStateOf(Color(0xffff0000)) }

    var checkInput = true

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
                )
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

        Button(
            shape = RoundedCornerShape(15.dp),
            onClick = {
                checkInput = true
                for (textField in textFieldList) {
                    if (textField.value == "") {
                        snackBarMessage.value = "همه موارد باید تکمیل شود!"
                        snackBarColor.value = Color(0xffff0000)
                        scope.launch {
                            visible.value = true
                            delay(2000)
                            visible.value = false
                        }
                        checkInput = false
                    }
                }
                if (checkInput) {
                    client.write(client.id.toString())
                    for (textField in textFieldList) {
                        client.write(textField.value)
                        textField.value = ""
                    }
                    snackBarMessage.value = "دانشجو با موفقیت اضافه شد"
                    snackBarColor.value = Color(0xff00aa00)
                    scope.launch {
                        visible.value = true
                        delay(2000)
                        visible.value = false
                    }
                }
            }) {
            Text(
                text = "تایید",
                style = TextStyle(
                    fontFamily = FontFamily(
                        Font(resource = "vazir.ttf", weight = FontWeight.Bold)
                    )
                )
            )
        }
    }
    CustomSnackBar(snackBarMessage, visible, snackBarColor)

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

@Composable
fun ShowResultScreen() {

}

@ExperimentalUnitApi
@Composable
fun Main(client: Client) {
    var screenState by remember { mutableStateOf<Screen>(Screen.MenuScreen) }

    when (screenState) {
        is Screen.MenuScreen -> MenuScreen(
            addStudent = { screenState = Screen.InputFormScreen },
            average = { screenState = Screen.ShowResultScreen },
            sortAverage = { screenState = Screen.ShowResultScreen },
            minAverage = { screenState = Screen.ShowResultScreen },
            maxAverage = { screenState = Screen.ShowResultScreen },
        )
        is Screen.InputFormScreen -> InputFormScreen(client)
    }

}



