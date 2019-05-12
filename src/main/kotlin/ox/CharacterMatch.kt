package ox

interface CharacterMatch {
    val proposedChar: Char
    val displayChar: Char
    fun isPerfectMatch(): Boolean = false
    fun isPartialMatch(): Boolean = false
}

data class PerfectCharacterMatch(override val proposedChar: Char) : CharacterMatch {
    override val displayChar: Char
        get() = proposedChar

    override fun isPerfectMatch(): Boolean = true
    override fun isPartialMatch(): Boolean = true
}

data class PartialCharacterMatch(override val proposedChar: Char) : CharacterMatch {
    override val displayChar: Char
        get() = '+'

    override fun isPartialMatch(): Boolean = true
}

data class FailedCharacterMatch(override val proposedChar: Char) : CharacterMatch {
    override val displayChar: Char
        get() = '-'
}

fun createCharacterMatch(proposedChar: Char, proposedIndex: Int,
                         wordToGuess: String): CharacterMatch {
    return when {
        wordToGuess[proposedIndex].equals(proposedChar, ignoreCase = true) -> {
            PerfectCharacterMatch(proposedChar)
        }
        wordToGuess.contains(proposedChar, ignoreCase = true) -> {
            PartialCharacterMatch(proposedChar)
        }
        else -> {
            FailedCharacterMatch(proposedChar)
        }
    }
}