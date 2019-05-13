package ox.data

import ox.game.Dictionary

class ListBasedDictionary(override val words: Collection<String> = setOf()) : Dictionary {
    override fun getRandomWord(minLength: Int, maxLength: Int): String {
        return words.filter { it.length in minLength..maxLength }.random()
    }
}
