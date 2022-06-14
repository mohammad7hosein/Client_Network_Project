package ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import model.Client
import ui.component.CustomSnackBar
import ui.component.InputTextField

@ExperimentalUnitApi
@Composable
fun InputFormScreen(
    client: Client,
    onBackClick: () -> Unit
) {
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

    Box(modifier = Modifier.fillMaxSize()) {
        IconButton(
            modifier = Modifier.align(Alignment.TopStart).padding(36.dp),
            onClick = onBackClick
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource("angle_left.svg"),
                contentDescription = "back"
            )
        }
        Column(
            modifier = Modifier.align(Alignment.Center).padding(24.dp),
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
                        client.write("student")
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

}
