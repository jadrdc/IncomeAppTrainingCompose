package com.koombea.presenter.ui.settings.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.koombea.data.character.datasource.entity.Currency
import com.koombea.data.character.datasource.entity.Security
import com.koombea.presenter.R
import com.koombea.presenter.ui.settings.ProfileViewModel
import com.koombea.presenter.ui.theme.BLACK_SELECTOR
import com.koombea.presenter.ui.theme.PURPLE

@Composable
fun SettingsPickerScreen(
    title: String, navController: NavHostController, viewModel: ProfileViewModel
) {
    Column(
        Modifier.fillMaxWidth()
    ) {
        Header(title, navController)
        Selector(title = title, viewModel = viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(title: String, navController: NavHostController) {
    CenterAlignedTopAppBar(title = {
        Text(
            text = title,
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
fun Selector(title: String, viewModel: ProfileViewModel) {
    val selectedCurrency by viewModel.state.collectAsStateWithLifecycle()

    if (title == "Currency") {
        Currency.values().forEach {
            val mutableIsSelected by remember { mutableStateOf(selectedCurrency.user?.currency?.name == it.name) }

            ItemSelector(name = it.currency, mutableIsSelected) {
                viewModel.setCurrency(it)
            }
        }
    } else {
        Security.values().forEach {
            val mutableIsSelected by remember { mutableStateOf(selectedCurrency.user?.security?.name == it.name) }
            ItemSelector(name = it.internalName, mutableIsSelected) {
                viewModel.setSecurity(it)
            }
        }
    }
}

@Composable
fun ItemSelector(name: String, isSelected: Boolean, onSelectedItem: () -> Unit) {
    Row(
        Modifier
            .padding(start = 24.dp, end = 16.dp, top = 8.dp)
            .clickable { onSelectedItem() }) {
        Text(
            text = name,
            fontSize = 14.sp,
            lineHeight = 18.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W500,
            color = BLACK_SELECTOR
        )
        Spacer(modifier = Modifier.weight(1f))
        if (isSelected) {
            Icon(
                painter = painterResource(R.drawable.selecteditem),
                contentDescription = "selected ",
                modifier = Modifier.size(32.dp),
                tint = PURPLE
            )
        }
    }
}