package com.koombea.presenter.ui.character

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.koombea.androidtemplate.ui.theme.AndroidtemplateTheme
import com.koombea.domain.model.CharacterModel
import com.koombea.presenter.ui.character.screen.CharacterDetailScreen

class CharacterDetailActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var model = intent.getParcelableExtra<CharacterModel>("model")
        setContent {
            AndroidtemplateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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
                                navigationIcon = {
                                    IconButton(onClick = { this.finish() }) {
                                        Icon(Icons.Filled.ArrowBack, contentDescription = "Menu")
                                    }
                                }
                            )
                        },  content = {
                            Column(modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = it.calculateBottomPadding(), top = it.calculateTopPadding())) {
                                 CharacterDetailScreen(model)
                            }
                        } )
                }
            }
        }
    }
}