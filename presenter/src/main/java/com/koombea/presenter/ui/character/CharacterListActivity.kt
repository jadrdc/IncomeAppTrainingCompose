package com.koombea.presenter.ui.character

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import com.koombea.androidtemplate.ui.theme.AndroidtemplateTheme
import com.koombea.domain.model.CharacterModel
import com.koombea.presenter.ui.character.screen.CharacterListScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: CharacterListViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCharactersList()
        setContent {
            AndroidtemplateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(viewModel){ model ->
                        startActivity(Intent(this, CharacterDetailActivity::class.java).apply{
                            this.putExtra("model",model)
                        })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: CharacterListViewModel,onClick:(CharacterModel)->Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Character List")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                ),
            )
        },  content = {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding(), top = it.calculateTopPadding())) {
                    CharacterListScreen(viewModel,onClick)
                }
        } )
}