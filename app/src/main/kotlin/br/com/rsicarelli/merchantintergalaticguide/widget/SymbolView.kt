package br.com.rsicarelli.merchantintergalaticguide.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import br.com.rsicarelli.merchantintergalaticguide.R

class SymbolView @JvmOverloads constructor(
        context: Context,
        attributeSet: AttributeSet? = null,
        defStyleAttr: Int = 0
) : TextView(context, attributeSet, defStyleAttr) {

    init {
        compoundDrawablePadding = resources.getDimensionPixelOffset(R.dimen.key_line)

        val styledAttrs = context.obtainStyledAttributes(attributeSet, R.styleable.SymbolView, 0, 0)

        try {
            val title = styledAttrs.getString(R.styleable.SymbolView_coinViewTitle)
            val icon = styledAttrs.getDrawable(R.styleable.SymbolView_coinViewIcon)

            text = title
            setCompoundDrawablesWithIntrinsicBounds(null, icon, null, null)
        } finally {
            styledAttrs.recycle()
        }
    }
}
