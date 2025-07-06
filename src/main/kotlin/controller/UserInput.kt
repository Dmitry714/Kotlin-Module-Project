package controller

import view.View
import java.util.Scanner

internal class UserInput(
    private val view: View,
    private val scanner: Scanner
) {

    fun checkIntInput(): Int {
        val input = scanner.nextLine().trim()
        return input.toIntOrNull() ?: -1
    }

    fun checkEmptyInput(message: String): String {
        while (true) {
            view.showMessage(message)
            val input = scanner.nextLine().trim()
            if (input.isNotBlank()) {
                return input
            } else {
                view.showMessage("Ошибка! Строка не может быть пустой!\n")
            }
        }
    }
}
