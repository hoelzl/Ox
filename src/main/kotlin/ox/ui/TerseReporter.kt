package ox.ui

import ox.game.Game

class TerseReporter : Reporter {
    var guesses = 0

    override fun join(theGame: Game) {
        theGame.onNewGame += {
            println("The word to guess has ${theGame.wordToGuess.length} characters.\n")
        }
        theGame.onNewMatch += {
            guesses++
        }
        theGame.onGameWon += {
            println()
            println("Player guessed '${it.proposedWord()}' after $guesses tries and won!")
        }
        theGame.onGameLost += {
            println()
            println("Player lost after $guesses tries.")
        }
    }
}