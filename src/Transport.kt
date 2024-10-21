abstract class Transport {
    abstract fun maxSpeed(): Int
}

class Plane(private val engineCount: Int) : Transport() {
    override fun maxSpeed(): Int {
        return 1500
    }

    fun fly() {
        println("Самолет летит с $engineCount двигателями на скорости ${maxSpeed()} км/ч.")
    }
}

class Ship(private val displacement: Int) : Transport() {
    override fun maxSpeed(): Int {
        return 55
    }
    fun sail() {
        println("Корабль плывет с водоизмещением $displacement тонн на скорости ${maxSpeed()} км/ч.")
    }
}

fun main() {
    val plane = Plane(engineCount = 4)
    plane.fly()

    val ship = Ship(displacement = 15000)
    ship.sail()
}
