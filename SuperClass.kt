open class A {
    open fun hello() = println("Hello from A")
}
class B : A() {
    override fun hello() {
        super.hello()
        println("Hello from B")
    }
}