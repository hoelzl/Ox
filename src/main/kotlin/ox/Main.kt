package ox

import ox.core.Match
import ox.core.describeCharacterMatch
import ox.data.ListBasedDictionary
import ox.game.Game

fun main(args: Array<String>) {
    val dictionary = ListBasedDictionary(setOf("song", "bar", "word", "game", "generally"))

    var wasGameWon = false
    val matches = mutableListOf<Match>()
    var exitMainLoop: Boolean

    val mainLoop = { game: Game -> readAndProcessInput(game) }

    val game = Game(dictionary)
    game.onGameWon += { wasGameWon = true }
    game.onNewMatch += {
        matches.add(it)
        printPreviousMatches(matches)
    }

    println("Guess the word!")
    println("The word to guess has ${game.wordToGuess.length} characters.\n")

    do {
        exitMainLoop = mainLoop(game)
    } while (!wasGameWon && !exitMainLoop)

    println()
    if (wasGameWon) {
        println("You won!")
    } else {
        println("Better luck next time.")
    }
}

private fun printPreviousMatches(matches: MutableList<Match>) {
    println("\nMatches:\n")
    matches.forEach {
        println(it.proposedWord())
        println(it.describeMatch(::describeCharacterMatch))
    }
}

private fun readAndProcessInput(game: Game): Boolean {
    println("\nPlease enter your guess: ")
    val input = readLine() ?: ""
    game.proposeSolution(input)
    return input.isBlank()
}
