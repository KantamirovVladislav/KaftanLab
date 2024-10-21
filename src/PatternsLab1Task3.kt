// Абстрактная фабрика
interface AbstractFactory {
    fun CreateProductA(Na: Int): AbstractProductA
    fun CreateProductB(Nb: Int): AbstractProductB
}

// Конкретная фабрика 1
class ConcreteFactory1 : AbstractFactory {
    override fun CreateProductA(Na: Int): AbstractProductA {
        return ProductA1(Na.toString())
    }

    override fun CreateProductB(Nb: Int): AbstractProductB {
        return ProductB1(Nb.toString())
    }
}

// Конкретная фабрика 2
class ConcreteFactory2 : AbstractFactory {
    override fun CreateProductA(Na: Int): AbstractProductA {
        return ProductA2(Na.toString())
    }

    override fun CreateProductB(Nb: Int): AbstractProductB {
        return ProductB2(Nb.toString())
    }
}


// Абстрактный продукт A
abstract class AbstractProductA(protected var info: String) {
    abstract fun GetInfo(): String
    abstract fun A()
}

// Конкретный продукт A1
class ProductA1(info: String) : AbstractProductA(info) {
    override fun GetInfo(): String {
        return info
    }

    override fun A() {
        // Преобразуем info в число, удваиваем его и сохраняем результат как строку
        val num = info.toIntOrNull() ?: 0
        info = (num * 2).toString()
    }
}

// Конкретный продукт A2
class ProductA2(info: String) : AbstractProductA(info) {
    override fun GetInfo(): String {
        return info
    }

    override fun A() {
        // Удваиваем строковое значение info
        info = info + info
    }
}


// Абстрактный продукт B
abstract class AbstractProductB(protected var info: String) {
    abstract fun GetInfo(): String
    abstract fun B(pa: AbstractProductA)
}

// Конкретный продукт B1
class ProductB1(info: String) : AbstractProductB(info) {
    override fun GetInfo(): String {
        return info
    }

    override fun B(pa: AbstractProductA) {
        // Складываем числовые значения info продуктов A и B
        val numA = pa.GetInfo().toIntOrNull() ?: 0
        val numB = info.toIntOrNull() ?: 0
        info = (numA + numB).toString()
    }
}

// Конкретный продукт B2
class ProductB2(info: String) : AbstractProductB(info) {
    override fun GetInfo(): String {
        return info
    }

    override fun B(pa: AbstractProductA) {
        // Добавляем строковое значение продукта A к значению продукта B
        info = info + pa.GetInfo()
    }
}


fun main() {
    // Тестирование с фабрикой ConcreteFactory1
    val factory1 = ConcreteFactory1()
    val productA1 = factory1.CreateProductA(5)
    val productB1 = factory1.CreateProductB(10)

    println("Factory1 - Before A():")
    println("ProductA1: ${productA1.GetInfo()}")
    println("ProductB1: ${productB1.GetInfo()}")

    productA1.A()
    productB1.B(productA1)

    println("Factory1 - After A() and B():")
    println("ProductA1: ${productA1.GetInfo()}")
    println("ProductB1: ${productB1.GetInfo()}")

    // Тестирование с фабрикой ConcreteFactory2
    val factory2 = ConcreteFactory2()
    val productA2 = factory2.CreateProductA(5)
    val productB2 = factory2.CreateProductB(10)

    println("\nFactory2 - Before A():")
    println("ProductA2: ${productA2.GetInfo()}")
    println("ProductB2: ${productB2.GetInfo()}")

    productA2.A()
    productB2.B(productA2)

    println("Factory2 - After A() and B():")
    println("ProductA2: ${productA2.GetInfo()}")
    println("ProductB2: ${productB2.GetInfo()}")
}
