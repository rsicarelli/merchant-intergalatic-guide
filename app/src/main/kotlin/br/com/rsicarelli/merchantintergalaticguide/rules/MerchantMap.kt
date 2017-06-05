package br.com.rsicarelli.merchantintergalaticguide.rules

enum class MerchantMap constructor(
        val value: Int = 0,
        val canRepeat: Boolean = false,
        val canBeSubtractedFrom: () -> ArrayList<MerchantMap>? = { arrayListOf<MerchantMap>() }
) {
    GLOB(
            value = 1,
            canRepeat = true,
            canBeSubtractedFrom = { arrayListOf(PROK, PISH) }
    ),
    PROK(
            value = 5
    ),
    PISH(
            value = 10,
            canRepeat = true,
            canBeSubtractedFrom = { arrayListOf(TEGJ) }

    ),
    TEGJ(
            value = 50
    ),
    INVALID;

    companion object {
        private val representations = hashMapOf(
                "glob" to GLOB,
                "prok" to PROK,
                "pish" to PISH,
                "tegj" to TEGJ
        )

        fun getHumanRepresentation(symbol: String) = representations[symbol.toLowerCase()] ?: INVALID

    }
}