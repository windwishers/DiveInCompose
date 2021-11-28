package fail.toepic.android.compose.maze.fullfitswitch.model

data class DecorateToggle(
    val on : Decorate,
    val off : Decorate,
){

    fun get(isOn : Boolean) : Decorate = if(isOn) on else off

}