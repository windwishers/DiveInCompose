package fail.toepic.android.study.compose.diveincompose.ui.mainlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fail.toepic.android.study.compose.diveincompose.ui.nav.Screen

data class Items(val text : String, val nav : Screen)

val list = listOf(
    Items("Navigation",Screen.NavigationMain),
    Items("FullFitSwitch",Screen.FullFitSwitch),
)

@Composable
fun MainListScreen(nav : (Screen)->Unit ){
    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(list){ (label, screen) ->
            Box(modifier = Modifier
                .clickable { nav.invoke(screen) }
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(48.dp)
            ){
                Text(text = label.uppercase(), modifier = Modifier.align(Alignment.CenterStart),style = MaterialTheme.typography.h5)
            }
            Divider(thickness = 3.dp,color = MaterialTheme.colors.primary, modifier = Modifier.padding(horizontal = 4.dp))
        }
    }
}

@Preview
@Composable
fun MainListScreenTest(){
    MainListScreen(nav = {})
}