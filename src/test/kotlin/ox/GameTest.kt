package ox

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class GameTest {
    private val defaultDictionary = Dictionary(setOf("foo", "quux", "foobar"))

    @Test
    fun newGame_WhenCalledWithDefaultArgs_HasCorrectWordToGuessAndHintValues() {
        val game = Game(defaultDictionary)

        assertTrue(game.wordToGuess in game.words)
        assertTrue(game.wordToGuess.length in game.minLength..game.maxLength)
        assertThat(game.hint, equalTo("*".repeat(game.wordToGuess.length)))
    }

    @Test
    fun newGame_GeneratesAllPossibleWordsInDictionary() {
        val wordsNotYetGenerated = tryToGenerateAllWordsToGuess()

        assertTrue(wordsNotYetGenerated.isEmpty(),
                "Not all words were generated. Missing: $wordsNotYetGenerated")
    }

    private fun tryToGenerateAllWordsToGuess(): HashSet<String> {
        val wordsNotYetGenerated = HashSet<String>(defaultDictionary.words)
        var remainingIterations = 1000

        do {
            wordsNotYetGenerated -= Game(defaultDictionary, 3, 6).wordToGuess
            remainingIterations--
        } while (wordsNotYetGenerated.isNotEmpty() && remainingIterations > 0)

        return wordsNotYetGenerated
    }
}
