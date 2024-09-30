class Wallet {
    // Приватный атрибут для хранения суммы денег
    private var balance: Double = 0.0

    // Метод для пополнения кошелька
    fun addMoney(amount: Double) {
        if (amount > 0) {
            balance += amount
            println("Кошелек пополнен на сумму: $amount. Текущий баланс: $balance")
        } else {
            println("Невозможно пополнить на отрицательную сумму.")
        }
    }

    // Метод для траты денег
    fun spendMoney(amount: Double) {
        if (amount > 0 && amount <= balance) {
            balance -= amount
            println("Вы потратили: $amount. Текущий баланс: $balance")
        } else if (amount > balance) {
            println("Недостаточно средств для траты: $amount. Текущий баланс: $balance")
        } else {
            println("Невозможно потратить отрицательную сумму.")
        }
    }

    // Метод для проверки текущего состояния кошелька
    fun checkBalance(): Double {
        println("Текущий баланс: $balance")
        return balance
    }
}

fun main() {
    // Создаем объект кошелька
    val wallet = Wallet()

    // Пополняем кошелек
    wallet.addMoney(100.0)

    // Тратим деньги
    wallet.spendMoney(30.0)

    // Проверяем текущий баланс
    wallet.checkBalance()
}
