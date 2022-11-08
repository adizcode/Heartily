package com.github.adizcode.heartily

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.adizcode.heartily.ui.theme.HeartilyTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HeartilyTheme {
                val predictionStage = rememberSaveable { mutableStateOf("Main") }
                when (predictionStage.value) {
                    "Main" -> {
                        HeartilyApp { predictionStageNew ->
                            predictionStage.value = predictionStageNew
                        }
                    }
                    "PredictionTrue" -> {
                        PredictionTrue()
                    }
                    "PredictionFalse" -> {
                        PredictionFalse()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeartilyApp(onPredictionStageChange: (String) -> Unit) {
    val currentPage = rememberSaveable { mutableStateOf("Home") }

    Scaffold(topBar = {
        TopAppBar(title = { Text("Heartily") })
    }, bottomBar = {
        NavigationBar {
            NavigationBarItem(
                selected = currentPage.value == "Home",
                onClick = { currentPage.value = "Home" },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home, contentDescription = null
                    )
                },
                label = { Text("Home") })
            NavigationBarItem(selected = false, onClick = { }, icon = {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = null
                )
            }, label = { Text("Search") })
            NavigationBarItem(
                selected = currentPage.value == "Info",
                onClick = { currentPage.value = "Info" },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Person, contentDescription = null
                    )
                },
                label = { Text("Info") })
        }
    }, floatingActionButton = {
        FloatingActionButton(onClick = { }) {
            Icon(imageVector = Icons.Default.Create, contentDescription = null)
        }
    }) {
        when (currentPage.value) {
            "Home" -> {
                HomePage(it) { newPage -> onPredictionStageChange(newPage) }
            }
            "Info" -> {
                InfoPage(it)
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomePage(paddingValues: PaddingValues, onPageUpdate: (String) -> Unit) {
    val (age, setAge) = rememberSaveable { mutableStateOf("") }
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
    val (bmi, setBmi) = rememberSaveable { mutableStateOf("") }
    val (heartRate, setHeartRate) = rememberSaveable { mutableStateOf("") }

    fun onCalculate() {
        // TODO: Validate input

        val ageNum = age.toInt()
        val cholesterolNum = cholesterol.toInt()
        val dailyCigarettesNum = dailyCigarettes.toInt()

        // TODO: Make toggle buttons
        val isOnBpMeds = bpMeds.isNotBlank()
        val hasPrevalentStroke = prevalentStroke.isNotBlank()
        val hasPrevalentHypertension = prevalentHypertension.isNotBlank()
        val isDiabetic = diabetic.isNotBlank()


        val bmiNum = bmi.toFloat()
        val heartRateNum = heartRate.toInt()

        val isHeartDiseaseLikely =
            ageNum >= 65 || cholesterolNum >= 240 || dailyCigarettesNum >= 1 || isOnBpMeds || hasPrevalentStroke || hasPrevalentHypertension || isDiabetic || bmiNum >= 35 || heartRateNum < 60 || heartRateNum > 100

        val newPage = if (isHeartDiseaseLikely) "PredictionTrue" else "PredictionFalse"
        onPageUpdate(newPage)
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = spacedBy(10.dp)
    ) {
        Text(
            "Your Bio Details", style = MaterialTheme.typography.headlineMedium
        )
        OutlinedTextField(
            value = age,
            onValueChange = setAge,
            placeholder = { Text("Age") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = dailyCigarettes,
            onValueChange = setDailyCigarettes,
            placeholder = { Text("Cigarettes Per Day") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(value = bpMeds,
            onValueChange = setBpMeds,
            placeholder = { Text("Currently on any Blood Pressure Meds") })
        OutlinedTextField(value = prevalentStroke,
            onValueChange = setPrevalentStroke,
            placeholder = { Text("Prevalent Stroke") })
        OutlinedTextField(value = prevalentHypertension,
            onValueChange = setPrevalentHypertension,
            placeholder = { Text("Prevalent Hypertension") })
        OutlinedTextField(value = diabetic,
            onValueChange = setDiabetic,
            placeholder = { Text("Diabetic or not") })
        OutlinedTextField(
            value = cholesterol,
            onValueChange = setCholesterol,
            placeholder = { Text("Total Cholesterol") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = bmi,
            onValueChange = setBmi,
            placeholder = { Text("Body Mass Index") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
        OutlinedTextField(
            value = heartRate,
            onValueChange = setHeartRate,
            placeholder = { Text("Heart Rate") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(onClick = { onCalculate() }) {
            Text("Calculate Heart Health")
        }
    }
}

@Composable
fun InfoPage(paddingValues: PaddingValues) {
    Text(modifier = Modifier.padding(paddingValues), text = "Info")
}

@Composable
fun PredictionTrue() {
    Text(text = "True")
}

@Composable
fun PredictionFalse() {
    Text(text = "False")
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