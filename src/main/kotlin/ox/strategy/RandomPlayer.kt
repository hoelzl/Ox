package ox.strategy

class RandomPlayer : AbstractPlayer() {
    override fun proposeSolutionToGame() {
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
