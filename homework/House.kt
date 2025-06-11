class House(val address: String, val floors: Int) {
    inner class Room(val name: String, val area: Double) {
        fun describe() {
            println("Комната $name площадью $area м² в доме по адресу $address с $floors этажами")
        }
    }
}
