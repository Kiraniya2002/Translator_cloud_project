 package com.example.translator_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.translator_app.ui.theme.Translator_appTheme
import com.huawei.hms.mlsdk.common.MLApplication
import dagger.hilt.android.AndroidEntryPoint

 @AndroidEntryPoint
class MainActivity : ComponentActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         MLApplication.getInstance().apiKey = resources.getString(R.string.api_key)
         setContent {
             Translator_appTheme {
                 // A surface container using the 'background' color from the theme
                 Surface(
                     modifier = Modifier.fillMaxSize(),
                     color = MaterialTheme.colorScheme.background
                 ) {
                     //MainScreen()
                     val navController = rememberNavController()
                     NavHost(
                         navController = navController,
                         startDestination = "translate_screen"
                     ) {
                         composable("translate_screen") { TranslateScreen() }
                     }
                 }
             }
         }
     }
 }
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(){
    val list= listOf("From","English","Africaans","Arabic","Belarusian","Bulgarian","Bengali","Catalan","Czech","Welsh","Hindi","Urdu");
    val tolist= listOf("To","English","Africaans","Arabic","Belarusian","Bulgarian","Bengali","Catalan","Czech","Welsh","Hindi","Urdu");
    val expanded= remember {
        mutableStateOf(false)
    }
    val currentVal= remember {
        mutableStateOf(list[0])
    }
    val toexpanded= remember {
        mutableStateOf(false)
    }
    val tocurrentVal= remember {
        mutableStateOf(tolist[0])
    }
    var inputTex by remember {
        mutableStateOf("")
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier= Modifier
            .fillMaxSize()
            .padding(all = 40.dp)) {
        Text(text = "Language Translator", fontSize = 30.sp)
        Row(modifier = Modifier.padding(top =40.dp)) {
                Box(){
                    Row(modifier = Modifier.clickable {
                        expanded.value=!expanded.value
                    }) {
                        Text(text = currentVal.value)
                        Icon(imageVector = Icons.Filled.ArrowDropDown ,
                            contentDescription
                            = null)
                        DropdownMenu(
                            expanded = expanded.value,
                            onDismissRequest = { expanded
                                .value=false}) {
                            list.forEach{
                                DropdownMenuItem(
                                    text = { Text(text = it)},
                                    onClick = { currentVal.value=it
                                        expanded.value=false})
                            }
                        }
                    }
                }


            Box(){
                    Icon(imageVector =Icons.Filled.KeyboardArrowRight , contentDescription
                    = null)
            }
            Box(){
                Row(modifier = Modifier.clickable {
                    toexpanded.value=!toexpanded.value
                }) {
                    Text(text = tocurrentVal.value)
                    Icon(imageVector = Icons.Filled.ArrowDropDown ,
                        contentDescription
                        = null)
                    DropdownMenu(
                        expanded = toexpanded.value,
                        onDismissRequest = { toexpanded
                            .value=false}) {
                        tolist.forEach{
                            DropdownMenuItem(
                                text = { Text(text = it)},
                                onClick = { tocurrentVal.value=it
                                    toexpanded.value=false})
                        }
                    }
                }
            }
        }
        TextField(value = inputTex, onValueChange = {inputTex=it},label =
        { Text(text = "Input Text to be translated",color = Color
            .Gray,fontSize = 14.sp) },colors =
        TextFieldDefaults
            .textFieldColors(
                containerColor = Color(0xFFfafafa) , textColor =Color
                    .Black, cursorColor = Color.Black
            ), modifier =
        Modifier
            .height(68.dp)
            .width(330.dp)
            .padding(top = 10.dp)
            .border(
                width = 0.5.dp, color =
                Color.LightGray, shape = CutCornerShape(6)
            ))
        Text(text = "OR")
        Image(painterResource(id = R.drawable.ic_mic),
            contentDescription =null)
        Text(text = "Say Something")
        Button(onClick = {}, colors = ButtonDefaults.buttonColors
            (Color(0xFF0095f6)),shape = CutCornerShape(10), modifier
        = Modifier
            .padding(all = 20.dp)
            .height(38.dp)
            .width(330.dp)
        ) {
            Text(text = "Translate",Modifier.padding
                (start
            = 10.dp), color = Color.White
            )
        }
        Text(text = "Translated Text")

    }
}
fun Greeting(name: String, modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Translator_appTheme {
        Greeting("Android")
    }
}