package ox

class Game(val dictionary: Dictionary, val minLength: Int = 3, val maxLength: Int = 8) {
    var wordToGuess: String = ""

    init {
        generateRandomWord()
    }

    private fun generateRandomWord() {
        wordToGuess = dictionary.getRandomWord(minLength, maxLength)
    }

    val words: Collection<String>
        get() {
            return dictionary.words
        }

    val hint: String
        get() {
            return "*".repeat(wordToGuess.length)
        }
}
