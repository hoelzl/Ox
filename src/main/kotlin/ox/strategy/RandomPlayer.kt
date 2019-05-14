package ox.strategy

import ox.game.Game

class RandomPlayer : AbstractPlayer() {
    var numMatches = 0

    override fun join(theGame: Game) {
        super.join(theGame)
        theGame.onNewGame += { numMatches = 0 }
        theGame.onNewMatch += { numMatches++ }
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
