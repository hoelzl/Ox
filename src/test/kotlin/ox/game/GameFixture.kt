package ox.game

class DictionaryStub : Dictionary {
    override val words = setOf("solution")

    override fun getRandomWord(minLength: Int, maxLength: Int): String =
            if ("solution".length in minLength..maxLength)
                "solution"
            else
                throw Exception()
}


open class GameFixture {
    protected fun createSingleSolutionGame(): Game {
        val game = Game(DictionaryStub(), maxLength = 10)
        assert(game.wordToGuess == "solution")
        return game
    }
}