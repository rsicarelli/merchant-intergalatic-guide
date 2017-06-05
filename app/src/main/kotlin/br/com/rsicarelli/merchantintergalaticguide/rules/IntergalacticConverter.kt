package br.com.rsicarelli.merchantintergalaticguide.rules

import br.com.rsicarelli.merchantintergalaticguide.extensions.elementAtLast

class IntergalacticConverter {

    val elementsDecryption = ElementsDecryption()

    fun getConvertedValue(selectedSymbols: ArrayList<MerchantMap>, element: String): Credits {
        val elementToConvert = elementsDecryption.getElement(element)
        val intergalacticValidator: IntergalacticValidator = IntergalacticValidator()

        selectedSymbols
                .forEachIndexed { index, currentMerchantMap ->
                    val previousMerchantMap = selectedSymbols.elementAtLast(index)

                    intergalacticValidator.validateBlock(currentMerchantMap, previousMerchantMap)
                }

        return Credits(selectedSymbols, elementToConvert)
    }
}

