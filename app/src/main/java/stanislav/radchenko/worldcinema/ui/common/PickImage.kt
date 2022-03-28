package stanislav.radchenko.worldcinema.ui.common

import android.Manifest
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import stanislav.radchenko.worldcinema.R
import stanislav.radchenko.worldcinema.network.getRealPathFromURI_API19
import stanislav.radchenko.worldcinema.screens.profile.UserUI
import stanislav.radchenko.worldcinema.ui.common.imagerequests.ProfileImageLoader

@Composable
fun UploadAvatarSection(context: Context, userUI: UserUI, onLoadAvatar: (String) -> Unit) {
    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            val path = getRealPathFromURI_API19(context, uri)
            Log.e("UploadAvatarSection", path)
            onLoadAvatar(path)
        } else {
            Log.e("UploadAvatarSection", "path is null")
        }
    }

    val requestPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { success ->
            if (success) {
                launcher.launch("image/*")
            } else {
                Log.e("requestPermission", "failure")
            }
        })

    Column() {
        AsyncImage(
            model = ProfileImageLoader.load(userUI.avatar, LocalContext.current),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .border(1.dp, Color.Gray, CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = {
            requestPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }) {
            Text(stringResource(id = R.string.change_profile_data))
        }
    }
}