class Book(
    val title: String,
    val author: String,
    private val _year: Int
) {
    // Кастомный геттер: если год меньше 1900 — возвращаем 1900
    val year: Int
        get() = if (_year >= 1900) _year else 1900

    fun info() {
        println("Название: $title")
        println("Автор: $author")
        println("Год издания: $year")
    }
}