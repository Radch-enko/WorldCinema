package stanislav.radchenko.worldcinema.ui.common.imagerequests

import android.content.Context
import coil.request.ImageRequest
import stanislav.radchenko.worldcinema.BuildConfig
import stanislav.radchenko.worldcinema.R

object ProfileImageLoader {

    fun load(url: String, context: Context): ImageRequest {
        return ImageRequest.Builder(context)
            .data("${BuildConfig.BASE_URL}/up/images/$url")
            .crossfade(true)
            .placeholder(R.drawable.no_image_profile_placeholder)
            .error(R.drawable.no_image_profile_placeholder)
            .build()
    }
}