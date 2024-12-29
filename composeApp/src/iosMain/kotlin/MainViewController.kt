import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.io.files.Path
import planets.di.appStorage
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

private val fileManager: NSFileManager = NSFileManager.defaultManager
@OptIn(ExperimentalForeignApi::class)
private val documentsUrl: NSURL? = fileManager.URLForDirectory(
    directory = NSDocumentDirectory,
    appropriateForURL = null,
    create = false,
    inDomain = NSUserDomainMask,
    error = null
)

@OptIn(ExperimentalForeignApi::class)
fun MainViewController() = ComposeUIViewController {
    val path: String = requireNotNull(documentsUrl?.path) { "Documents directory not found" }
    appStorage = Path(path)
    App()
}