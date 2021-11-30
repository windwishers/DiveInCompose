package fail.toepic.android.compose.maze.fullfitswitch

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import fail.toepic.android.compose.maze.fullfitswitch.model.Decorate
import fail.toepic.android.compose.maze.fullfitswitch.model.DecorateToggle
import kotlinx.coroutines.flow.collect
import kotlin.math.roundToInt


@ExperimentalMaterialApi
@Composable
fun FullFitSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean,
    enable : Boolean = true,
    width: Dp = 100.dp,
    height: Dp = 60.dp,
    thumbPadding : Dp = 2.dp,
    thumbHeight :Dp = height - thumbPadding,
    thumbWidth : Dp = thumbHeight,
    onBgColor : Color = Color.White,
    onBorderColor :Color = Color.Black,
    offBgColor : Color = Color.White,
    offBorderColor :Color = Color.Black,
    thumbOnColor : Color = Color.Black,
    thumbOffColor : Color = Color.Black,
    thumbOnBorderColor : Color = thumbOnColor,
    thumbOnBorderWidth : Dp = 0.dp,
    thumbOffBorderColor : Color = thumbOffColor,
    thumbOffBorderWidth : Dp = 0.dp,
    shape : Shape = RoundedCornerShape(50),
    thumbShape : Shape = RoundedCornerShape(50),
    onText : String? = null,
    offText : String? = null,
    onPadding : PaddingValues = PaddingValues(0.dp),
    onTextStyle : TextStyle = LocalTextStyle.current,
    onTextColor : Color = Color.White,
    offPadding : PaddingValues = PaddingValues(0.dp),
    offTextStyle : TextStyle = LocalTextStyle.current,
    offTextColor : Color = Color.White,
    onCheckedChange: ((Boolean) -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {

    val onDeco = Decorate(color = onBgColor, borderColor = onBorderColor, borderWidth = 1.dp)
    val offDeco = Decorate(color = offBgColor, borderColor = offBorderColor, borderWidth = 1.dp)
    val animationColorSpec : AnimationSpec<Color> = TweenSpec(durationMillis = 100)


    val thumbOnDeco = Decorate(
        color = thumbOnColor,
        borderColor = thumbOnBorderColor,
        borderWidth = thumbOnBorderWidth
    )

    val thumbOffDeco = Decorate(
        color = thumbOffColor,
        borderColor = thumbOffBorderColor,
        borderWidth = thumbOffBorderWidth,
    )

    val animationSpec : AnimationSpec<Float> = TweenSpec(durationMillis = 100)

    val onBoxWidth : Dp = thumbWidth
    val onBoxHeight : Dp = thumbHeight
    val onBoxPadding : Dp = thumbPadding
    
    val offBoxWidth : Dp = thumbWidth
    val offBoxHeight : Dp = thumbHeight
    val offBoxPadding : Dp = thumbPadding

    // inSwitch

    FullFitSwitchImpl(
        checked = checked,
        animationSpec = animationSpec,
        animationColorSpec = animationColorSpec,
        onCheckedChange = onCheckedChange,
        enabled = enable,
        interactionSource = interactionSource,
        modifier = modifier,
        height = height,
        width = width,
        shape = shape,
        onDeco = onDeco,
        offDeco = offDeco,
        thumbOnDeco = thumbOnDeco,
        thumbOffDeco = thumbOffDeco,
        thumbHeight = thumbHeight,
        thumbWidth = thumbWidth,
        thumbPadding = thumbPadding,
        thumbShape = thumbShape,
        onBoxWidth = onBoxWidth,
        onBoxHeight = onBoxHeight,
        onBoxPadding = onBoxPadding,
        onPadding = onPadding,
        onText = onText,
        onTextColor = onTextColor,
        onTextStyle = onTextStyle,
        offBoxHeight = offBoxHeight,
        offBoxWidth = offBoxWidth,
        offBoxPadding = offBoxPadding,
        offPadding = offPadding,
        offText = offText,
        offTextColor = offTextColor,
        offTextStyle = offTextStyle
    )
}

@ExperimentalMaterialApi
@Composable
private fun FullFitSwitchImpl(
    checked: Boolean,
    animationSpec: AnimationSpec<Float>,
    animationColorSpec: AnimationSpec<Color>,
    onCheckedChange: ((Boolean) -> Unit)?,
    enabled: Boolean,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    modifier: Modifier,
    height: Dp,
    width: Dp,
    shape: Shape,
    onDeco: Decorate,
    offDeco: Decorate,
    thumbOnDeco :Decorate,
    thumbOffDeco :Decorate,
    thumbHeight: Dp,
    thumbWidth: Dp,
    thumbPadding: Dp,
    thumbShape: Shape,
    onBoxWidth: Dp,
    onBoxHeight: Dp,
    onBoxPadding: Dp,
    onPadding: PaddingValues,
    onText: String?= null,
    onTextColor: Color,
    onTextStyle: TextStyle,
    offBoxHeight: Dp,
    offBoxWidth: Dp,
    offBoxPadding: Dp,
    offPadding: PaddingValues,
    offText: String?=null,
    offTextColor: Color,
    offTextStyle: TextStyle,
) {
    val thumbPathLength = width - thumbWidth
    val minBound = 0f
    val maxBound = with(LocalDensity.current) { thumbPathLength.toPx() }


    val interactions = remember { mutableStateListOf<Interaction>() }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> interactions.add(interaction)
                is PressInteraction.Release -> interactions.remove(interaction.press)
                is PressInteraction.Cancel -> interactions.remove(interaction.press)
                is DragInteraction.Start -> interactions.add(interaction)
                is DragInteraction.Stop -> interactions.remove(interaction.start)
                is DragInteraction.Cancel -> interactions.remove(interaction.start)
            }
        }
    }

    val decoTg = DecorateToggle(onDeco,offDeco)
    val thumbDecoTg = DecorateToggle(thumbOnDeco,thumbOffDeco)

    val state = remember {
        SwipeableState(
            initialValue = checked,
            animationSpec = animationSpec,
            confirmStateChange = { true }
        )
    }


    val bodyColorState = animateColorAsState(
        targetValue = decoTg.get(checked).color,
        animationSpec = animationColorSpec,
    )

    val bodyBorderState = animateColorAsState(
        targetValue = decoTg.get(checked).borderColor,
        animationSpec = animationColorSpec,
    )


    val thumbColorState = animateColorAsState(
        targetValue =
        thumbDecoTg.get(checked).color,
        animationSpec = animationColorSpec,
    )

    val thumbBorderState = animateColorAsState(
        targetValue =
        thumbDecoTg.get(!checked).borderColor,
        animationSpec = animationColorSpec,
    )

    val thumbBorderDp = animateDpAsState(
        targetValue = thumbDecoTg.get(checked).borderWidth,
        animationSpec = tween(500)
    )

    val forceAnimationCheck = remember { mutableStateOf(false) }
    LaunchedEffect(checked, forceAnimationCheck.value) {
        if (checked != state.currentValue) {
            state.animateTo(checked)
        }
    }

    DisposableEffect(state.currentValue) {
        if (checked != state.currentValue) {
            onCheckedChange?.invoke(state.currentValue)
            forceAnimationCheck.value = !forceAnimationCheck.value
        }
        onDispose { }
    }

    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl

    val toggleableModifier =
        if (onCheckedChange != null) {
            Modifier.toggleable(
                value = checked,
                onValueChange = onCheckedChange,
                enabled = enabled,
                role = Role.Switch,
                interactionSource = interactionSource,
                indication = null
            )
        } else {
            Modifier
        }


    //body
    Box(
        modifier = modifier
            .size(height = height, width = width)
            .clip(shape)
            .background(color = bodyColorState.value)
            .border(
                border = BorderStroke(
                    color = bodyBorderState.value,
                    width = if (checked) onDeco.borderWidth else offDeco.borderWidth
                ), shape
            )
            .then(toggleableModifier)
            .swipeable(
                state = state,
                anchors = mapOf(minBound to false, maxBound to true),
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal,
                enabled = enabled && onCheckedChange != null,
                reverseDirection = isRtl,
                interactionSource = interactionSource,
                resistance = null
            )
    ) {


        //Thumb
        Box(modifier = Modifier
            .size(height = thumbHeight, width = thumbWidth)
            .padding(thumbPadding)
            .align(alignment = Alignment.CenterStart)
            .offset {
                IntOffset(state.offset.value.roundToInt(), 0)
            }
            .indication(
                interactionSource = interactionSource,
                indication = rememberRipple(bounded = false, radius = height - thumbHeight)
            )
            .clip(thumbShape)
            .border(
                border = BorderStroke(
                    width = thumbBorderDp.value,
                    color = thumbBorderState.value
                ), thumbShape
            )
            .background(color = thumbColorState.value)
        ) {

        }

        //ONBox
        if(onText != null){
            Box(
                modifier = Modifier
                    .size(height = onBoxWidth, width = onBoxHeight)
                    .padding(onBoxPadding)
                    .align(alignment = Alignment.CenterEnd), contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(onPadding),
                    text = onText,
                    color = onTextColor,
                    style = onTextStyle
                )
            }
        }

        //OFFBox
        if(offText != null){
            Box(
                modifier = Modifier
                    .size(height = offBoxHeight, width = offBoxWidth)
                    .padding(offBoxPadding)
                    .align(alignment = Alignment.CenterStart), contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(offPadding),
                    text = offText,
                    color = offTextColor,
                    style = offTextStyle
                )
            }
        }


    }
}

