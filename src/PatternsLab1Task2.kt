// Конкретный продукт 1
open class ConcreteProduct11(info: String) {
    protected var info: String = info.toLowerCase()

    open fun GetInfo(): String {
        return info
    }

    open fun Transform() {
        info = info.mapIndexed { index, char ->
            if (char != '*' && index != info.length - 1) "$char*" else "$char"
        }.joinToString("")
    }
}

// Конкретный продукт 2, наследуется от ConcreteProduct1
class ConcreteProduct22(info: String) : ConcreteProduct11(info) {

    init {
        // Преобразуем первый символ к верхнему регистру
        if (this.info.isNotEmpty()) {
            this.info = info.substring(0, 1).toUpperCase() + info.substring(1).toLowerCase()
        }
    }

    override fun Transform() {
        // Добавляем "=" в начало и конец строки
        info = "==" + info + "=="
        // Вызываем базовый Transform для добавления звездочек
        super.Transform()
    }
}


// Конкретный создатель 1
open class ConcreteCreator11 {
    open fun FactoryMethod(info: String): ConcreteProduct11 {
        return ConcreteProduct11(info)
    }

    fun AnOperation(info: String): String {
        val product = FactoryMethod(info)
        product.Transform()
        product.Transform()
        return product.GetInfo()
    }
}

// Конкретный создатель 2, наследуется от ConcreteCreator1
class ConcreteCreator22 : ConcreteCreator11() {
    override fun FactoryMethod(info: String): ConcreteProduct22 {
        return ConcreteProduct22(info)
    }
}


fun main() {
    val strings = listOf("Hello", "World", "Test", "Design", "Pattern")

    val creator1 = ConcreteCreator11()
    val creator2 = ConcreteCreator22()

    println("Using ConcreteCreator1:")
    strings.forEach { str ->
        println("Original: $str")
        println("Transformed: ${creator1.AnOperation(str)}")
        println()
    }

    println("Using ConcreteCreator2:")
    strings.forEach { str ->
        println("Original: $str")
        println("Transformed: ${creator2.AnOperation(str)}")
        println()
    }
}
