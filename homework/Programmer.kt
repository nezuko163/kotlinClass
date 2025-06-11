data class Programmer(
    val name: String,
    val age: Int,
    val languages: List<String>,
    val experienceYears: Int,
) {

    fun primaryLanguage(): String {
        return if (languages.isEmpty()) {
            "No languages"
        } else {
            languages.maxByOrNull { it.length } ?: "No languages"
        }
    }

    fun isSenior(): Boolean = experienceYears >= 5

    override fun toString(): String {
        val status = if (isSenior()) "Senior" else "Junior"
        return "Программист: $name, возраст: $age, языки: $languages, опыт: $experienceYears лет, статус: $status"
    }
}

fun seniorFilter(programmers: List<Programmer>): List<Programmer> {
    return programmers.filter { it.isSenior() }
}

fun averageAge(list: List<Programmer>): Double {
    return if (list.isEmpty()) 0.0 else list.map { it.age }.average()
}

fun main() {
    val programmer1 = Programmer("Алексей", 30, listOf("Kotlin", "Java", "Python"), 6)
    val programmer2 = Programmer("Марина", 25, listOf("C++", "Python"), 3)
    val programmer3 = Programmer("Игорь", 40, listOf("JavaScript", "TypeScript", "Go"), 10)

    println(programmer1)
    println(programmer2)
    println(programmer3)

    println("Primary language у Алексея: ${programmer1.primaryLanguage()}")
    println("Марина Senior? ${programmer2.isSenior()}")

    // Создаем копию с другим именем и опытом
    val programmer4 = programmer1.copy(name = "Дмитрий", experienceYears = 2)
    println(programmer4)

    // Доп. задача: список и фильтрация Senior
    val programmers = listOf(programmer1, programmer2, programmer3, programmer4)
    val seniors = seniorFilter(programmers)
    println("Senior программисты:")
    seniors.forEach { println(it.name) }


    println("Средний возраст программистов: ${"%.2f".format(averageAge(programmers))}")
}
