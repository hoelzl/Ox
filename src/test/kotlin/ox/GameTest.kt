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
        val wordsNotYetGenerated = tryToGenerateAllWordsToGuess(defaultDictionary)

        assertTrue(wordsNotYetGenerated.isEmpty(),
                   "Not all words were generated. Missing: $wordsNotYetGenerated")
    }

    private fun tryToGenerateAllWordsToGuess(dictionary: Dictionary): HashSet<String> {
        val wordsNotYetGenerated = HashSet<String>(dictionary.words)
        // How many wordToGuess candidates do we generate before we give up?
        // Formally this is known as the coupon collector problem, but since the solution is not
        // trivial to compute we just stick with a number that is way higher than the iterations
        // we expect to need.
        var remainingIterations = 1000

        val wordLengths = dictionary.words.map { it.length }
        val minLength = wordLengths.min() ?: -1
        val maxLength = wordLengths.max() ?: -1

        do {
            wordsNotYetGenerated -= Game(dictionary, minLength, maxLength).wordToGuess
            remainingIterations--
        } while (wordsNotYetGenerated.isNotEmpty() && remainingIterations > 0)

        return wordsNotYetGenerated
    }
}
