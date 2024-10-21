import kotlin.math.*
import kotlin.random.Random

// Класс Request для описания заявки
data class Request(val arrivalTime: Double, val serviceTime: Double)

// Базовый класс для системы массового обслуживания
open class QueueSystem(protected val alpha: Double, protected val serviceTime: Double, protected val workTime: Double) {

    // Генерация времени между поступлениями заявок
    protected fun generateArrivalTime(): Double {
        return -ln(1.0 - Random.nextDouble()) / alpha
    }

    // Метод для моделирования работы системы (для переопределения в подклассах)
    open fun simulate(): Int {
        return 0
    }
}

// Трехканальная система массового обслуживания с отказами
class ThreeChannelQueueSystem(alpha: Double, serviceTime: Double, workTime: Double) : QueueSystem(alpha, serviceTime, workTime) {
    private val channels = 3  // Количество каналов

    override fun simulate(): Int {
        var currentTime = 0.0
        var servicedRequests = 0
        var rejectedRequests = 0

        // Очередь активных заявок (время освобождения канала)
        val activeChannels = mutableListOf<Double>()

        // Пока система работает (в течение workTime часов)
        while (currentTime < workTime * 60) {
            // Генерируем время следующей заявки
            val arrivalTime = generateArrivalTime()
            currentTime += arrivalTime

            // Удаляем каналы, которые освободились
            activeChannels.removeAll { it <= currentTime }

            if (activeChannels.size < channels) {
                // Если есть свободный канал, обслуживаем заявку
                activeChannels.add(currentTime + serviceTime)
                servicedRequests++
            } else {
                // Иначе заявка отклонена
                rejectedRequests++
            }
        }

        return servicedRequests
    }
}

// Функция для запуска моделирования методом Монте-Карло
fun monteCarloSimulation(alpha: Double, serviceTime: Double, workTime: Double, iterations: Int): Double {
    val queueSystem = ThreeChannelQueueSystem(alpha, serviceTime, workTime)
    var totalServiced = 0

    repeat(iterations) {
        totalServiced += queueSystem.simulate()
    }

    return totalServiced.toDouble() / iterations
}

// Пример использования
fun main() {
    val alpha = 0.2  // Параметр для распределения заявок
    val serviceTime = 2.0  // Время обслуживания одной заявки (минуты)
    val workTime = 8.0  // Время работы системы (часы)
    val iterations = 10000  // Количество итераций для Монте-Карло

    val expectedServicedRequests = monteCarloSimulation(alpha, serviceTime, workTime, iterations)
    println("Ожидаемое число обслуженных заявок: $expectedServicedRequests")
}
