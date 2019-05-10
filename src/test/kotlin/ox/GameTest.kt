package ox

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class GameTest {
    @Test
    fun newGameWithDefaultLength() {
        val dictionary = Dictionary(listOf("foo", "quux", "foobar"))
        val game = Game(dictionary)

        assertTrue(game.wordToGuess in game.words)
        assertTrue(game.wordToGuess.length in game.minLength..game.maxLength)
    }
}