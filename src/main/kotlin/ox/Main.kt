package ox

import ox.data.ListBasedDictionary
import ox.game.Game
import ox.strategy.ManualPlayer
import ox.strategy.RandomPlayer
import ox.ui.TerseReporter
import ox.ui.VerboseReporter

fun main(args: Array<String>) {
    val isAiActive = args.contains("--auto")
    val numGuesses = if (isAiActive) 250000 else 5

    val dictionary = ListBasedDictionary(setOf("song", "bar", "word", "game", "generally"))
    val game = Game(dictionary, maxGuesses = numGuesses)
    val player = if (isAiActive) RandomPlayer() else ManualPlayer()
    val reporter = if (isAiActive) TerseReporter() else VerboseReporter()

    player.join(game)
    reporter.join(game)

    game.startNewGame()

    do {
        player.proposeSolution()
    } while (!game.isGameDecided)
}
