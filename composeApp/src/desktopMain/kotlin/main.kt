

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import net.harawata.appdirs.AppDirsFactory
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import planets.di.appStorage
import java.awt.Dimension

private const val PACKAGE_NAME = "org.ncgroup.solarsystemkmp"
private const val VERSION = "1.0.0"
private const val AUTHOR = "ismailmohamed"

fun main() = application {
    val userDataDir: String = AppDirsFactory.getInstance()
        .getUserDataDir(PACKAGE_NAME, VERSION, AUTHOR)
    val path = Path(userDataDir)
    with(SystemFileSystem) { if(!exists(path)) createDirectories(path) }
    appStorage = path

    Window(
        onCloseRequest = ::exitApplication,
        title = "SolarSystemKMP",
    ) {
        window.minimumSize = Dimension(700, 800)
        window.rootPane.putClientProperty("apple.awt.fullWindowContent", true)
        window.rootPane.putClientProperty("apple.awt.transparentTitleBar", true)
        window.rootPane.putClientProperty("apple.awt.windowTitleVisible", false)
        App()
    }
}