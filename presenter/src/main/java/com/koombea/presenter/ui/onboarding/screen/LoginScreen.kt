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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.koombea.domain.model.UserModel
import com.koombea.presenter.ui.onboarding.OnboardingViewModel
import com.koombea.presenter.ui.onboarding.navigation.OnboardingNavigation
import com.koombea.presenter.ui.theme.LIGHT_GRAY
import com.koombea.presenter.ui.theme.PURPLE

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: OnboardingViewModel,
    onLogin: (UserModel) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val user = state.user
    Column(
        Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Login",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 32.dp, bottom = 64.dp),
            fontWeight = FontWeight.W600
        )
        user?.let {
            OutlinedTextField(
                value = user.email,
                onValueChange = { value ->
                    viewModel.updateEmail(value)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                label = { Text(text = "Email", color = LIGHT_GRAY) },
            )
            OutlinedTextField(
                value = user.password,
                onValueChange = { value ->
                    viewModel.updatePassword(value)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 36.dp),
                label = { Text(text = "Password", color = LIGHT_GRAY) },
                shape = RoundedCornerShape(16.dp),
                visualTransformation = PasswordVisualTransformation()
            )
        }
        Button(
            shape = RoundedCornerShape(16.dp),
            onClick = {
                viewModel.login()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PURPLE)
        ) {
            Text(
                text = "Sign in",
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center
            )
        }

        TextButton(
            onClick = { navController.navigate(OnboardingNavigation.SignUp.route) },
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 36.dp)
        ) {
            Text(
                color = Color.Black,
                text = "Create Account",
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center
            )
        }
        if (state.wasSignUp == false) {
            AlertDialog(
                title = { Text(text = "User was not logon") },
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
            state.user?.let { onLogin(it) }
        }
    }
}