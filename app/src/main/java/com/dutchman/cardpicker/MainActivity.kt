package com.dutchman.cardpicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dutchman.cardpicker.ui.theme.CardPickerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardPickerTheme {
                Scaffold {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        var selectedCard by remember {
                            mutableIntStateOf(-1)
                        }
                        if (selectedCard == -1) {
                            val drawableList = R.drawable::class.java.fields.mapNotNull { field ->
                                val name = field.name
                                val resId = field.getInt(null)
                                name to resId
                            }.filter {
                                !it.first.contains("ic_launcher")
                            }

                            LazyVerticalGrid(
                                columns = GridCells.Fixed(4),
                                verticalArrangement = Arrangement.spacedBy(
                                    8.dp
                                ),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(
                                    drawableList.size
                                ) { index ->
                                    val (name, resId) = drawableList[index]
                                    Image(
                                        painter = painterResource(resId),
                                        contentDescription = name,
                                        modifier = Modifier.clickable {
                                            selectedCard = resId
                                        }
                                    )
                                }
                            }
                        } else {
                            Image(
                                painter = painterResource(selectedCard),
                                contentDescription = "Selected Card",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }

}
