package ox

fun main(args: Array<String>) {
    val dictionary = Dictionary(setOf("song", "bar", "word", "game", "generally"))

    var wasGameWon = false
    val matches = mutableListOf<Match>()
    var input: String

    val game = Game(dictionary)
    game.onGameWon += { wasGameWon = true }
    game.onNewMatch += { matches.add(it) }

    println("Guess the word!")
    println("The word to guess has ${game.wordToGuess.length} characters.\n")

    do {
        println("\nPrevious matches:\n")
        matches.forEach {
            println(it.proposedWord())
            println(it.matchDescription())
            println()
        }
        println("Please enter your guess: ")

        input = readLine() ?: ""
        game.proposeSolution(input)

    } while (!wasGameWon && !input.isBlank())

    if (wasGameWon) {
        println("You won!")
    } else {
        println("Better luck next time.")
    }
}
