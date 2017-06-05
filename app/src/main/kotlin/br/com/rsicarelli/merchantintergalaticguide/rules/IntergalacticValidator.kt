package br.com.rsicarelli.merchantintergalaticguide.rules

class IntergalacticValidator {

    companion object {
        val INITIAL_SEQUENCE = 1
        val MAX_SEQUENCES: Int = 3
    }

    private var sequenceHits: Int = INITIAL_SEQUENCE

    var currentMerchantMap: MerchantMap? = null
    var previousMerchantMap: MerchantMap? = null

    fun validateBlock(currentMerchantMap: MerchantMap, previousMerchantMap: MerchantMap) {
        this.currentMerchantMap = currentMerchantMap
        this.previousMerchantMap = previousMerchantMap

        if (hasPreviousMap()) {
            validateSequence()
        }
    }

    private fun hasPreviousMap() = previousMerchantMap != MerchantMap.INVALID

    private fun validateSequence() {
        isValidSequence()
        if (isSameSequence()) {
            canRepeat()
            updateHits()
        } else {
            canSubtract()
        }
    }

    fun isValidSequence(): Boolean {
        if (currentMerchantMap?.value!! >= previousMerchantMap?.value!!) {
            return true
        } else {
            throw IllegalArgumentException("You can't subtract smaller symbols: " +
                    "$previousMerchantMap $currentMerchantMap")
        }
    }

    fun canRepeat(): Boolean {
        if (currentMerchantMap!!.canRepeat) {
            if (sequenceHits <= MAX_SEQUENCES)
                return true
            else
                throw IllegalArgumentException("You can't repeat the symbol $currentMerchantMap more " +
                        "than ${MAX_SEQUENCES} times")
        } else {
            throw IllegalArgumentException("You can't repeat the symbol $currentMerchantMap")
        }
    }

    fun isSameSequence() = currentMerchantMap == previousMerchantMap

    fun canSubtract(): Boolean {
        currentMerchantMap?.let {
            val canBeSubtractedFrom = it.canBeSubtractedFrom() as ArrayList<MerchantMap>

            if (canBeSubtractedFrom.size > 0) {
                canBeSubtractedFrom.forEach {
                    return it == previousMerchantMap
                }
            }
        }
        return false
    }

    private fun updateHits() {
        sequenceHits += 1
    }
}