import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.Dimension

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "SolarSystemKMP",
    ) {
        window.minimumSize = Dimension(900, 700)
        App()
    }
}