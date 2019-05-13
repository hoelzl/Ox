package ox.core

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MatchTest {
    @Test
    fun constructor_GivenWordsThatDoNotMatch() {
        val match = Match("solution", "backward")

        assertFalse(match.isPerfectMatch())
        assertFalse(match.isPartialMatch())
    }

    @Test
    fun constructor_GivenWordsThatDoNotMatch_WhenCaseIsDifferent() {
        val match = Match("solution", "BACKWARD")

        assertFalse(match.isPerfectMatch())
        assertFalse(match.isPartialMatch())
    }

    @Test
    fun constructor_GivenWordsThatMatchPartially() {
        val match = Match("solution", "crossbow")

        assertFalse(match.isPerfectMatch())
        assertTrue(match.isPartialMatch())
    }

    @Test
    fun constructor_GivenWordsThatMatchPartially_WhenOnlyFirstCharacterMatches() {
        val match = Match("solution", "scabbard")

        assertFalse(match.isPerfectMatch())
        assertTrue(match.isPartialMatch())
    }

    @Test
    fun constructor_GivenWordsThatMatchPartially_WhenOnlyLastCharacterMatches() {
        val match = Match("solution", "jerrycan")

        assertFalse(match.isPerfectMatch())
        assertTrue(match.isPartialMatch())
    }

    @Test
    fun constructor_GivenWordsThatMatchPartially_WhenCaseIsDifferent() {
        val match = Match("solution", "CROSSBOW")

        assertFalse(match.isPerfectMatch())
        assertTrue(match.isPartialMatch())
    }

    @Test
    fun constructor_GivenWordsThatMatchPartially_WhenGuessIsShorterThanSolution() {
        val match = Match("solution", "cross")

        assertFalse(match.isPerfectMatch())
        assertTrue(match.isPartialMatch())
    }

    @Test
    fun constructor_GivenWordsThatMatchPartially_WhenGuessIsLongerThanSolution() {
        val match = Match("solution", "crossroads")

        assertFalse(match.isPerfectMatch())
        assertTrue(match.isPartialMatch())
    }


    @Test
    fun constructor_GivenWordsThatMatchesPerfectly() {
        val match = Match("solution", "solution")

        assertTrue(match.isPerfectMatch())
        assertTrue(match.isPartialMatch())
    }

    @Test
    fun constructor_GivenWordsThatMatchesPerfectly_WhenCaseIsDifferent() {
        val match = Match("solution", "SOLUTION")

        assertTrue(match.isPerfectMatch())
        assertTrue(match.isPartialMatch())
    }

    @Test
    fun descriptions_GivenWordsThatDoNotMatch() {
        val match = Match("solution", "backward")

        assertThat(match.proposedWord(), equalTo("backward"))
        assertThat(match.describeMatch(::describeCharacterMatch), equalTo("........"))
    }

    @Test
    fun descriptions_GivenWordsThatMatchPartially() {
        val match = Match("solution", "crossbow")

        assertThat(match.proposedWord(), equalTo("crossbow"))
        assertThat(match.describeMatch(::describeCharacterMatch), equalTo("..---.+."))
    }

    @Test
    fun matches_GivenWordsThatMatchPartially() {
        val match = Match("solution", "crossbow")

        assertThat(match.characterMatches.map(::describeCharacterMatch).joinToString(separator = ""),
                equalTo("..---.+."))
    }
}