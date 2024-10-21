data class Film(
    val title: String,
    val director: String,
    val releaseYear: Int
)

fun main() {
    val film = Film("Довод", "Кристофер Нолан", 2017)
    println(film.toString())
}
