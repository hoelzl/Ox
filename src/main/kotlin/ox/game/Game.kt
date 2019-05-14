package ox.game

import ox.core.Match

class Game(private val dictionary: Dictionary, private val minLength: Int = 3,
           val maxLength: Int = 8, val maxGuesses: Int = 100) {

    private var _player: Player? = null
    var player
        get() = if (_player != null) _player!! else throw Exception()
        set(newPlayer) {
            if (_player != null) throw Exception()
            _player = newPlayer
        }

    private var _guesses = 0
    val guesses: Int get() = _guesses
    private var _wasGameWon = false
    val wasGameWon: Boolean get() = _wasGameWon
    private var _wasGameLost = false
    val wasGameLost: Boolean get() = _wasGameLost

    val isGameDecided: Boolean get() = wasGameWon || wasGameLost

    private var _wordToGuess: String = ""
    val wordToGuess: String get() = _wordToGuess

    val onNewGame: MutableCollection<(game: Game) -> Unit> = mutableListOf()
    val onNewMatch: MutableCollection<(match: Match) -> Unit> = mutableListOf()
    val onGameWon: MutableCollection<(match: Match) -> Unit> = mutableListOf()
    val onGameLost: MutableCollection<(match: Match) -> Unit> = mutableListOf()

    init {
        startNewGame()
    }

    val words: Collection<String>
        get() {
            return dictionary.words
        }

    fun startNewGame() {
        _guesses = 0
        _wasGameWon = false
        _wasGameLost = false
        generateRandomWord()
        onNewGame.forEach { it(this) }
    }

    fun proposeSolution(proposedSolution: String) {
        _guesses++
        if (_guesses > maxGuesses) {
            _wasGameLost = true
            onGameLost.forEach {}
            return
        }

        val match = computeMatchFor(proposedSolution)
        onNewMatch.forEach { it(match) }
        if (match.isPerfectMatch()) {
            _wasGameWon = true
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
