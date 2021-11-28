package fail.toepic.android.compose.common.color

import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import androidx.compose.ui.graphics.Color

fun Color.alpha(@FloatRange(from = 0.0, to = 1.00) alpha : Float = 1.0f) = this.copy(alpha = alpha)


fun Color.alpha( @IntRange(from = 0, to = 100) alphaRate : Int = 100) = this.copy(alpha = alphaRate / 100f)