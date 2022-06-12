package ui

import androidx.compose.runtime.*
import androidx.compose.ui.unit.ExperimentalUnitApi
import model.Client
import model.Screen

@ExperimentalUnitApi
@Composable
fun ScreenHandler(client: Client) {
    var screenState by remember { mutableStateOf<Screen>(Screen.MenuScreen) }
    when (screenState) {
        is Screen.MenuScreen -> MenuScreen(
            addStudent = { screenState = Screen.InputFormScreen },
            average = {
                screenState = Screen.ShowResultScreen
                client.write(client.id.toString())
                client.write("avg")
            },
            sortAverage = {
                screenState = Screen.ShowResultScreen
                client.write(client.id.toString())
                client.write("sort")
            },
            minAverage = {
                screenState = Screen.ShowResultScreen
                client.write(client.id.toString())
                client.write("min")
            },
            maxAverage = {
                screenState = Screen.ShowResultScreen
                client.write(client.id.toString())
                client.write("max")
            },
        )
        is Screen.InputFormScreen -> InputFormScreen(
            client = client,
            onBackClick = { Screen.MenuScreen })
        is Screen.ShowResultScreen -> ShowResultScreen(
            reader = client.reader,
            onBackClick = { Screen.MenuScreen })
    }
}