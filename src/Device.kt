// Базовый класс "Устройство"
open class Device(val name: String, val brand: String) {
    
    // Общий метод для включения устройства
    fun turnOn() {
        println("$name от $brand включен.")
    }

    // Общий метод для выключения устройства
    fun turnOff() {
        println("$name от $brand выключен.")
    }
}

// Класс "Компьютер", наследующий от "Устройства"
class Computer(brand: String, val ram: Int) : Device("Компьютер", brand) {

    // Специфический метод для запуска программы
    fun runProgram(programName: String) {
        println("Программа $programName запущена на компьютере с $ram ГБ оперативной памяти.")
    }
}

// Класс "Смартфон", наследующий от "Устройства"
class Smartphone(brand: String, val cameraResolution: Int) : Device("Смартфон", brand) {

    // Специфический метод для фотографии
    fun takePhoto() {
        println("Фото сделано с разрешением камеры $cameraResolution МП.")
    }
}

fun main() {
    // Создаем объект класса "Компьютер" и вызываем его методы
    val computer = Computer(brand = "Dell", ram = 16)
    computer.turnOn()
    computer.runProgram("IntelliJ IDEA")
    computer.turnOff()

    // Создаем объект класса "Смартфон" и вызываем его методы
    val smartphone = Smartphone(brand = "Samsung", cameraResolution = 108)
    smartphone.turnOn()
    smartphone.takePhoto()
    smartphone.turnOff()
}
