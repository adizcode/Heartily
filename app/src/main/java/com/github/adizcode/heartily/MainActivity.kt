package com.github.adizcode.heartily

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
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
    val (bpMeds, setBpMeds) = rememberSaveable { mutableStateOf(false) }
    val (prevalentStroke, setPrevalentStroke) = rememberSaveable { mutableStateOf(false) }
    val (prevalentHypertension, setPrevalentHypertension) = rememberSaveable {
        mutableStateOf(
            false
        )
    }
    val (diabetic, setDiabetic) = rememberSaveable { mutableStateOf(false) }
    val (cholesterol, setCholesterol) = rememberSaveable { mutableStateOf("") }
    val (bmi, setBmi) = rememberSaveable { mutableStateOf("") }
    val (heartRate, setHeartRate) = rememberSaveable { mutableStateOf("") }

    fun onCalculate() {
        // TODO: Validate input

        val ageNum = age.toInt()
        val cholesterolNum = cholesterol.toInt()
        val dailyCigarettesNum = dailyCigarettes.toInt()


        val bmiNum = bmi.toFloat()
        val heartRateNum = heartRate.toInt()

        val isHeartDiseaseLikely =
            ageNum >= 65 || cholesterolNum >= 240 || dailyCigarettesNum >= 1 || bpMeds || prevalentStroke || prevalentHypertension || diabetic || bmiNum >= 35 || heartRateNum < 60 || heartRateNum > 100

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
            "Heart Health Calculator", style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(5.dp))
        Row(horizontalArrangement = SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "I am ")
            OutlinedTextField(
                value = age,
                onValueChange = setAge,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .width(64.dp)
                    .height(48.dp),
            )
            Text(" years old.")
        }
        Row(horizontalArrangement = SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "I smoke ")
            OutlinedTextField(
                value = dailyCigarettes,
                onValueChange = setDailyCigarettes,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .width(64.dp)
                    .height(48.dp)
            )
            Text(" cigarettes per day.")
        }
        Row(horizontalArrangement = SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "My total cholesterol is ")
            OutlinedTextField(
                value = cholesterol,
                onValueChange = setCholesterol,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .width(64.dp)
                    .height(48.dp)
            )
            Text(" mg / dL.")
        }
        Row(horizontalArrangement = SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "My Body Mass Index is ")
            OutlinedTextField(
                value = bmi,
                onValueChange = setBmi,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier
                    .width(64.dp)
                    .height(48.dp)
            )
            Text(" .")
        }
        Row(horizontalArrangement = SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "My heart rate is ")
            OutlinedTextField(
                value = heartRate,
                onValueChange = setHeartRate,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .width(64.dp)
                    .height(48.dp)
            )
            Text(" beats per minute.")
        }

        LabelledCheckBox(
            checked = bpMeds,
            onCheckedChange = setBpMeds,
            label = "I'm currently on blood pressure meds."
        )

        LabelledCheckBox(
            checked = prevalentStroke,
            onCheckedChange = setPrevalentStroke,
            label = "I've had a prevalent stroke."
        )

        LabelledCheckBox(
            checked = prevalentHypertension,
            onCheckedChange = setPrevalentHypertension,
            label = "I've had prevalent hypertension."
        )

        LabelledCheckBox(
            checked = diabetic,
            onCheckedChange = setDiabetic,
            label = "I am diabetic."
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
fun LabelledCheckBox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit),
    label: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable(
                indication = rememberRipple(color = MaterialTheme.colorScheme.primary),
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onCheckedChange(!checked) }
            )
            .requiredHeight(ButtonDefaults.MinHeight)
            .padding(horizontal = 4.dp, vertical = 10.dp)
    ) {

        Text(
            text = label,
        )

        Spacer(Modifier.weight(1f))

        Checkbox(
            checked = checked,
            onCheckedChange = null
        )
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