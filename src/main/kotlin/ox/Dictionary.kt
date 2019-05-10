package ox

class Dictionary(val words: List<String> = listOf()) {
    fun getRandomWord(minLength: Int = 4, maxLength: Int = 8): String {
        return words.filter { it.length in minLength..maxLength }.random()
    }
}
