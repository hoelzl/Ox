package ox

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CharacterMatchTest {
    @Test
    fun isPerfectMatch_WhenPerfectMatch() {
        assertTrue(PerfectCharacterMatch('a').isPerfectMatch())
    }

    @Test
    fun isPerfectMatch_WhenPartialMatch() {
        assertFalse(PartialCharacterMatch('a').isPerfectMatch())
    }

    @Test
    fun isPerfectMatch_WhenFailedMatch() {
        assertFalse(FailedCharacterMatch('a').isPerfectMatch())
    }

    @Test
    fun isPartialMatch_WhenPerfectMatch() {
        assertTrue(PerfectCharacterMatch('a').isPartialMatch())
    }

    @Test
    fun isPartialMatch_WhenPartialMatch() {
        assertTrue(PartialCharacterMatch('a').isPartialMatch())
    }

    @Test
    fun isPartialMatch_WhenFailedMatch() {
        assertFalse(FailedCharacterMatch('a').isPartialMatch())
    }

    @Test
    fun displayChar_WhenPerfectMatch() {
        assertThat(PerfectCharacterMatch('a').displayChar, equalTo('a'))
    }

    @Test
    fun displayChar_WhenPartialMatch() {
        assertThat(PartialCharacterMatch('a').displayChar, equalTo('+'))
    }

    @Test
    fun displayChar_WhenFailedMatch() {
        assertThat(FailedCharacterMatch('a').displayChar, equalTo('-'))
    }
}