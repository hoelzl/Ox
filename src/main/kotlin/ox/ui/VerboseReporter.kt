package ox.ui

import ox.core.Match
import ox.core.describeCharacterMatch
import ox.game.Game

class VerboseReporter : Reporter {
    private val matches = mutableListOf<Match>()

    override fun join(theGame: Game) {
        theGame.onNewGame += {
            println("Guess the word!")
            println("The word to guess has ${theGame.wordToGuess.length} characters.\n")
        }
        theGame.onGameWon += {
            println()
            println("You won!")
        }
        theGame.onGameLost += {
            println()
            println("Better luck next time.")
        }
        theGame.onNewMatch += {
            matches.add(it)
            printMatches(matches)
        }
    }

    private fun printMatches(matches: List<Match>) {
        println("\nMatches:\n")
        matches.forEach {
            println(it.proposedWord())
            println(it.describeMatch(::describeCharacterMatch))
        }
    }

}