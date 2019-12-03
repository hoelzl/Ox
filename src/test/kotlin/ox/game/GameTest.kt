package ox.game

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import ox.core.Match
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class PlayerStub : Player {
    override fun join(theGame: Game) {
    }

    override fun proposeSolutionToGame() {
    }
}

class GameTest : GameFixture() {
    @Test
    fun constructor_GivenDefaultArgs_HasCorrectValuesForAttributes() {
        val game = Game(DictionaryStub())

        assertThat(game.wordToGuess, equalTo("solution"))
    }

    @Test
    fun setPlayer_SetsPlayer_WhenCalledWithValidPlayer() {
        val player: Player = PlayerStub()
        val game = Game(DictionaryStub())

        game.player = player

        assertThat(game.player, equalTo(player))
    }

    @Test
    fun setPlayer_Throws_WhenCalledTwice() {
        val player: Player = PlayerStub()
        val game = Game(DictionaryStub())
        game.player = player

        assertThrows<Exception> { game.player = player }
    }

    @Test
    fun computeMatchFor_GivenWordThatDoesNotMatch() {
        val match = createSingleSolutionGame().computeMatchFor("backward")

        assertFalse(match.isPerfectMatch())
        assertFalse(match.isPartialMatch())
    }

    @Test
    fun computeMatchFor_GivenWordThatMatchesPartially() {
        val match = createSingleSolutionGame().computeMatchFor("crossbow")

        assertFalse(match.isPerfectMatch())
        assertTrue(match.isPartialMatch())
    }

    @Test
    fun computeMatchFor_GivenWordThatMatchesPerfectly() {
        val match = createSingleSolutionGame().computeMatchFor("solution")

        assertTrue(match.isPerfectMatch())
        assertTrue(match.isPartialMatch())
    }

    @Test
    fun proposeSolution_GivenWordThatMatchesPartially() {
        var wasGameWon = false
        var wasNewMatchComputed = false
        var newMatch: Match? = null
        val game = createSingleSolutionGame()
        game.onGameWon += {
            wasGameWon = true
        }
        game.onNewMatch += {
            wasNewMatchComputed = true
            newMatch = it
        }

        game.proposeSolution("crossbow")

        assertFalse(wasGameWon)
        assertTrue(wasNewMatchComputed)
        assertTrue(newMatch != null)
        assertEquals(newMatch?.isPartialMatch(), true)
        assertNotEquals(newMatch?.isPerfectMatch(), true)
    }


    @Test
    fun proposeSolution_GivenWordThatMatchesPerfectly() {
        var wasGameWon = false
        var wasNewMatchComputed = false
        var newMatch: Match? = null
        val game = createSingleSolutionGame()
        game.onGameWon += {
            wasGameWon = true
        }
        game.onNewMatch += {
            wasNewMatchComputed = true
            newMatch = it
        }

        game.proposeSolution("solution")

        assertTrue(wasGameWon)
        assertTrue(wasNewMatchComputed)
        assertTrue(newMatch != null)
        assertEquals(newMatch?.isPartialMatch(), true)
        assertEquals(newMatch?.isPerfectMatch(), true)
    }

//    private fun tryToGenerateAllWordsToGuess(dictionary: ListBasedDictionary): HashSet<String> {
//        val wordsNotYetGenerated = HashSet<String>(dictionary.words)
//        // How many wordToGuess candidates do we generate before we give up?
//        // Formally this is known as the coupon collector problem, but since the solution is not
//        // trivial to compute we just stick with a number that is way higher than the iterations
//        // we expect to need.
//        var remainingIterations = 1000
//
//        val wordLengths = dictionary.words.map { it.length }
//        val minLength = wordLengths.min() ?: -1
//        val maxLength = wordLengths.max() ?: -1
//
//        do {
//            wordsNotYetGenerated -= Game(dictionary, minLength, maxLength).wordToGuess
//            remainingIterations--
//        } while (wordsNotYetGenerated.isNotEmpty() && remainingIterations > 0)
//
//        return wordsNotYetGenerated
//    }
}
