package br.com.rsicarelli.merchantintergalaticguide.rules

import br.com.rsicarelli.merchantintergalaticguide.extensions.firstString
import br.com.rsicarelli.merchantintergalaticguide.extensions.lastString
import br.com.rsicarelli.merchantintergalaticguide.extensions.removeSuffixAndTrim
import br.com.rsicarelli.merchantintergalaticguide.extensions.splitByBlankSpace

class ElementsDecryption {

    companion object {
        private val charades = arrayListOf(
                "glob glob Silver is 34 Credits",
                "glob prok Gold is 57800 Credits",
                "pish pish Iron is 3910 Credits"
        )
    }

    private var elements: HashMap<String?, Element> = hashMapOf()

    init {
        decryptElements()
    }

    fun decryptElements() {
        charades.forEach {
            var credits: Double = 0.0
            var element: String? = null
            val merchantMap = arrayListOf<MerchantMap>()

            it.split("( is )"
                    .toRegex())
                    .forEachIndexed { index, word ->
                        if (index == 0) {
                            element = word.lastString()

                            val removedSuffix = word.removeSuffixAndTrim(element.toString())

                            removedSuffix
                                    .splitByBlankSpace()
                                    .forEach {
                                        merchantMap.add(MerchantMap.getHumanRepresentation(it))
                                    }
                        } else {
                            credits = word.firstString().toDouble()
                        }
                    }

            val resultCredits = Credits(symbols = merchantMap)

            elements.put(element!!.toLowerCase(),
                    Element(
                            type = element,
                            credits = credits / resultCredits.getMultiplier()
                    )
            )
        }
    }

    fun getElement(element: String) = elements[element]
}