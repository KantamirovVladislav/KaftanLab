// Абстрактный класс продукта
abstract class Product {
    abstract fun GetInfo(): String
    abstract fun Transform()
}

// Абстрактный класс создателя
abstract class Creator {
    abstract fun FactoryMethod(info: String): Product

    fun AnOperation(info: String): String {
        val product = FactoryMethod(info)
        product.Transform()
        product.Transform()
        return product.GetInfo()
    }
}



// Конкретный продукт 1
class ConcreteProduct1(info: String) : Product() {
    private var info: String = info.toLowerCase()

    override fun GetInfo(): String {
        return info
    }

    override fun Transform() {
        info = info.mapIndexed { index, char ->
            if (char != ' ' && index != info.length - 1) "$char " else "$char"
        }.joinToString("")
    }
}

// Конкретный продукт 2
class ConcreteProduct2(info: String) : Product() {
    private var info: String = info.toUpperCase()

    override fun GetInfo(): String {
        return info
    }

    override fun Transform() {
        info = info.mapIndexed { index, char ->
            if (char != '*' && index != info.length - 1) "$char**" else "$char"
        }.joinToString("")
    }
}




// Конкретный создатель 1
class ConcreteCreator1 : Creator() {
    override fun FactoryMethod(info: String): Product {
        return ConcreteProduct1(info)
    }
}

// Конкретный создатель 2
class ConcreteCreator2 : Creator() {
    override fun FactoryMethod(info: String): Product {
        return ConcreteProduct2(info)
    }
}

fun main() {
    val strings = listOf("Hello", "World", "Test", "Design", "Pattern")

    val creator1 = ConcreteCreator1()
    val creator2 = ConcreteCreator2()

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
