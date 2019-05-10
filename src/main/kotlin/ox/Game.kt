package ox

class Game(val dictionary: Dictionary, val minLength: Int = 3, val maxLength: Int = 8) {
    lateinit var wordToGuess: String

    init {
        generateRandomWord()
    }

    private fun generateRandomWord() {
        wordToGuess = dictionary.getRandomWord(minLength, maxLength)
    }

    val words: List<String>
        get() {
            return dictionary.words
        }
}
