package ox.core

class Match(wordToGuess: String, proposedSolution: String) {
    private val _characterMatches: MutableList<CharacterMatch> = mutableListOf()

    init {
        for (index in 0 until wordToGuess.length) {
            if (proposedSolution.length > index) {
                _characterMatches.add(
                        createCharacterMatch(proposedSolution[index], index, wordToGuess))
            } else {
                _characterMatches.add(FailedCharacterMatch('.'))
            }
        }
    }

    fun isPerfectMatch(): Boolean {
        return _characterMatches.all(CharacterMatch::isPerfectMatch)
    }

    fun isPartialMatch(): Boolean {
        return _characterMatches.any(CharacterMatch::isPartialMatch)
    }

    fun proposedWord(): String {
        return _characterMatches.map { it.proposedChar }.joinToString(separator = "")
    }

    fun describeMatch(descriptionGenerator: (CharacterMatch) -> Char): String {
        return _characterMatches.map { descriptionGenerator(it) }.joinToString(separator = "")
    }

    val characterMatches: List<CharacterMatch> get() = _characterMatches
}
