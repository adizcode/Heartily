package com.github.adizcode.heartily

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
                    val (age, setAge) = rememberSaveable { mutableStateOf("") }
                    val (gender, setGender) = rememberSaveable { mutableStateOf("") }
                    val (education, setEducation) = rememberSaveable { mutableStateOf("") }
                    val (dailyCigarettes, setDailyCigarettes) = rememberSaveable { mutableStateOf("") }
                    val (bpMeds, setBpMeds) = rememberSaveable { mutableStateOf("") }
                    val (prevalentStroke, setPrevalentStroke) = rememberSaveable { mutableStateOf("") }
                    val (prevalentHypertension, setPrevalentHypertension) = rememberSaveable {
                        mutableStateOf(
                            ""
                        )
                    }
                    val (diabetic, setDiabetic) = rememberSaveable { mutableStateOf("") }
                    val (cholesterol, setCholesterol) = rememberSaveable { mutableStateOf("") }
                    val (systolicBp, setSystolicBp) = rememberSaveable { mutableStateOf("") }
                    val (diastolicBp, setDiastolicBp) = rememberSaveable { mutableStateOf("") }
                    val (bmi, setBmi) = rememberSaveable { mutableStateOf("") }
                    val (heartRate, setHeartRate) = rememberSaveable { mutableStateOf("") }
                    val (glucose, setGlucose) = rememberSaveable { mutableStateOf("") }

                    Column(
                        modifier = Modifier
                            .padding(it)
                            .padding(horizontal = 20.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = spacedBy(10.dp)
                    ) {
                        Text(
                            "Please tell us about yourself",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        OutlinedTextField(
                            value = age,
                            onValueChange = setAge,
                            placeholder = { Text("Age") }
                        )
                        OutlinedTextField(
                            value = gender,
                            onValueChange = setGender,
                            placeholder = { Text("Gender") }
                        )
                        OutlinedTextField(
                            value = education,
                            onValueChange = setEducation,
                            placeholder = { Text("Educational Qualification") }
                        )
                        OutlinedTextField(
                            value = dailyCigarettes,
                            onValueChange = setDailyCigarettes,
                            placeholder = { Text("Cigarettes Per Day") }
                        )
                        OutlinedTextField(
                            value = bpMeds,
                            onValueChange = setBpMeds,
                            placeholder = { Text("Currently on any Blood Pressure Meds") }
                        )
                        OutlinedTextField(
                            value = prevalentStroke,
                            onValueChange = setPrevalentStroke,
                            placeholder = { Text("Prevalent Stroke") }
                        )
                        OutlinedTextField(
                            value = prevalentHypertension,
                            onValueChange = setPrevalentHypertension,
                            placeholder = { Text("Prevalent Hypertension") }
                        )
                        OutlinedTextField(
                            value = diabetic,
                            onValueChange = setDiabetic,
                            placeholder = { Text("Diabetic or not") }
                        )
                        OutlinedTextField(
                            value = cholesterol,
                            onValueChange = setCholesterol,
                            placeholder = { Text("Total Cholesterol") }
                        )
                        OutlinedTextField(
                            value = systolicBp,
                            onValueChange = setSystolicBp,
                            placeholder = { Text("Systolic Blood Pressure") }
                        )
                        OutlinedTextField(
                            value = diastolicBp,
                            onValueChange = setDiastolicBp,
                            placeholder = { Text("Diastolic Blood Pressure") }
                        )
                        OutlinedTextField(
                            value = bmi,
                            onValueChange = setBmi,
                            placeholder = { Text("Body Mass Index") }
                        )
                        OutlinedTextField(
                            value = heartRate,
                            onValueChange = setHeartRate,
                            placeholder = { Text("Heart Rate") }
                        )
                        OutlinedTextField(
                            value = glucose,
                            onValueChange = setGlucose,
                            placeholder = { Text("Glucose") }
                        )

                        Button(onClick = { /*TODO*/ }) {
                            Text("Calculate Heart Health")
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