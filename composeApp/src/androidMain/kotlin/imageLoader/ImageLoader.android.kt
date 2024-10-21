package imageLoader

import applicationContext
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.intercept.painterMemoryCacheConfig
import com.seiko.imageloader.option.androidContext
import okio.Path.Companion.toOkioPath

actual fun generateImageLoader(): ImageLoader {
    return ImageLoader {
        options {
            androidContext(applicationContext)
        }
        components {
            setupDefaultComponents()
        }
        interceptor {
            painterMemoryCacheConfig {
                maxSize(50)
            }
            diskCacheConfig {
                directory(applicationContext.cacheDir.resolve("image_cache").toOkioPath())
                maxSizeBytes(512L * 1024 * 1024) // 512MB
            }
        }
    }
}