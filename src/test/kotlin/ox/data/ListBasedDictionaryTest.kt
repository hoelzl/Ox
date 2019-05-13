package ox.data

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertTrue

class ListBasedDictionaryTest {
    @Test
    fun getRandomWord_GivenLength4() {
        val dictionary = ListBasedDictionary(setOf("bar", "word", "games"))
        assertThat(dictionary.getRandomWord(4), equalTo("word"))
    }

    @Test
    fun getRandomWord_GivenLengthBetween3And5() {
        val dictionary = ListBasedDictionary(setOf("a", "be", "bar", "word", "games", "kotlin"))

        val randomWord = dictionary.getRandomWord(3, 5)

        assertTrue(randomWord in dictionary.words)
        assertTrue(randomWord.length in 3..5)
    }

    @Test
    fun getRandomWord_WhenDictionaryIsEmpty() {
        val dictionary = ListBasedDictionary()
        assertThrows<NoSuchElementException> { dictionary.getRandomWord(2) }
    }

    @Test
    fun getRandomWord_GivenLength4_WhenMultipleWordsOfLength4AreAvailable() {
        val dictionary = ListBasedDictionary(setOf("song", "bar", "word", "game", "generally"))
        val randomWord = dictionary.getRandomWord(4)
        assertTrue(randomWord in dictionary.words)
        assertThat(randomWord.length, equalTo(4))
    }
}