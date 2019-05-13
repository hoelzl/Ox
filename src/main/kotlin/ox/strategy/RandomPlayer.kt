package ox.strategy

import ox.game.Game
import ox.game.Player

class RandomPlayer : Player {
    var wasGameStarted = false
    var wasGameWon = false
    var numMatches = 0
    var game: Game? = null

    override fun join(theGame: Game) {
        game = theGame
        theGame.onNewGame += { wasGameStarted = true }
        theGame.onGameWon += { wasGameWon = true }
        theGame.onNewMatch += { numMatches++ }
        theGame.player = this
    }

    override fun proposeSolution() {
        val theGame = game ?: return
        val proposedSolution = generateRandomWordOfLength(theGame.wordToGuess.length)
        theGame.proposeSolution(proposedSolution)
    }

    private fun generateRandomWordOfLength(length: Int): String {
        return (1..length).map { generateRandomChar() }.joinToString(separator = "")
    }

    private val chars = 'a'..'z'

    private fun generateRandomChar(): Char {
        return chars.random()
    }
}
