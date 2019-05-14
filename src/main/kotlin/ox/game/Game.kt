package ox.game

import ox.core.Match

enum class GameState {
    NotStarted,
    InProgress,
    Won,
    Lost
}

class Game(private val dictionary: Dictionary, private val minLength: Int = 3,
           val maxLength: Int = 8, val maxGuesses: Int = 10) {

    private var _player: Player? = null
    var player
        get() = if (_player != null) _player!! else throw Exception()
        set(newPlayer) {
            if (_player != null) throw Exception()
            _player = newPlayer
        }

    private var _guesses = 0
    val guesses: Int get() = _guesses

    private var gameState = GameState.NotStarted
    val wasGameWon: Boolean get() = gameState == GameState.Won
    val wasGameLost: Boolean get() = gameState == GameState.Lost

    val isGameDecided: Boolean get() = wasGameWon || wasGameLost

    private var _wordToGuess: String = ""
    val wordToGuess: String get() = _wordToGuess

    val onNewGame: MutableCollection<(game: Game) -> Unit> = mutableListOf()
    val onNewMatch: MutableCollection<(match: Match) -> Unit> = mutableListOf()
    val onGameWon: MutableCollection<(match: Match) -> Unit> = mutableListOf()
    val onGameLost: MutableCollection<() -> Unit> = mutableListOf()

    init {
        startNewGame()
    }

    val words: Collection<String>
        get() {
            return dictionary.words
        }

    fun startNewGame() {
        _guesses = 0
        gameState = GameState.InProgress
        generateRandomWord()
        onNewGame.forEach { it(this) }
    }

    fun proposeSolution(proposedSolution: String) {
        _guesses++
        val match = computeMatchFor(proposedSolution)
        onNewMatch.forEach { it(match) }
        updateGameState(match)
    }

    private fun updateGameState(match: Match) {
        if (match.isPerfectMatch()) {
            gameState = GameState.Won
            onGameWon.forEach { it(match) }
        } else if (_guesses >= maxGuesses) {
            gameState = GameState.Lost
            onGameLost.forEach { it() }
        }
    }

    internal fun computeMatchFor(proposedSolution: String): Match {
        return Match(wordToGuess, proposedSolution)
    }

    private fun generateRandomWord() {
        _wordToGuess = dictionary.getRandomWord(minLength, maxLength)
    }
}
