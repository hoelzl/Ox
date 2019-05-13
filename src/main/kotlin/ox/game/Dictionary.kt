package ox.game

interface Dictionary {
    val words: Collection<String>
    fun getRandomWord(minLength: Int = 4, maxLength: Int = minLength): String
}