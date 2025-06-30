package model

data class Menu<T>(
    val title: String,
    val items: List<T>,
    val itemLabel: String,
    val exitLabel: String,
    val itemLabelExtractor: (T) -> String,
    val onCreate: () -> Unit,
    val onItemSelected: (T) -> Unit
)
