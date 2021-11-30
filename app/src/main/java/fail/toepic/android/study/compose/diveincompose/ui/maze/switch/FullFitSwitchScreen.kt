package fail.toepic.android.study.compose.diveincompose.ui.maze.switch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fail.toepic.android.compose.maze.fullfitswitch.FullFitSwitch
import fail.toepic.android.study.compose.diveincompose.ui.nav.Screen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FullFitSwitchScreen(nav : (Screen)->Unit ){
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)) {
        Row(modifier = Modifier
            .clickable {
                nav.invoke(Screen.MainList)
            }
            .heightIn(48.dp)
            .padding(start = 16.dp)
        ){
            Text(
                text = Screen.FullFitSwitch.route.uppercase(),
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.h5
            )
        }
        Divider(
            thickness = 4.dp,
            color = MaterialTheme.colors.secondary,
            startIndent = 2.dp
        )

        LazyColumn(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.spacedBy(10.dp)){

            item{
                Divider()
            }

            item{
                val checked = remember{ mutableStateOf(false) }
                Box(Modifier.fillMaxWidth()){
                    FullFitSwitch(
                        modifier = Modifier.align(Alignment.Center),
                        checked = checked.value,onCheckedChange = {checked.value = it}
                    )
                }
            }

            item{
                Divider()
            }

            item{

                Column {
                    val checked = remember{ mutableStateOf(false) }
                    Text(text = checked.value.toString() )

                    FullFitSwitch(
                        checked = checked.value,onCheckedChange = {checked.value = it},
                        onText = "ON",
                        offText = "OFF",
                    )
                }

            }
        }

    }
}

@Preview
@Composable
private fun Preview(){
    FullFitSwitchScreen(nav = {})
}