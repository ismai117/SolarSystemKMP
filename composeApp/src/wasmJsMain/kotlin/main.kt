import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.ErrorEvent
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.PromiseRejectionEvent
import org.w3c.dom.events.Event

val WasmCompileError: JsAny = js("WebAssembly.CompileError")

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow(canvasElementId = "ComposeTarget") { App() }
    window.addEventListener("error") {
        it as ErrorEvent
        unhandledError(it, it.error!!)
    }
    window.addEventListener("unhandledrejection") {
        it as PromiseRejectionEvent
        unhandledError(it, it.reason!!)
    }
}


fun unhandledError(event: Event, error: JsAny) {
    if (error::class == WasmCompileError) {
        val warn = document.getElementById("warning") as HTMLDivElement
        warn.style.display = "initial"
        // Hide a Scary Webpack Overlay which is less informative in this case.
        val webPack = document.getElementById("webpack-dev-server-client-overlay") as? HTMLDivElement
        webPack?.style?.display = "none"
    }
}