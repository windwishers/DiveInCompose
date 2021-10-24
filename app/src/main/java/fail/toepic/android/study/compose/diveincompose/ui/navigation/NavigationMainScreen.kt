package fail.toepic.android.study.compose.diveincompose.ui.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fail.toepic.android.study.compose.diveincompose.ui.nav.Screen

/**
 * ref : https://developer.android.com/jetpack/compose/navigation#nav-with-args
 * 1. navigate Composable
 *  1-1. navigate : inclusive = true
 *  1-2. navigate : launchSingleTop = true
 *  1-3. navigate : 뭐가 더 있나 확인 할 것.
 * 2. navigate with argument
 * 3. navigate with optional argument
 * 4. deep link
 * 5. nest navigation
 * 6. pending - Integration with the bottom nav bar
 * 7. pending - Interoperability
 * 8. pending - Testing
 */
@Composable
fun NavigationMainScreen(nav : (Screen)->Unit ){
    Column(Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .clickable {
                nav.invoke(Screen.MainList)
            }
            .heightIn(48.dp)
            .padding(start = 16.dp)
        ){
            Text(
                text = Screen.NavigationMain.route.uppercase(),
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.h5
            )
        }
        Divider(
            thickness = 4.dp,
            color = MaterialTheme.colors.secondary,
            startIndent = 2.dp
        )

        LazyColumn(modifier = Modifier.fillMaxSize()){

        }

    }
}

//

@Preview
@Composable
private fun Preview(){
    NavigationMainScreen(nav = {})
}