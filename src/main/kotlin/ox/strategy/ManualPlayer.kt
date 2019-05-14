package ox.strategy

import ox.core.Match
import ox.core.describeCharacterMatch
import ox.game.Game
import ox.game.Player

class ManualPlayer : Player {
    private var game: Game? = null
    private var wasGameStarted = false
    private var wasGameWon = false
    private val matches = mutableListOf<Match>()

    override fun join(theGame: Game) {
        game = theGame
        theGame.onNewGame += { wasGameStarted = true }
        theGame.onGameWon += { wasGameWon = true }
        theGame.onNewMatch += {
            matches.add(it)
            printMatches(matches)
        }
        theGame.player = this
    }

    override fun proposeSolution() {
        println("\nPlease enter your guess: ")
        val input = readLine() ?: ""
        game?.proposeSolution(input)
    }

    private fun printMatches(matches: List<Match>) {
        println("\nMatches:\n")
        matches.forEach {
            println(it.proposedWord())
            println(it.describeMatch(::describeCharacterMatch))
        }
    }
}
