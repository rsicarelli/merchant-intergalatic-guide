package br.com.rsicarelli.merchantintergalaticguide.widget

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.TextView
import br.com.rsicarelli.merchantintergalaticguide.R

class SymbolBadgeView @JvmOverloads constructor(
        context: Context,
        attributeSet: AttributeSet? = null,
        defStyleAttr: Int = 0,
        val description: String
) : TextView(context, attributeSet, defStyleAttr) {

    init {
        text = description
        background = ContextCompat.getDrawable(context, R.drawable.rounded_corner_shape)
    }
}
