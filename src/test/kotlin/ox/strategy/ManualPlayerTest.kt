package ox.strategy

import org.junit.jupiter.api.Test
import ox.game.Player

class ManualPlayerTest : PlayerTest() {
    @Test
    fun join() {
        val player: Player = ManualPlayer()
        testJoin(player)
    }
}