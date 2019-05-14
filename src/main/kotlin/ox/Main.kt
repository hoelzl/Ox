package ox

import ox.core.Match
import ox.core.describeCharacterMatch
import ox.data.ListBasedDictionary
import ox.game.Game
import ox.strategy.ManualPlayer
import ox.ui.VerboseReporter

fun main(args: Array<String>) {
    val dictionary = ListBasedDictionary(setOf("song", "bar", "word", "game", "generally"))
    val game = Game(dictionary)
    val player = ManualPlayer()
    val reporter = VerboseReporter()

    player.join(game)
    reporter.join(game)

    game.startNewGame()

    do {
        player.proposeSolution()
    } while (!game.isGameDecided)
}
