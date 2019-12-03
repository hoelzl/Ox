package ox.strategy

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import ox.core.Match
import ox.game.DictionaryStub
import ox.game.Game
import ox.game.Player
import kotlin.test.assertFalse

class RandomPlayerTest : PlayerTest() {

    @Test
    fun join() {
        val player: Player = RandomPlayer()
        testJoin(player)
    }

    @Test
    fun proposeSolution() {
        val player: Player = RandomPlayer()
        val game = Game(DictionaryStub())
        player.join(game)

        val matches = mutableListOf<Match>()
        game.onNewMatch += { matches.add(it) }

        player.proposeSolutionToGame()

        assertThat(matches.size, equalTo(1))
        assertThat(matches[0].proposedWord().length, equalTo(game.wordToGuess.length))
        // Matcher generates '.' when characters are missing
        assertFalse(matches[0].proposedWord().contains('.'))
    }

}