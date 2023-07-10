package com.koombea.presenter.ui.settings.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.koombea.presenter.ui.settings.ProfileViewModel
import com.koombea.presenter.ui.theme.LIGHT_GRAY
import com.koombea.presenter.ui.theme.PURPLE

@Composable
fun EditProfileScreen(navController: NavHostController, viewModel: ProfileViewModel) {
    Column(Modifier.fillMaxWidth()) {
        EditProfileHeader(navController = navController)
        EditProfileBody(viewModel) {
            viewModel.setUpdated()
            navController.popBackStack()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileHeader(navController: NavHostController) {
    CenterAlignedTopAppBar(title = {
        Text(
            text = "Edit Profile",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W600,
        )
    }, navigationIcon = {
        Icon(imageVector = Icons.Filled.ArrowBack,
            contentDescription = "back",
            modifier = Modifier
                .padding(start = 16.dp)
                .clickable { navController.popBackStack() })
    })
    Divider(
        Modifier
            .background(Color(0XFFF9F9F9))
            .height(1.dp)
            .padding(50.dp), thickness = 0.dp
    )
}

@Composable
fun EditProfileBody(
    viewModel: ProfileViewModel, onUpdatedUser: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        state.user?.let {
            OutlinedTextField(
                value = it.name,
                onValueChange = { value ->
                    viewModel.updateName(value)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                label = { Text(text = "Name", color = LIGHT_GRAY) },
            )
            OutlinedTextField(
                value = it.email,
                onValueChange = { viewModel.updateEmail(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                label = { Text(text = "Email", color = LIGHT_GRAY) },
                shape = RoundedCornerShape(16.dp)
            )
            OutlinedTextField(
                value = it.birthday,
                onValueChange = { viewModel.updateBirthday(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                label = { Text(text = "Birthday", color = LIGHT_GRAY) },
                shape = RoundedCornerShape(16.dp)
            )
        }
        Button(
            shape = RoundedCornerShape(16.dp),
            onClick = { viewModel.updateUser() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PURPLE)
        ) {
            Text(text = "Edit", fontSize = 18.sp)
        }
    }
    if (state.wasUpdated == true) {
        AlertDialog(title = { Text(text = "User updated") }, onDismissRequest = {
            onUpdatedUser()
        }, confirmButton = {
            TextButton(onClick = { onUpdatedUser() }) {
                Text(text = "Ok")
            }
        })
    }
}