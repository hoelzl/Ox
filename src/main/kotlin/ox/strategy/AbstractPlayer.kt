package ox.strategy

import ox.game.Game
import ox.game.Player

abstract class AbstractPlayer : Player {
    protected var game: Game? = null

    override fun join(theGame: Game) {
        game = theGame
        theGame.player = this
    }
}
