// Базовый класс Subject
open class Subject {
    private val observers = mutableListOf<Observer>()

    // Добавление наблюдателя
    fun Attach(observ: Observer) {
        observers.add(observ)
    }

    // Удаление наблюдателя
    fun Detach(observ: Observer) {
        observers.remove(observ)
    }

    // Уведомление всех наблюдателей
    fun Notify() {
        for (observer in observers) {
            observer.Update()
        }
    }
}

// Конкретный класс ConcreteSubject
class ConcreteSubject : Subject() {
    private var state: Char = ' ' // Текущее состояние

    // Метод для изменения состояния
    fun SetState(st: Char) {
        if (state != st) {
            state = st
            Notify() // Уведомляем наблюдателей об изменении состояния
        }
    }

    // Метод для получения текущего состояния
    fun GetState(): Char {
        return state
    }
}


// Абстрактный класс Observer
abstract class Observer {
    abstract fun Update()
}

// Конкретный класс ConcreteObserver
class ConcreteObserver(
    private val subj: ConcreteSubject, // Ссылка на субъект
    private val detachInfo: Char // Символ для отсоединения
) : Observer() {

    private val log = StringBuilder() // Для хранения изменений

    // Присоединение наблюдателя к субъекту
    fun Attach() {
        subj.Attach(this)
    }

    // Отсоединение наблюдателя от субъекта
    fun Detach() {
        subj.Detach(this)
    }

    // Метод для получения лога наблюдателя
    fun GetLog(): String {
        return log.toString()
    }

    // Метод для обновления состояния наблюдателя
    override fun Update() {
        val currentState = subj.GetState()
        log.append(currentState) // Добавляем новое состояние в лог

        // Если состояние соответствует символу detachInfo, отсоединяем наблюдателя
        if (currentState == detachInfo) {
            Detach()
        }
    }
}


fun main() {
    // Создаем объект субъекта
    val subj = ConcreteSubject()

    // Коллекция наблюдателей с уникальными символами для отсоединения
    val observers = listOf(
        ConcreteObserver(subj, 'x'),
        ConcreteObserver(subj, 'y'),
        ConcreteObserver(subj, 'z')
    )

    // Присоединяем всех наблюдателей к субъекту
    for (observer in observers) {
        observer.Attach()
    }

    // Строка для тестирования
    val testString = "abcdxyz"

    // Проходимся по строке и изменяем состояние субъекта
    for (char in testString) {
        subj.SetState(char)
    }

    // Выводим лог каждого наблюдателя
    for ((index, observer) in observers.withIndex()) {
        println("Observer ${index + 1} log: ${observer.GetLog()}")
    }
}
