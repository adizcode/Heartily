package com.github.adizcode.heartily

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.adizcode.heartily.ui.theme.HeartilyTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HeartilyTheme {
                Scaffold(topBar = {
                    TopAppBar(title = { Text("Heartily") })
                }, bottomBar = {
                    NavigationBar {
                        NavigationBarItem(selected = true, onClick = { }, icon = {
                            Icon(
                                imageVector = Icons.Default.Home, contentDescription = null
                            )
                        }, label = { Text("Home") })
                        NavigationBarItem(selected = false, onClick = { }, icon = {
                            Icon(
                                imageVector = Icons.Default.Search, contentDescription = null
                            )
                        }, label = { Text("Search") })
                        NavigationBarItem(selected = false, onClick = { }, icon = {
                            Icon(
                                imageVector = Icons.Default.Person, contentDescription = null
                            )
                        }, label = { Text("Profile") })
                    }
                }, floatingActionButton = {
                    FloatingActionButton(onClick = { }) {
                        Icon(imageVector = Icons.Default.Create, contentDescription = null)
                    }
                }) {
                    LazyColumn(modifier = Modifier.padding(it)) {
                        items(5) {
                            Card(modifier = Modifier
                                .padding(20.dp)
                                .clickable { }) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("Test Box")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HeartilyTheme {
        Greeting("Android")
    }
}