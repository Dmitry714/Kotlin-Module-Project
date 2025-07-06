package controller

import model.Model
import model.Archive
import model.Menu
import model.Note
import view.View
import java.util.Scanner

class Controller(private val model: Model, private val view: View) {
    private val scanner = Scanner(System.`in`)
    private val userInput = UserInput(view, scanner)

    fun run() {
        archivesMenu()
        view.showMessage("Завершение работы... (архивов больше нет T_T)")
    }

    private fun archivesMenu() {

        while (true) {

            val menu = MenuBuilder<Archive>()
                .title("Список архивов")
                .items(model.getArchives())
                .itemLabel("Создать архив")
                .exitLabel("Выход из программы")
                .itemLabelExtractor { item -> item.name }
                .onCreate { createArchive() }
                .onItemSelected { item -> notesMenu(item) }.build()

            if (!showMenu(menu)) break
        }
    }

    private fun notesMenu(archive: Archive) {

        while (true) {

            val menu = MenuBuilder<Note>()
                .title("Архив '${archive.name}' | Список заметок")
                .items(archive.notes)
                .itemLabel("Создать заметку")
                .exitLabel("Назад")
                .itemLabelExtractor { note -> note.title }
                .onCreate { createNote(archive) }
                .onItemSelected { note ->
                    view.displayNote(note)
                    view.showMessage("Нажмите любую клавишу для продолжения...")
                    scanner.nextLine()
                }.build()

            if (!showMenu(menu)) break
        }
    }

    private fun createArchive() {
        val name = userInput.checkEmptyInput("Введите название нового архива -> ")
        model.addArchive(name)
        view.showMessage("Архив \"$name\" успешно создан!")
    }

    private fun createNote(archive: Archive) {
        val title = userInput.checkEmptyInput("Введите название новой заметки -> ")
        val content = userInput.checkEmptyInput("Введите текст заметки -> ")
        model.addNoteToArchive(archive, Note(title, content))
        view.showMessage("Заметка \"$title\" успешно добавлена!")
    }

    private fun <T> showMenu(menu: Menu<T>): Boolean {

        val menuItems = mutableListOf(menu.itemLabel)

        menu.items.forEach { item -> menuItems.add(menu.itemLabelExtractor(item)) }

        while (true) {
            view.displayMenu(menu.title, menuItems, menu.exitLabel)
            when (val input = userInput.checkIntInput()) {
                0 -> return false
                1 -> menu.onCreate()
                in 2..(menu.items.size + 1) -> {
                    val selectedItem = menu.items[input - 2]
                    menu.onItemSelected(selectedItem)
                }

                else -> view.showMessage("Ошибка! Такого пункта не существует!")
            }
            return true
        }
    }
}
