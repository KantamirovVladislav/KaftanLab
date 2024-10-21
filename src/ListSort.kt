fun main(){
    val list = arrayOf(1,3,5,7,9)
    val itemToInsert = 8

    println(insertItem(itemToInsert, list).toList())
}


fun insertItem(item: Int, array: Array<Int>) : Array<Int> {
    var newArray = Array<Int>(array.size + 1) { 0 }
    newArray = array + item
    newArray.sort()
    return newArray
}