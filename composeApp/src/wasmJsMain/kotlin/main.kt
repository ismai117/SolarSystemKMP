import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.ErrorEvent
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.PromiseRejectionEvent
import org.w3c.dom.events.Event

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow("SolarSystem") {
        App()
    }
}

