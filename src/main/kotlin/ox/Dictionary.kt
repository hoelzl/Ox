package ox

class Dictionary(val words: Collection<String> = setOf()) {
    fun getRandomWord(minLength: Int = 4, maxLength: Int = minLength): String {
        return words.filter { it.length in minLength..maxLength }.random()
    }
}
