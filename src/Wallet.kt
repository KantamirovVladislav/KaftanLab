class Wallet {
    private var balance: Double = 0.0
    fun addMoney(amount: Double) {
        if (amount > 0) {
            balance += amount
            println("Кошелек пополнен на сумму: $amount. Текущий баланс: $balance")
        } else {
            println("Невозможно пополнить на отрицательную сумму.")
        }
    }

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

    fun checkBalance(): Double {
        println("Текущий баланс: $balance")
        return balance
    }
}

fun main() {
    val wallet = Wallet()

    wallet.addMoney(100.0)

    wallet.spendMoney(30.0)

    wallet.checkBalance()
}
