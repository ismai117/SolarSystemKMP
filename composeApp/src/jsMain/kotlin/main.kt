import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        CanvasBasedWindow("SolarSystemKMP") {
            App()
            if (getPlatform().isMobile){
                println("is mobile mode")
            } else {
                println("is web mode")
            }
        }
    }
}
