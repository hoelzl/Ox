package ox

class Dictionary(val words: List<String> = listOf()) {
    fun getRandomWord(size: Int): String {
        return words.filter{ it.length == size }.random()
    }
}
