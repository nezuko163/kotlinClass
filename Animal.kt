open class Animal {
    open fun speak() {
        println("Я говорю")
    }
}
class Cat : Animal() {
    override fun speak() {
        println("Мяу!")
    }
}