package br.com.rsicarelli.merchantintergalaticguide.extensions

import br.com.rsicarelli.merchantintergalaticguide.rules.MerchantMap

fun List<MerchantMap>.elementAtLast(index: Int) = this.elementAtOrElse(index - 1, defaultValue = {
    MerchantMap.INVALID
})