package com.koombea.presenter.ui.onboarding.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.koombea.domain.model.UserModel
import com.koombea.presenter.ui.onboarding.OnboardingViewModel
import com.koombea.presenter.ui.theme.LIGHT_GRAY
import com.koombea.presenter.ui.theme.PURPLE

@Composable
fun CreateAccountScreen(viewModel: OnboardingViewModel, onSignUp: (UserModel) -> Unit) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val user = state.user
    Column(
        Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Create Account",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp, bottom = 32.dp),
            fontWeight = FontWeight.W600
        )
        user?.let {
            OutlinedTextField(
                value = user.name,
                onValueChange = {
                    viewModel.updateName(it)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                label = { Text(text = "Name", color = LIGHT_GRAY) },
            )
            OutlinedTextField(
                value = user.email,
                onValueChange = {
                    viewModel.updateEmail(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                label = { Text(text = "Email", color = LIGHT_GRAY) },
                shape = RoundedCornerShape(16.dp)
            )
            OutlinedTextField(
                value = user.birthday,
                onValueChange = {
                    viewModel.updateBirthday(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(16.dp),
                label = { Text(text = "Birthday", color = LIGHT_GRAY) },
            )
            OutlinedTextField(
                visualTransformation = PasswordVisualTransformation(),
                value = user.password,
                onValueChange = {
                    viewModel.updatePassword(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 36.dp),
                label = { Text(text = "Password", color = LIGHT_GRAY) },
                shape = RoundedCornerShape(16.dp),
            )
        }
        Button(
            shape = RoundedCornerShape(16.dp),
            onClick = { viewModel.signUpUser() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PURPLE)
        ) {
            Text(
                text = "Sign up",
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center,
            )
        }
        if (state.wasSignUp == false) {
            AlertDialog(
                title = { Text(text = "User was not created due to missing information.") },
                onDismissRequest = {
                    viewModel.hideValidationDialog()
                },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.hideValidationDialog()
                    }) {
                        Text(text = "Ok")
                    }
                })
        } else if (state.wasSignUp == true) {
            state.user?.let { onSignUp(it) }
        }
    }

}