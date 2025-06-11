interface Shape {
    fun area(): Double
    val cornersCount: Int
    fun sayHello(): Unit = println("Hello World")
}

class Circle(val radius: Double) : Shape {
    override fun area(): Double {
        return radius * radius * Math.PI
    }
    override val cornersCount = Int.MAX_VALUE
}

class Square(val side: Double) : Shape {
    override fun area(): Double {
        return side * side
    }
    override val cornersCount = 4
}