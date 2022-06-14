import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowSize
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import model.Client
import ui.ScreenHandler

@ExperimentalUnitApi
fun main() = application {

    val mainClient = Client("localhost", 9999, 0)
    val clientNumber = mainClient.reader.nextLine().toInt()
    for (i in 1..clientNumber) {
        val client = Client("localhost", 9999, i)
        Window(
            state = WindowState(size = WindowSize(800.dp, 800.dp)),
            icon = painterResource("img.png"),
            onCloseRequest = ::exitApplication, title = "model.Client $i"
        ) {
            ScreenHandler(client)
        }
    }

}






