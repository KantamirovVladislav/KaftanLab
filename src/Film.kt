class Film(
    val title: String,
    val director: String,
    val releaseYear: Int
) {
    // Метод для вывода информации о фильме
    fun printInfo() {
        println("Название: $title")
        println("Режиссер: $director")
        println("Год выхода: $releaseYear")
    }
}

fun main() {
    // Создаем объект класса Film
    val film = Film("Интерстеллар", "Кристофер Нолан", 2014)

    // Выводим информацию о фильме
    film.printInfo()
}
