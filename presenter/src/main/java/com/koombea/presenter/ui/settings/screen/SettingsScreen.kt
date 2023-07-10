package com.koombea.presenter.ui.settings.screen

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.koombea.presenter.R
import com.koombea.presenter.ui.settings.ProfileViewModel
import com.koombea.presenter.ui.settings.navigation.ProfileNavigation
import com.koombea.presenter.ui.theme.BLACK_PROFILE
import com.koombea.presenter.ui.theme.DEFAULT_BLACK
import com.koombea.presenter.ui.theme.LIGHT_BLACK
import com.koombea.presenter.ui.theme.LIGHT_GRAY
import com.koombea.presenter.ui.theme.PURPLE
import com.koombea.presenter.ui.theme.PURPLE_BUTTON
import com.koombea.presenter.ui.theme.PURPLE_PROFILE

@Composable
fun ProfileScreen(navController: NavHostController, viewModel: ProfileViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val activity = LocalContext.current as Activity
    var showShowModal by remember { mutableStateOf(false) }
    Column(Modifier.fillMaxSize()) {
        Text(
            text = "Settings",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W600,
            fontSize = 18.sp,
            color = DEFAULT_BLACK,
            modifier = Modifier
                .padding(start = 16.dp, top = 32.dp, bottom = 16.dp)
                .fillMaxWidth()
        )
        Divider(
            Modifier
                .background(Color(0XFFF9F9F9))
                .height(1.dp)
                .padding(50.dp), thickness = 0.dp
        )
        state.user?.let {
            ProfileInfo(
                R.drawable.foto2x2, it.email, it.name
            ) { navController.navigate(ProfileNavigation.EditProfile.route) }
            Spacer(modifier = Modifier.height(50.dp))
            PickerSettings(
                "Currency", it.currency.name
            ) { navController.navigate(ProfileNavigation.ChangeSettings.route + "/Currency") }
            PickerSettings(
                "Security", it.security.name
            ) { navController.navigate(ProfileNavigation.ChangeSettings.route + "/Security") }
        }
        TextButton(
            onClick = {
                showShowModal = true
            }, modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 16.dp)
        ) {
            Text(
                text = "Log out",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = DEFAULT_BLACK,
                fontWeight = FontWeight.W500
            )
        }
        if (showShowModal) {
            LogoutBottomModal({ showShowModal = false }, {
                showShowModal = false
                activity.finish()
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogoutBottomModal(noAction: () -> Unit, yesAction: () -> Unit) {
    ModalBottomSheet(onDismissRequest = {}) {
        BottomSheetContent(
            "Log out",
            "Are you sure you want to log out?",
            Modifier,
            noAction,
            yesAction
        )
    }
}

@Composable
fun BottomSheetContent(
    title: String,
    message: String,
    modifier: Modifier,
    noAction: () -> Unit,
    yesAction: () -> Unit
) {
    Column(
        modifier
            .height(188.dp)
            .fillMaxWidth()
    ) {
        Text(
            modifier = modifier.align(CenterHorizontally),
            maxLines = 1,
            textAlign = TextAlign.Center,
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.W600
        )
        Text(
            color = LIGHT_GRAY,
            modifier = modifier.align(CenterHorizontally),
            maxLines = 1,
            textAlign = TextAlign.Center,
            text = message,
            fontSize = 16.sp,
            fontWeight = FontWeight.W500
        )
        Row(
            modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                shape = RoundedCornerShape(16.dp),
                onClick = { noAction() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = PURPLE_BUTTON)
            ) {
                Text(text = "No", fontSize = 18.sp, color = PURPLE)
            }
            Button(
                shape = RoundedCornerShape(16.dp),
                onClick = { yesAction() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .weight(1f)
                    .padding(),
                colors = ButtonDefaults.buttonColors(containerColor = PURPLE)
            ) {
                Text(text = "Yes", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun ProfileInfo(@DrawableRes pict: Int, email: String, name: String, onClickEditIcon: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, top = 16.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = pict),
            contentDescription = "profile",
            modifier = Modifier
                .clip(CircleShape)
                .size(80.dp)
                .border(BorderStroke(2.dp, PURPLE_PROFILE), CircleShape)
        )
        Column(Modifier.padding(top = 8.dp, start = 16.dp)) {
            Text(
                text = email, fontSize = 14.sp, fontWeight = FontWeight.W500, color = LIGHT_GRAY
            )
            Text(
                text = name, fontSize = 24.sp, fontWeight = FontWeight.W600, color = BLACK_PROFILE
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(painterResource(id = R.drawable.img),
            contentDescription = "edit profile",
            modifier = Modifier
                .padding(top = 12.dp)
                .size(32.dp)
                .clickable { onClickEditIcon() })
    }
}

@Composable
fun PickerSettings(config: String, value: String, onclick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onclick() }) {
        Text(
            text = config, fontSize = 16.sp, fontWeight = FontWeight.W500, color = LIGHT_BLACK
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            color = LIGHT_GRAY,
        )
        Icon(
            painter = painterResource(id = R.drawable.pickarrow),
            contentDescription = "pick",
            modifier = Modifier.size(24.dp),
            tint = PURPLE
        )
    }
}
