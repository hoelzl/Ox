package ox.strategy

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import ox.game.Player

class ManualPlayerTest : PlayerTest() {
    @Test
    fun join() {
        val player: Player = ManualPlayer()
        testJoin(player)
        MatcherAssert.assertThat(game.onGameWon.size, Matchers.equalTo(1))
        MatcherAssert.assertThat(game.onNewMatch.size, Matchers.equalTo(0))
    }
}
