import kotlin.random.Random
import kotlin.math.abs

// Класс Element для описания элемента системы
open class Element(val reliability: Double) {
    // Метод для проверки, работает ли элемент (успех с вероятностью reliability)
    fun works(): Boolean {
        return Random.nextDouble() <= reliability
    }
}

// Класс Block для описания блока системы
class Block(private val elements: List<Element>) {
    // Метод для проверки, работает ли блок (блок работает, если работает хотя бы один элемент)
    fun works(): Boolean {
        return elements.any { it.works() }
    }

    // Метод для вычисления аналитической вероятности безотказной работы блока
    fun analyticalReliability(): Double {
        return 1.0 - elements.fold(1.0) { acc, element ->
            acc * (1.0 - element.reliability)
        }
    }
}

// Класс SystemReliability для моделирования надежности системы
class SystemReliability(
    private val block1: Block,
    private val block2: Block
) {
    // Метод для оценки надежности системы методом Монте-Карло
    fun monteCarloSimulation(trials: Int): Double {
        var successCount = 0

        repeat(trials) {
            val block1Works = block1.works()
            val block2Works = block2.works()

            // Система работает, если работают оба блока
            if (block1Works && block2Works) {
                successCount++
            }
        }

        // Вероятность безотказной работы системы
        return successCount.toDouble() / trials
    }

    // Метод для аналитического расчета надежности системы
    fun analyticalReliability(): Double {
        val block1Reliability = block1.analyticalReliability()
        val block2Reliability = block2.analyticalReliability()

        // Система работает, если работают оба блока
        return block1Reliability * block2Reliability
    }
}

// Функция для вычисления абсолютной погрешности
fun calculateError(estimated: Double, analytical: Double): Double {
    return abs(estimated - analytical)
}

// Пример использования
fun main() {
    // Вероятности безотказной работы элементов
    val pA = 0.95
    val pB = 0.9
    val pC = 0.92
    val pD = 0.88
    val pE = 0.87

    // Создаем элементы для каждого блока
    val block1Elements = listOf(Element(pA), Element(pB), Element(pC))
    val block2Elements = listOf(Element(pD), Element(pE))

    // Создаем блоки
    val block1 = Block(block1Elements)
    val block2 = Block(block2Elements)

    // Создаем систему
    val system = SystemReliability(block1, block2)

    // Количество испытаний для Монте-Карло
    val trials = 100

    // Оцениваем надежность методом Монте-Карло
    val estimatedReliability = system.monteCarloSimulation(trials)

    // Вычисляем аналитическую надежность
    val analyticalReliability = system.analyticalReliability()

    // Вычисляем абсолютную погрешность
    val error = calculateError(estimatedReliability, analyticalReliability)

    // Вывод результатов
    println("Оцененная надежность системы методом Монте-Карло: $estimatedReliability")
    println("Аналитическая надежность системы: $analyticalReliability")
    println("Абсолютная погрешность: $error")
}
