package ox

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class CharacterMatchTest {
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