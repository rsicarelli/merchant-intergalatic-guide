package br.com.rsicarelli.merchantintergalaticguide.rules

import br.com.rsicarelli.merchantintergalaticguide.extensions.elementAtLast

class Credits(
        val symbols: List<MerchantMap> = arrayListOf<MerchantMap>(),
        val element: Element? = null
) {
    fun getMultiplier(): Int {
        var multiplier: Int = 0
        symbols.forEachIndexed { index, merchantMap ->
            val previousMerchantMap = symbols.elementAtLast(index)

            if (previousMerchantMap == MerchantMap.INVALID) {
                multiplier += merchantMap.value
                return@forEachIndexed
            }

            if (isBiggerOrEqual(previousMerchantMap.value, merchantMap.value)) {
                multiplier += merchantMap.value
            } else {
                multiplier = merchantMap.value - previousMerchantMap.value
            }
        }
        return multiplier
    }

    fun getFormattedValue() = (element?.credits!! * getMultiplier()).toString()

    fun isBiggerOrEqual(previousValue: Int, currentValue: Int) = previousValue >= currentValue
}