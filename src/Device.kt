open class Device(val name: String, val brand: String) {
    fun turnOn() {
        println("$name от $brand включен.")
    }
    fun turnOff() {
        println("$name от $brand выключен.")
    }
}

class Computer(brand: String, val ram: Int) : Device("Компьютер", brand) {
    fun runProgram(programName: String) {
        println("Программа $programName запущена на компьютере с $ram ГБ оперативной памяти.")
    }
}

class Smartphone(brand: String, val cameraResolution: Int) : Device("Смартфон", brand) {
    fun takePhoto() {
        println("Фото сделано. Разрешение $cameraResolution МП.")
    }
}

fun main() {
    val computer = Computer(brand = "Huawei", ram = 8)
    computer.turnOn()
    computer.runProgram("Android Studio")
    computer.turnOff()

    val smartphone = Smartphone(brand = "IPhone", cameraResolution = 3)
    smartphone.turnOn()
    smartphone.takePhoto()
    smartphone.turnOff()
}
