package fail.toepic.android.study.compose.diveincompose.ui.nav

sealed class Screen(val route : String){
    object MainList : Screen("MainList")
    object NavigationMain : Screen("NavigationMain")
    object FullFitSwitch : Screen("FullFitSwitch")
}