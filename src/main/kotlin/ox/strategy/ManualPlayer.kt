package ox.strategy

class ManualPlayer : AbstractPlayer() {
    override fun proposeSolutionToGame() {
        println("\nPlease enter your guess: ")
        val input = readLine() ?: ""
        game?.proposeSolution(input)
    }
}
