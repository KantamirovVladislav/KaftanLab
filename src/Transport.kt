// Абстрактный класс "Транспорт"
abstract class Transport {
    abstract fun maxSpeed(): Int  // Абстрактный метод для вычисления максимальной скорости
}

// Класс "Самолет", наследующий от "Транспорта"
class Plane(private val engineCount: Int) : Transport() {
    override fun maxSpeed(): Int {
        return 900 // Максимальная скорость самолета
    }

    // Специфический метод для самолета
    fun fly() {
        println("Самолет летит с $engineCount двигателями на скорости ${maxSpeed()} км/ч.")
    }
}

// Класс "Корабль", наследующий от "Транспорта"
class Ship(private val displacement: Int) : Transport() {
    override fun maxSpeed(): Int {
        return 55 // Максимальная скорость корабля
    }

    // Специфический метод для корабля
    fun sail() {
        println("Корабль плывет с водоизмещением $displacement тонн на скорости ${maxSpeed()} км/ч.")
    }
}

fun main() {
    // Создаем объект класса "Самолет" и вызываем его методы
    val plane = Plane(engineCount = 2)
    plane.fly()

    // Создаем объект класса "Корабль" и вызываем его методы
    val ship = Ship(displacement = 30000)
    ship.sail()
}
