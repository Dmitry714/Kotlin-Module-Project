import model.Model
import view.View
import controller.Controller

fun main(args: Array<String>) {
    val model = Model()
    val view = View()
    val controller = Controller(model, view)

    controller.run()
}
