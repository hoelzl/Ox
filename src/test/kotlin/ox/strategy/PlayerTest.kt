package ox.strategy

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Assertions.assertThrows
import ox.game.DictionaryStub
import ox.game.Game
import ox.game.GameFixture
import ox.game.Player
import kotlin.test.assertTrue

open class PlayerTest : GameFixture() {
    protected val game = Game(DictionaryStub())
    protected fun testJoin(player: Player) {
        assertThrows(Exception::class.java) { game.player }
        assertTrue(game.onGameWon.isEmpty())
        assertTrue(game.onNewMatch.isEmpty())

        player.join(game)

        assertThat(game.player, equalTo(player))
    }
}