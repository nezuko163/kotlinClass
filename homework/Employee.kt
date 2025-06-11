abstract class Employee(val name: String) {
    abstract fun work()
}

// Класс Programmer наследуется от Employee и реализует метод work()
class Programmer1(name: String) : Employee(name) {
    override fun work() {
        println("$name пишет код")
    }
}

// Класс Designer наследуется от Employee и реализует метод work()
class Designer(name: String) : Employee(name) {
    override fun work() {
        println("$name создаёт дизайн")
    }
}