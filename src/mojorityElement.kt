fun main() {
    print(getMajorityElement(arrayOf(2,2,1,1,1,2,2)))
}

//Технически просто алгоритм большинства голосов Бойера-Мура
fun getMajorityElement(nums: Array<Int>) : Int {
    var counter = 0
    var majorityElement : Int = 0
    for (i in nums.indices) {
        if (counter == 0) {
            majorityElement = nums[i]
            counter++
        } else if(nums[i] == majorityElement) {
            counter++
        } else {
            counter--
        }
    }

    return majorityElement
}