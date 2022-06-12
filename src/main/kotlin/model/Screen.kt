package model

sealed class Screen {
    object MenuScreen : Screen()
    object InputFormScreen : Screen()
    object ShowResultScreen : Screen()
}
