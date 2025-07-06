package view

import model.Note

class View {
    fun showMessage(message: String) {
        println(message)
    }

    fun displayMenu(title: String, items: List<String>, exitLabel: String) {
        println("\n$title")
        items.forEachIndexed { index, item ->
            println("${index + 1}. $item")
        }
        println("0. $exitLabel")
        showMessage("Выберите пункт -> ")
    }

    fun displayNote(note: Note) {
        println("# Заметка: ${note.title}")
        println("#--------------------------#")
        println(note.text)
        println("#--------------------------#")
    }
}
