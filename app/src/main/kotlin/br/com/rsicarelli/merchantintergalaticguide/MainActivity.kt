package br.com.rsicarelli.merchantintergalaticguide

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import br.com.rsicarelli.merchantintergalaticguide.extensions.hide
import br.com.rsicarelli.merchantintergalaticguide.extensions.show
import br.com.rsicarelli.merchantintergalaticguide.rules.IntergalacticConverter
import br.com.rsicarelli.merchantintergalaticguide.rules.MerchantMap
import br.com.rsicarelli.merchantintergalaticguide.widget.SymbolBadgeView
import br.com.rsicarelli.merchantintergalaticguide.widget.SymbolView
import com.google.android.flexbox.FlexboxLayout

class MainActivity : AppCompatActivity() {

    companion object {
        private const val GOLD: String = "gold"
        private const val SILVER: String = "silver"
        private const val IRON: String = "iron"
    }

    val glob by lazy { findViewById(R.id.glob) as SymbolView }
    val prok by lazy { findViewById(R.id.prok) as SymbolView }
    val pish by lazy { findViewById(R.id.pish) as SymbolView }
    val tegj by lazy { findViewById(R.id.tegj) as SymbolView }

    val gold by lazy { findViewById(R.id.gold) as SymbolView }
    val silver by lazy { findViewById(R.id.silver) as SymbolView }
    val iron by lazy { findViewById(R.id.iron) as SymbolView }

    val reset by lazy { findViewById(R.id.reset) as Button }

    val selectedBadges by lazy { findViewById(R.id.tags) as FlexboxLayout }
    val elements by lazy { findViewById(R.id.elements) as LinearLayout }
    val resultTitle by lazy { findViewById(R.id.result_title) as TextView }
    val result by lazy { findViewById(R.id.result) as TextView }

    val symbolsToConvert: ArrayList<MerchantMap> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpButtons()
    }

    private fun setUpButtons() {
        glob.setOnClickListener {
            onSymbolClick(MerchantMap.GLOB)
        }

        prok.setOnClickListener {
            onSymbolClick(MerchantMap.PROK)
        }

        pish.setOnClickListener {
            onSymbolClick(MerchantMap.PISH)
        }

        tegj.setOnClickListener {
            onSymbolClick(MerchantMap.TEGJ)
        }

        gold.setOnClickListener {
            calculateResultFor(GOLD)
        }

        silver.setOnClickListener {
            calculateResultFor(SILVER)
        }

        iron.setOnClickListener {
            calculateResultFor(IRON)
        }

        reset.setOnClickListener {
            resetState()
        }
    }

    private fun resetState() {
        result.text = ""
        symbolsToConvert.clear()
        selectedBadges.removeAllViewsInLayout()
        elements.hide()
        resultTitle.hide()
        reset.hide()
    }

    private fun calculateResultFor(element: String) {
        try {
            val value = IntergalacticConverter().getConvertedValue(symbolsToConvert, element).getFormattedValue()
            result.text = "$value credits"
            resultTitle.show()
        } catch (e: IllegalArgumentException) {
            Snackbar.make(reset, e.message as CharSequence, Toast.LENGTH_SHORT).show()
        }
    }

    private fun onSymbolClick(merchantMap: MerchantMap) {
        toggleElements()

        symbolsToConvert.add(merchantMap)

        selectedBadges.addView(
                SymbolBadgeView(
                        description = merchantMap.name,
                        context = this
                )
        )
    }

    private fun toggleElements() {
        if (symbolsToConvert.size == 0) {
            elements.show()
        }
        reset.show()
    }
}
