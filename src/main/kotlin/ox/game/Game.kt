package ox.game

import ox.core.Match

class Game(private val dictionary: Dictionary, private val minLength: Int = 3, val maxLength: Int = 8) {
    private var _player: Player? = null
    var player
        get() = if (_player != null) _player!! else throw Exception()
        set(newPlayer) {
            if (_player != null) throw Exception()
            _player = newPlayer
        }

    val onNewGame: MutableCollection<(game: Game) -> Unit> = mutableListOf()
    val onNewMatch: MutableCollection<(match: Match) -> Unit> = mutableListOf()
    val onGameWon: MutableCollection<(match: Match) -> Unit> = mutableListOf()

    private var _wordToGuess: String = ""
    val wordToGuess: String get() = _wordToGuess

    val words: Collection<String>
        get() {
            return dictionary.words
        }

    init {
        startNewGame()
    }

    fun startNewGame() {
        generateRandomWord()
        onNewGame.forEach { it(this) }
    }

    fun proposeSolution(proposedSolution: String) {
        val match = computeMatchFor(proposedSolution)
        onNewMatch.forEach { it(match) }
        if (match.isPerfectMatch()) {
            onGameWon.forEach { it(match) }
        }
    }

    internal fun computeMatchFor(proposedSolution: String): Match {
        return Match(wordToGuess, proposedSolution)
    }

    private fun generateRandomWord() {
        _wordToGuess = dictionary.getRandomWord(minLength, maxLength)
    }
}
