package ox.ui

import ox.game.Game

class VerboseReporter : Reporter {
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
    }
}