package fail.toepic.android.compose.test4.modifier

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fail.toepic.android.study.compose.diveincompose.BuildConfig


@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.testBorder(width : Dp = 1.dp,color : Color = Color.Black, shape : Shape = RoundedCornerShape(0.dp) ): Modifier{
    return if(BuildConfig.DEBUG){
        composed {
            Modifier.border(width = 1.dp,color = Color.Blue)
        }
    }else{
        this
    }
}

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.testBColor(color : Color = Color.Black, shape : Shape = RoundedCornerShape(0.dp) ): Modifier{
    return if(BuildConfig.DEBUG){
        composed {
            Modifier.background(color = color, shape = shape)
        }
    }else{
        this
    }
}

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.testBox(border: Color = Color.Black ,color : Color = Color.LightGray, shape : Shape = RoundedCornerShape(0.dp) ): Modifier{
    return if(BuildConfig.DEBUG){
        composed {
            Modifier.background(color = color, shape = shape)
        }
    }else{
        this
    }
}

