package controller

import model.Menu

internal class MenuBuilder<T> {

    private var title: String? = null
    private var items: List<T>? = null
    private var itemLabel: String? = null
    private var exitLabel: String? = null
    private var itemLabelExtractor: ((T) -> String)? = null
    private var onCreate: (() -> Unit)? = null
    private var onItemSelected: ((T) -> Unit)? = null

    fun title(value: String) = apply { title = value }
    fun items(value: List<T>) = apply { items = value }
    fun itemLabel(value: String) = apply { itemLabel = value }
    fun exitLabel(value: String) = apply { exitLabel = value }
    fun itemLabelExtractor(value: (T) -> String) = apply { itemLabelExtractor = value }
    fun onCreate(value: () -> Unit) = apply { onCreate = value }
    fun onItemSelected(value: (T) -> Unit) = apply { onItemSelected = value }

    fun build(): Menu<T> {

        val currentTitle = title ?: error("\"Title\" не задан")
        val currentItems = items ?: error("\"Items\" не заданы")
        val currentItemLabel = itemLabel ?: error("\"ItemLabel\"не задан")
        val currentExitLabel = exitLabel ?: error("\"ExitLabel\"не задан")
        val currentItemLabelExtractor = itemLabelExtractor ?: error("\"ItemLabelExtractor\"не задан")
        val currentOnCreate = onCreate ?: error("\"OnCreate\"не задан")
        val currentOnItemSelected = onItemSelected ?: error("\"OnItemSelected\"не задан")

        return Menu(
            currentTitle,
            currentItems,
            currentItemLabel,
            currentExitLabel,
            currentItemLabelExtractor,
            currentOnCreate,
            currentOnItemSelected
        )
    }
}
