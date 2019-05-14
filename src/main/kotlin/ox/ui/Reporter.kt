package ox.ui

import ox.game.Game

interface Reporter {
    fun join(theGame: Game)
}