package ox.core

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

internal class CreateCharacterMatchTest {

    @Test
    fun createCharacterMatch_whenPerfectMatchAtStartOfWord() {
        val unit = createCharacterMatch('a', 0, "abc")
        assertThat(unit.proposedChar, equalTo('a'))
        assertThat(unit.isPerfectMatch(), equalTo(true))
        assertThat(unit.isPartialMatch(), equalTo(true))
    }

    fun createCharacterMatch_whenPerfectMatchInsideWord() {
        val unit = createCharacterMatch('b', 1, "abcd")
        assertThat(unit.proposedChar, equalTo('b'))
        assertThat(unit.isPerfectMatch(), equalTo(true))
        assertThat(unit.isPartialMatch(), equalTo(true))
    }

    fun createCharacterMatch_whenPerfectMatchAtEndOfWord() {
        val unit = createCharacterMatch('d', 3, "abcd")
        assertThat(unit.proposedChar, equalTo('d'))
        assertThat(unit.isPerfectMatch(), equalTo(true))
        assertThat(unit.isPartialMatch(), equalTo(true))
    }

    fun createCharacterMatch_whenPartialMatch() {
        val unit = createCharacterMatch('c', 1, "abcd")
        assertThat(unit.proposedChar, equalTo('c'))
        assertThat(unit.isPerfectMatch(), equalTo(false))
        assertThat(unit.isPartialMatch(), equalTo(true))
    }

    fun createCharacterMatch_whenNoMatch() {
        val unit = createCharacterMatch('e', 1, "abcd")
        assertThat(unit.proposedChar, equalTo('e'))
        assertThat(unit.isPerfectMatch(), equalTo(false))
        assertThat(unit.isPartialMatch(), equalTo(false))
    }
}