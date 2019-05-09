package ox

import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.BorderPane
import javafx.stage.Stage

class OxApp : Application() {
    private lateinit var button: Button

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = launch(OxApp::class.java, *args)
    }

    private val oxText = "Click me please!"

    override fun start(primaryStage: Stage?) {
        button = Button()
        button.text = oxText
        button.onAction = EventHandler { buttonClick() }

        val pane = BorderPane()
        pane.center = button

        val scene = Scene(pane, 300.0, 250.0)

        primaryStage?.scene = scene
        primaryStage?.title = "The Click Me App"
        primaryStage?.show()
    }

    private fun buttonClick() {
        if (button.text == oxText) {
            button.text = "You clicked me!"
        }
        else {
            button.text = oxText
        }
    }
}
