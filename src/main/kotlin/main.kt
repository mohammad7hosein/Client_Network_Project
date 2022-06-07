import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

fun main() = application {

    val client = Client("localhost", 9999)
    val clientNumber = client.reader.nextLine().toInt()
    println(clientNumber)
    for (i in 1..clientNumber) {
        Window(onCloseRequest = ::exitApplication, title = "Client $i") {
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
                Text("اضافه کردن دانشجو")
            }
        }
    }
}

@Composable
fun InputForm() {
    DesktopMaterialTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Hello sdlkkjw kldsvlksj lknsvlsn  lknlkvnsl  lknlksl")
        }
    }

}

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



