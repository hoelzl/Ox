package ox

class Game(val dictionary: Dictionary, val minLength: Int = 3, val maxLength: Int = 8) {
    val onNewMatch: MutableCollection<(match: Match) -> Unit> = mutableListOf()
    val onGameWon: MutableCollection<(match: Match) -> Unit> = mutableListOf()

    var wordToGuess: String = ""

    init {
        generateRandomWord()
    }

    private fun generateRandomWord() {
        wordToGuess = dictionary.getRandomWord(minLength, maxLength)
    }

    fun computeMatchFor(proposedSolution: String): Match {
        return Match(wordToGuess, proposedSolution)
    }

    fun proposeSolution(proposedSolution: String) {
        val match = computeMatchFor(proposedSolution)
        onNewMatch.forEach { it(match) }
        if (match.isPerfectMatch()) {
            onGameWon.forEach { it(match) }
        }
    }

    val words: Collection<String>
        get() {
            return dictionary.words
        }

    val hint: String
        get() {
            return "*".repeat(wordToGuess.length)
        }
}
