package ox

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DictionaryTest {
    @Test
    fun getRandomWord_GivenLength4() {
        val dictionary = Dictionary(listOf("bar", "word", "games"))
        assertThat(dictionary.getRandomWord(4), equalTo("word"))
    }

    @Test
    fun getRandomWord_GivenLengthBetween3And5() {
        val dictionary = Dictionary(listOf("a", "be", "bar", "word", "games", "kotlin"))

        val randomWord = dictionary.getRandomWord(3, 5)

        assertThat(randomWord, `in`(dictionary.words))
        assertThat(randomWord.length, greaterThanOrEqualTo(3))
        assertThat(randomWord.length, lessThanOrEqualTo(5))
    }

    @Test
    fun getRandomWord_WhenDictionaryIsEmpty() {
        val dictionary = Dictionary()
        assertThrows<NoSuchElementException> { dictionary.getRandomWord(2) }
    }

    @Test
    fun getRandomWord_GivenLength4_WhenMultipleWordsAreAvailable() {
        val dictionary = Dictionary(listOf("song", "bar", "word", "game", "generally"))
        val randomWord = dictionary.getRandomWord(4)
        assertThat(randomWord, `in`(dictionary.words))
        assertThat(randomWord.length, equalTo(4))
    }

}