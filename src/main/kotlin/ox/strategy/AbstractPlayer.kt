package ox.strategy

import ox.game.Game
import ox.game.Player

abstract class AbstractPlayer : Player {
    protected var game: Game? = null
    private var wasGameStarted = false
    private var wasGameWon = false
    private var wasGameLost = false

    override fun join(theGame: Game) {
        game = theGame
        theGame.onNewGame += {
            wasGameLost = false
            wasGameWon = false
            wasGameStarted = true
        }
        theGame.onGameWon += { wasGameWon = true }
        theGame.onGameLost += { wasGameLost = true }
        theGame.player = this
    }
}
