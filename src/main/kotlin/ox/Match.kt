package ox

class Match(wordToGuess: String, proposedSolution: String) {
    private val characterMatches: MutableList<CharacterMatch> = mutableListOf()

    init {
        for (index in 0 until wordToGuess.length) {
            if (proposedSolution.length > index) {
                characterMatches.add(
                        createCharacterMatch(proposedSolution[index], index, wordToGuess))
            } else {
                characterMatches.add(FailedCharacterMatch('.'))
            }
        }
    }

    fun isPerfectMatch(): Boolean {
        return characterMatches.all(CharacterMatch::isPerfectMatch)
    }

    fun isPartialMatch(): Boolean {
        return characterMatches.any(CharacterMatch::isPartialMatch)
    }

    fun proposedWord(): String {
        return characterMatches.map { it.proposedChar }.joinToString(separator = "")
    }

    fun matchDescription(): String {
        return characterMatches.map { it.descriptiveChar }.joinToString(separator = "")
    }
}