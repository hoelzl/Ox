package ox.core

interface CharacterMatch {
    val proposedChar: Char
    fun isPerfectMatch(): Boolean = false
    fun isPartialMatch(): Boolean = false
}

data class PerfectCharacterMatch(override val proposedChar: Char) : CharacterMatch {
    override fun isPerfectMatch(): Boolean = true
    override fun isPartialMatch(): Boolean = true
}

data class PartialCharacterMatch(override val proposedChar: Char) : CharacterMatch {
    override fun isPartialMatch(): Boolean = true
}

data class FailedCharacterMatch(override val proposedChar: Char) : CharacterMatch

fun createCharacterMatch(proposedChar: Char, proposedIndex: Int,
                         wordToGuess: String): CharacterMatch =
        when {
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

// Convenience function that should strictly speaking not be here.
fun describeCharacterMatch(match: CharacterMatch): Char =
        when {
            match.isPerfectMatch() -> '+'
            match.isPartialMatch() -> '-'
            else -> '.'
        }
